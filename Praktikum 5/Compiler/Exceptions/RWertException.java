package Compiler.Exceptions;

/**
 * Falls es sich bei einem Bezeichner auf der rechten Seite einer Zuweisung nicht um
 * den Bezeichner einer Variablen, einer Konstanten oder einer Funktion handelt.
 * */
public class RWertException extends Exception {
    public RWertException(String message) {
        super(message);
    }
}