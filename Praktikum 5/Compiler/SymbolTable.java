package Compiler;

import java.util.Hashtable;
import Compiler.Exceptions.*;

public class SymbolTable {
    private Hashtable<String, Symbol> symbolTabelle = new Hashtable<String, Symbol>();
    //DER FUCKING FEHLER WAR HIER, WEIL ICH INDIZES BEI 1 ANFANGEN LIEß
    private int nextIndex = 0;

    public SymbolTable(){
        this.nextIndex = 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SymbolTable:\n");
        for (String key : symbolTabelle.keySet()) {
            Symbol symbol = symbolTabelle.get(key);
            sb.append("Identifier: ").append(key)
                    .append(", Value: ").append(symbol.getValue())
                    .append(", IsVariable: ").append(symbol.isVariable())
                    .append("\n");
        }
        return sb.toString();
    }

    public String getSymbol(String ident) throws UnknownSymbolException {
        if (!symbolTabelle.containsKey(ident)) {
            throw new UnknownSymbolException("Symbol " + ident + " wurde nicht definiert.");
        } else {
            return symbolTabelle.get(ident).getValue();
        }
    }

    public Symbol getSymbolObject(String ident) throws UnknownSymbolException {
        if (!symbolTabelle.containsKey(ident)) {
            throw new UnknownSymbolException("Symbol " + ident + " wurde nicht definiert.");
        } else {
            return symbolTabelle.get(ident);
        }
    }

    public void addConstant(String ident, String value) {
        try {
            if (symbolTabelle.containsKey(ident)) {
                throw new SymbolAlreadyDefinedException("Konstante " + ident + " wurde schon definiert.");
            } else {
                symbolTabelle.put(ident, new Symbol(ident, value, false));
            }
        } catch (SymbolAlreadyDefinedException e) {
            System.err.println(e.getMessage());
            throw new Error(e);
        }
    }

    public String addVariable(String ident) {
        try {
            if (symbolTabelle.containsKey(ident)) {
                throw new SymbolAlreadyDefinedException("Variable " + ident + " wurde schon definiert.");
            } else {
                Symbol symbol = new Symbol(ident, Integer.toString(nextIndex), true);
                symbolTabelle.put(ident, symbol);
                // nächste Variable hat den nächsten Index
                this.nextIndex = this.nextIndex + 1;
                // stellt sicher dass immer zweistellig zurückgegeben wird
                return String.format("%02d", this.nextIndex - 1);
            }
        } catch (SymbolAlreadyDefinedException e) {
            System.err.println(e.getMessage());
            throw new Error(e);
        }
    }

    //returned für den Identifier den Index der Variable, sonst bei Konstante Exception
    public String getVariable(String ident) throws LWertException, ConstantException {
        if (!symbolTabelle.containsKey(ident)) {
            throw new LWertException("Symbol " + ident + " wurde nicht definiert.");
        }
        else {
            Symbol symbol = symbolTabelle.get(ident);
            if (!symbol.isVariable()) {
                throw new ConstantException("Symbol " + ident + " ist eine Konstante.");
            } else {
                return symbol.getValue();
            }
        }
    }

    //returned für den Identifier, ob das Symbol eine Variable oder Konstante ist
    public boolean isVariable(String ident) throws UnknownSymbolException{
        if (!symbolTabelle.containsKey(ident)) {
            throw new UnknownSymbolException("Symbol " + ident + " wurde nicht definiert.");
        }
        else {
            Symbol symbol = symbolTabelle.get(ident);
            return symbol.isVariable();
        }
    }
}