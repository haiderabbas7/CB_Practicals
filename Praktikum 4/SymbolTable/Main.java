package SymbolTable;

import SymbolTable.SymbolTable;
import SymbolTable.SymbolAlreadyDefinedException;

public class Main {
    public static void main(String[] args) {
        SymbolTable symbolTable = new SymbolTable();

        /*try {
            // Test adding variables
            String index1 = symbolTable.addVariable("var1");
            System.out.println("Index of var1: " + index1);

            String index2 = symbolTable.addVariable("var2");
            System.out.println("Index of var2: " + index2);

            // Attempt to add a duplicate variable
            symbolTable.addVariable("var1");

        } catch (SymbolAlreadyDefinedException e) {
            System.err.println(e.getMessage());
        }*/
        //System.out.println(Integer.toHexString(Integer.parseInt("17")));
        final int c1 = 1, c2 = 2, c3 = 3;
        int v1 = 5, v2 = 10, v3 = 15;
        System.out.println((c1 + c2 / (v1*v2-v3)));
    }
}