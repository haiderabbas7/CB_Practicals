package Compiler;

import Compiler.SymbolTable;
import Compiler.Method;
import java.util.Arrays;
import java.util.ArrayList;

public class Compiler {
    private static SymbolTable mainSymbolTable;
    private static LabelGenerator labelGenerator = new LabelGenerator();
    private Hashtable<String, Method> methods = new Hashtable<String, Method>();

    public Compiler() {
        this.methods.put("main", new Method("main", false, 1));
    }

    public SymbolTable getSymbolTable(String scope){
        return this.methods.get(scope).getSymbolTable();
    }

    public void addConstant(String scope, String constname, String number){
        this.getSymbolTable(scope).addConstant(constname, number);
    }

    public String getVariable(String scope, String ident){
        return this.getSymbolTable(scope).getVariable(ident);
    }

    //deklariert die Variable in der symboltabelle und gibt den bytecode dafür zurück
    public String declareVariable(String scope, String varName) {
        try{
            String varIndex = this.getSymbolTable(scope).addVariable(varName);
            return "10 00 36 " + varIndex + " ";
        } catch(Exception e){
            System.err.println(e.getMessage());
            throw new Error(e);
        }
    }

    //gibt den Code zurück, um die Variable zu initialisieren
    public String initVariable(String scope, String varName, String varValue) {
        try {
            String varIndex = String.format("%02x", Integer.parseInt(this.getSymbolTable(scope).getSymbolObject(varName).getValue()));
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
            boolean isVariable = mainSymbolTable.isVariable(ident);
            String hashMapValue = mainSymbolTable.getSymbol(ident);
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
        System.out.println("\nPROGRAM VOR AUFLOESUNG:\n" + program + "\n\n");
        String programString = program;
        int labelCount = labelGenerator.getLabelCount();

        for(int i = 0; i < labelCount; i++){
            //Bytecode anhand der Whitespaces in Array packen
            //aus diesem Array werden dann die Function calls in zwei Dummy-Bytes aufgelöst
            //schließlich noch Umwandlung in ArrayList
            ArrayList<String> programList = new ArrayList<>(
                    Arrays.asList(
                            CompilerHelper.splitAllFunctionCalls(
                                    programString.split(" "))));

            /*mit einer forschleife entfernt er alle weiteren schließenden Labels XN
		    * also wir sind nun bei Label i, es werden also alle Labels Xi+1 bis Xn entfernt
		    * die labels X0 bis Xi-1 wurden im vorherigen run ja aufgelöst
            * */
            for(int x = i + 1; x < labelCount; x++){
                for(int y = 0; y < programList.size(); y++){
                    if(programList.get(y).charAt(0) == 'X' && Integer.parseInt(programList.get(y).substring(1)) == y){
                        programList.remove(y);
                        System.out.println("Temporarily removed " + x);
                    }
                }
            }

            //wir holen uns die position von Li und Xi
            int labelPos = programList.indexOf("L" + i);
            System.out.println(i + " labelPos: " + labelPos);
            int destinationPos = programList.indexOf("X" + i);
            System.out.println(i + " destinationPos: " + destinationPos);
            int distance = destinationPos - labelPos;
            if(distance > 0){
                //Addiert hier 2 stellen für die zwei Bytes vor dem L1
                distance += 2;
                String hexDistance = String.format("%02x", distance);
                programString = programString.replace("L" + i + " ", hexDistance + " ");
                System.out.println("Replaced " + i + " with " + hexDistance + "\n");
            }
            else {
                int positiveDistance = -distance; //invertieren damit wir einfacher damit rechnen können
                positiveDistance -= 1; //-1 weil das X0 als Byte mitgezählt wird weil negativer Sprung
                positiveDistance -= 2; //-2 weil wir beim Sprung die zwei Bytes vor dem Ln beim call mitgezählt haben
                System.out.println("positiveDistance: " + positiveDistance + "\n");

                int hexOffset = 0xFFFF - positiveDistance + 1; //IDK OB DAS PLUS 1 IN JEDEM FALL RICHTIG IST

                String hexDistance = String.format("%04x", hexOffset);
                hexDistance = hexDistance.substring(0, 2) + " " + hexDistance.substring(2);
                programString = programString.replace("00 L" + i + " ", hexDistance + " ");
                System.out.println("Replaced " + i + " with " + hexDistance + "\n");
            }

            //Und im programString dann noch "Xi " entfernen
            programString = programString.replace("X" + i + " ", "");
        }
        return programString;
    }


    //sobald der Scope bekannt ist wird hier das Method objekt mit der dazugehörigen SymbolTabelle erstellt
    public Method createMethod(String scope, boolean isFunction){
        this.methods.put(scope, new Method(), isFunction);
    }

    //Hier werden nur die Parameter in der SymbolTabelle verankert
    public void defineParameters(String scope, String routinenparameter){
        //TODO: HIER WEITER
    }

    //Hier wird der Code der Prozedur erstellt und dem entsprechenden Method object zurückgegeben
    public void generateProcedureCode(String scope, String routinenblock){
        //TODO: HIER WEITER
    }

    //Hier wird der Code der Funktion erstellt und dem entsprechenden Method object zurückgegeben
    public void generateFunctionCode(String scope, String routinenblock){
        //TODO: HIER WEITER
    }
}