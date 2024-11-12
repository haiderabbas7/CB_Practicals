package SymbolTable;

public class Symbol {
    public enum Type {
        CONSTANT,
        VARIABLE
    }

    private Type type;
    private String value;

    public Symbol(Type type, String value) {
        this.type = type;
        this.value = value;
    }

    public Type getType() {
        return type;
    }

    public String getValue() {
        return value;
    }
}