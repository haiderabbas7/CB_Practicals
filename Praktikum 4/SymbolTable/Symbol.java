package SymbolTable;

public class Symbol {
    private String name;
    private String value;
    private boolean isVariable;

    public Symbol(String name, String value, boolean isVariable) {
        this.name = name;
        this.value = value;
        this.isVariable = isVariable;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public boolean isVariable() {
        return isVariable;
    }

    public void setValue(String value) {
        this.value = value;
    }
}