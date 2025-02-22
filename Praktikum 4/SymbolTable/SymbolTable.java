package SymbolTable;

import java.util.Hashtable;

public class SymbolTable {
    private Hashtable<String, Symbol> symbolTabelle = new Hashtable<String, Symbol>();
    //globale Variable zum Ermitteln von Speicherindizes
    private static int nextIndex = 0;

    public void addConstant(String ident, String value) throws SymbolAlreadyDefinedException {
        if (symbolTabelle.containsKey(ident)) {
            throw new SymbolAlreadyDefinedException("Konstante " + ident + " wurde schon definiert.");
        } else {
            symbolTabelle.put(ident, new Symbol(ident, value, false));
        }
    }

    public String addVariable(String ident) throws SymbolAlreadyDefinedException {
        if (symbolTabelle.containsKey(ident)) {
            throw new SymbolAlreadyDefinedException("Variable " + ident + " wurde schon definiert.");
        } else {
            Symbol symbol = new Symbol(ident, Integer.toString(nextIndex), true);
            symbolTabelle.put(ident, symbol);
            //nächste Variable hat den nächsten Index
            nextIndex++;
            //stellt sicher dass immer zweistellig zurückgegeben wird
            return String.format("%02d", nextIndex - 1);
        }
    }

    public String getSymbol(String ident) throws UnknownSymbolException {
        if (!symbolTabelle.containsKey(ident)) {
            throw new UnknownSymbolException("Symbol " + ident + " wurde nicht definiert.");
        } else {
            String value = symbolTabelle.get(ident).getValue();
            return String.format("%02x", Integer.parseInt(value));
        }
    }

    //returned für den Identifier, ob das Symbol eine Variable oder Konstante ist
    public boolean isVariable(String ident) throws UnknownSymbolException {
        if (!symbolTabelle.containsKey(ident)) {
            throw new UnknownSymbolException("Symbol " + ident + " wurde nicht definiert.");
        } else {
            Symbol symbol = symbolTabelle.get(ident);
            return symbol.isVariable();
        }
    }
}