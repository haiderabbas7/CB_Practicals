package SymbolTable;

import java.util.Hashtable;

public class SymbolTable{
    private Hashtable<String, String> symbolTabelle = new Hashtable<String, String>();

    public void addConstant(String ident, String value) throws SymbolAlreadyDefinedException {
        if (symbolTabelle.containsKey(ident)) {
            throw new SymbolAlreadyDefinedException("Konstante " + ident + " wurde schon definiert.");
        } else {
            symbolTabelle.put(ident, value);
        }
    }

    public String getSymbol(String ident) throws UnknownSymbolException {
        if(!symbolTabelle.containsKey(ident)) {
            throw new UnknownSymbolException("Symbol " + ident + " wurde nicht definiert.");
        } else {
            return symbolTabelle.get(ident);
        }
    }
}