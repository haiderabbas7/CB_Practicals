package Compiler.Exceptions;

/**
 * Falls es sich um die falsche Anzahl der Parameter einer Funktion oder Prozedur
 * handelt.
 * */
public class WrongParametersException extends Exception {
    public WrongParametersException(String message) {
        super(message);
    }
}