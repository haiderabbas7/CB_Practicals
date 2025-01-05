package Compiler.Exceptions;

/**
 * MEINE EIGENE EXCEPTION ANSTELLE DER RWERTEXCEPTION
 * joa basically wenn man versucht eine Methode zu callen, welche nicht existiert
 * */
public class UnknownMethodException extends Exception {
    public UnknownMethodException(String message) {
        super(message);
    }
}