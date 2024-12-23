package Compiler;

import java.util.Arrays;
import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        // Test the splitAllFunctionCalls method
        String[] programArray = {"10", "00", "36", "01", "b8", "(print)", "10", "02"};
        String[] result = CompilerHelper.splitAllFunctionCalls(programArray);

        // Print the result
        for (String s : result) {
            System.out.print(s + " ");
        }
    }
}