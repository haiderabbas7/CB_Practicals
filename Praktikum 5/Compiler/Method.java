package Compiler;

import Compiler.SymbolTable;

//Oberklasse für Routinen
public class Method {
    private String name;
    private boolean isFunction = false;
    private int parameterAmount = 0;
    private SymbolTable symbolTable = new SymbolTable();
    private String bytecode = "";
}