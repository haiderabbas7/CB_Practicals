package Compiler;

import Compiler.SymbolTable;

public class Method {
    private String name;
    private boolean isFunction = false;
    private int parameterAmount = 0;
    private SymbolTable symbolTable = new SymbolTable();
    private String bytecode = "";

    public Method(String name, boolean isFunction, int parameterAmount){
        this.name = name;
        this.isFunction = isFunction;
        this.parameterAmount = parameterAmount;
    }

    public String getName() {
        return name;
    }

    public boolean isFunction() {
        return isFunction;
    }

    public int getParameterAmount() {
        return parameterAmount;
    }

    public String getBytecode() {
        return bytecode;
    }

    public void setBytecode(String bytecode) {
        this.bytecode = bytecode;
    }
}