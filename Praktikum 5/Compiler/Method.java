package Compiler;

import Compiler.SymbolTable;
import Compiler.LabelGenerator;

public class Method {
    private String name;
    //isFunction = true => Die Funktion ist NICHT void
    private boolean isFunction;
    private int parameterAmount;
    private SymbolTable symbolTable = new SymbolTable();
    private LabelGenerator labelGenerator = new LabelGenerator();
    private String bytecode = "";

    public Method(String name, boolean isFunction){
        this.name = name;
        this.isFunction = isFunction;
    }

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

    public void setParameterAmount(int parameterAmount) {
        this.parameterAmount = parameterAmount;
    }

    public String getBytecode() {
        return bytecode;
    }

    public void setBytecode(String bytecode) {
        this.bytecode = bytecode;
    }

    public SymbolTable getSymbolTable() {
        return symbolTable;
    }

    public LabelGenerator getLabelGenerator() {
        return labelGenerator;
    }

    @Override
    public String toString() {
        return "Method{" +
                "name='" + name + '\'' +
                ", isFunction=" + isFunction +
                ", parameterAmount=" + parameterAmount +
                ", symbolTable=" + symbolTable +
                ", bytecode='" + bytecode + '\'' +
                '}';
    }
}