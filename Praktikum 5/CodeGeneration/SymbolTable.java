package CodeGeneration;

import java.util.Hashtable;

public class SymbolTable {
    private Hashtable<String, Symbol> symbolTabelle = new Hashtable<String, Symbol>();
    //globale Variable zum Ermitteln von Speicherindizes
    private static int nextIndex = 0;

    //TODO: idk wie richtig das ist, dass die exception geworfen und direkt gecatched wird
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

    public Symbol getSymbolObject(String ident) throws UnknownSymbolException {
        if (!symbolTabelle.containsKey(ident)) {
            throw new UnknownSymbolException("Symbol " + ident + " wurde nicht definiert.");
        } else {
            Symbol symbol = symbolTabelle.get(ident);
            return symbol;
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