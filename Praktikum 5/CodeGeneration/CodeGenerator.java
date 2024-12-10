package CodeGeneration;

import CodeGeneration.SymbolTable;
import java.util.Arrays;

public class CodeGenerator {
    private static SymbolTable symbolTable;
    private static LabelGenerator labelGenerator = new LabelGenerator();

    public CodeGenerator(SymbolTable symbolTable) {
        this.symbolTable = symbolTable;
    }

    //deklariert die Variable in der symboltabelle und gibt den bytecode dafür zurück
    public String declareVariable(String varName) {
        try{
            String varIndex = symbolTable.addVariable(varName);
            return "10 00 36 " + varIndex + " ";
        } catch(Exception e){
            System.err.println(e.getMessage());
            throw new Error(e);
        }
    }

    //gibt den Code zurück, um die Variable zu initialisieren
    public String initVariable(String varName, String varValue) {
        try {
            //TODO: HIER MUSS ICH GLAUBE ICH STATTDESSEN %02x NUTZEN, PACKS REIN UND TEST AUS
            String varIndex = String.format("%02d", Integer.parseInt(symbolTable.getSymbolObject(varName).getValue()));
            return "10 " + String.format("%02x", Integer.parseInt(varValue)) + " 36 " + varIndex + " ";
        } catch(Exception e){
            System.err.println(e.getMessage());
            throw new Error(e);
        }
    }

    //joa erstellt den Code zum laden der Idents, also entweder Variable oder Konstante
    public String generateIdentCode(String ident){
        try{
            //prüft ob der Identifier eine Variable ist
            boolean isVariable = symbolTable.isVariable(ident);
            String hashMapValue = symbolTable.getSymbol(ident);
            //Variable = erzeug 15 für LOAD, Konstante = 10 für Laden der Konstante
            return (isVariable ? "15 " : "10 ") + hashMapValue + " ";
        } catch(Exception e){
            System.err.println(e.getMessage());
            throw new Error(e);
        }
    }

    public String generateConditionCode(String left, String right, String compOP){
        try {
            String res = left + right;
            switch (compOP) {
                case "==": //NEGATION: not equal
                    res += "a0 ";
                    break;
                case "!=": //NEGATION: equal
                    res += "9f ";
                    break;
                case "<": //NEGATION: größer gleich
                    res += "a2 ";
                    break;
                case ">": //NEGATION: kleiner gleich
                    res += "a4 ";
                    break;
                case "<=": //NEGATION: größer
                    res += "a3 ";
                    break;
                case ">=": //NEGATION: kleiner
                    res += "a1 ";
                    break;
                default:
                    throw new Exception("Invalid comparison operator: " + compOP);
            }
            return res;
        }
        catch(Exception e){
            System.err.println(e.getMessage());
            throw new Error(e);
        }
    }

    //OPTELSE IST AN DIESER STELLE EINFACH DAS STATEMENT DES ELSE BLOCKS
    public String generateIfCode(String condition, String statement, String optElse) {
        try {
            //LN für Labelanfang, XN für Labelende
            String res = condition;
            int anext = labelGenerator.getLabel();
            res += "00 L" + anext + " "  //(JMC) anext
                    + statement;        //stmt1
            if(optElse != ""){
                int anext_plus_1 = labelGenerator.getLabel();
                res += "a7 00 L" + anext_plus_1 + " "    //JMP anext+1
                        + "X" + anext + " "           //anext:
                        + optElse                       //stmt2
                        + "X" + anext_plus_1 + " ";       //anext+1:
            }
            //FALL kein else block angegeben
            else{
                res += "X" + anext + " "; //anext:
            }
            return res;
        }
        catch(Exception e){
            System.err.println(e.getMessage());
            throw new Error(e);
        }
    }

    public String generateWhileCode(String condition, String statement){
        try {
            int anext = labelGenerator.getLabel();
            int anext_plus_1 = labelGenerator.getLabel();
            String res = "X" + anext + " "      //anext: ...
                    + condition                 //...cond1 und JMC...
                    + "00 L" + anext_plus_1 + " "  //...anext+1
                    + statement                 //stmt1
                    + "a7 00 L" + anext + " "      //JMP anext
                    + "X" + anext_plus_1 + " "; //anext+1
            return res;

        }
        catch(Exception e){
            System.err.println(e.getMessage());
            throw new Error(e);
        }
    }

    public String resolveLabels(String program){
        String programString = program;
        int labelCount = labelGenerator.getLabelCount();

        for(int i = 0; i < labelCount; i++){
            //er splitted programString in array bei spaces, sodass er nun ein string array hat, WAS EINE KOPIE IST
            String[] programArray = programString.split(" ");
            /*mit einer forschleife entfernt er alle weiteren schließenden Labels XN
		    * also wir sind nun bei Label i, es werden also alle Labels Xi+1 bis Xn entfernt
		    * die labels X0 bis Xi-1 wurden im vorherigen run ja aufgelöst
            * */
            for(int x = i + 1; x < labelCount; x++){
                final int index = x;
                programArray = Arrays.stream(programArray)
                        .filter(s -> !s.equals("X" + index))
                        .toArray(String[]::new);
            }
            //wir holen uns die position von Li und Xi
            int labelPos = Arrays.asList(programArray).indexOf("L" + i);
            int destinationPos = Arrays.asList(programArray).indexOf("X" + i);
            int distance = destinationPos - labelPos;
            if(distance > 0){
                distance += 2;
                String hexDistance = String.format("%02x", distance);
                programString = programString.replace("L" + i + " ", hexDistance + " ");
            }
            else {
                distance += 2;
                int positiveDistance = -distance;
                int hexOffset = 0xFFFF - positiveDistance;
                String hexDistance = String.format("%04x", hexOffset);
                hexDistance = hexDistance.substring(0, 2) + " " + hexDistance.substring(2);
                programString = programString.replace("00 L" + i + " ", hexDistance + " ");
            }

            //Und im programString dann noch "Xi " entfernen
            programString = programString.replace("X" + i + " ", "");
        }
        return programString;
    }
    /**
     * ACHTUNG: SOLANGE ICH LABELS NICHT AUFLÖSE WIRD IMMER EINE EXCEPTION GEWORFEN
     * WEIL LN und XN keine validen hexadezimalen Zahlen sind
     */
}