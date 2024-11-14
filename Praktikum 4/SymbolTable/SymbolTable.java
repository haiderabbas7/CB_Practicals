package SymbolTable;

import java.util.Hashtable;

public class SymbolTable {
    private Hashtable<String, String> symbolTabelle = new Hashtable<String, String>();
    private static int nextIndex = 0;

    public void addConstant(String ident, String value) throws SymbolAlreadyDefinedException {
        if (symbolTabelle.containsKey(ident)) {
            throw new SymbolAlreadyDefinedException("Konstante " + ident + " wurde schon definiert.");
        } else {
            symbolTabelle.put(ident, value);
        }
    }

    //FUNKTIONIERT
    /**
     * fügt die Variable mit dem Speicherwert (holt sich aus der globalen Variable) in Hashtable hinzu, wenn noch nicht drin
     * und returned den Index der Variable, damit in der P4.jj der code erzeugt werden kann*/
    public String addVariable(String ident) throws SymbolAlreadyDefinedException {
        if (symbolTabelle.containsKey(ident)) {
            throw new SymbolAlreadyDefinedException("Variable " + ident + " wurde schon definiert.");
        } else {
            symbolTabelle.put(ident, Integer.toString(nextIndex));
            nextIndex++;
            //glaube das String.format funktioniert richtig, aber nicht 100% getestet
            return String.format("%02d", nextIndex - 1);
        }
    }

    public String getSymbol(String ident) throws UnknownSymbolException {
        if (!symbolTabelle.containsKey(ident)) {
            throw new UnknownSymbolException("Symbol " + ident + " wurde nicht definiert.");
        } else {
            return symbolTabelle.get(ident);
        }
    }
}