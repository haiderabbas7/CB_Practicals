package CodeGeneration;

import CodeGeneration.SymbolTable;

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
    // gibt den Code zurück, um die Variable zu initialisieren
    public String initVariable(String varName, String varValue) {
        try {
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

    //DIESE FUNKTION GENERIERT DAS LABEL NICHT, PASSIERT ES FÜR DEN IF-CODE
    /*
    * Hier wird nur der Bytecode für die Auflösung der COndition
    * und der richtige SPrungbefehel OHNE label erstellt
    * */
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
    public String generateIfCode(String statement, String optElse) {
        try {
            //LN für Labelanfang, XN für Labelende
            String res = "";
            int ifLabel = labelGenerator.getLabel();
            res += "L" + ifLabel + " "  //anext
                    + statement;        //stmt1
            if(optElse != ""){
                int elseLabel = labelGenerator.getLabel();
                res += "a7 " + "L" + elseLabel + " "    //JMP anext+1
                        + "L" + ifLabel + " "           //anext:
                        + optElse                       //stmt2
                        + "X" + elseLabel + " ";       //anext+1:
            }
            //FALL kein else block angegeben
            else{
                res += "X" + ifLabel + " "; //anext:
            }
            return res;
        }
        catch(Exception e){
            System.err.println(e.getMessage());
            throw new Error(e);
        }
    }
    /**
     * ACHTUNG: SOLANGE ICH LABELS NICHT AUFLÖSE WIRD IMMER EINE EXCEPTION GEWORFEN
     * WEIL LN und XN keine validen hexadezimalen Zahlen sind
     */
}