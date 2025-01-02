package Compiler.Exceptions;

/**
 * Implementieren Sie zusätzlich den Ausnahmetyp LWertException, der immer dann
 * geworfen wird, wenn es sich bei der linken Seite einer Zuweisung nicht um einen
 * Variablenbezeichner handelt.
 * */
public class LWertException extends Exception {
    public LWertException(String message) {
        super(message);
    }
}