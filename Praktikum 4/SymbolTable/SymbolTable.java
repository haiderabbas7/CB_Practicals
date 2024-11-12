package SymbolTable;

import java.util.Hashtable;

public class SymbolTable {
    private Hashtable<String, Symbol> symbolTabelle = new Hashtable<String, Symbol>();

    public void addConstant(String ident, String value) throws SymbolAlreadyDefinedException {
        if (symbolTabelle.containsKey(ident)) {
            throw new SymbolAlreadyDefinedException("Konstante " + ident + " wurde schon definiert.");
        } else {
            symbolTabelle.put(ident, new Symbol(Symbol.Type.CONSTANT, value));
        }
    }

    public void addVariable(String ident, String value) throws SymbolAlreadyDefinedException {
        if (symbolTabelle.containsKey(ident)) {
            throw new SymbolAlreadyDefinedException("Variable " + ident + " wurde schon definiert.");
        } else {
            symbolTabelle.put(ident, new Symbol(Symbol.Type.VARIABLE, value));
        }
    }

    public Symbol getSymbol(String ident) throws UnknownSymbolException {
        if (!symbolTabelle.containsKey(ident)) {
            throw new UnknownSymbolException("Symbol " + ident + " wurde nicht definiert.");
        } else {
            return symbolTabelle.get(ident);
        }
    }
}