package Compiler;

import java.util.Arrays;
import java.util.ArrayList;

public class CompilerHelper {
    // Splits all function calls in the bytecode like (print) into two dummy bytes
    public static String[] splitAllFunctionCalls(String[] programArray) {
        ArrayList<String> result = new ArrayList<>();
        String dummyByte = "WW";
        for (String s : programArray) {
            if (s.charAt(0) == '(' || s.charAt(0) == '[') {
                result.add(dummyByte);
                result.add(dummyByte);
            } else {
                result.add(s);
            }
        }
        return result.toArray(new String[0]);
    }
}