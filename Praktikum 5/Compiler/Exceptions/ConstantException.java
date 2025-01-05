package Compiler.Exceptions;

/**
 * Meine eigene Exception, wenn man versucht, eine Variable zu bekommen, es aber eine Konstante ist
 * */
public class ConstantException extends Exception {
    public ConstantException(String message) {
        super(message);
    }
}