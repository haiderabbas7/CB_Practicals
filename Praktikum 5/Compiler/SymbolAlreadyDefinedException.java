package Compiler;

public class SymbolAlreadyDefinedException extends Exception {

	public SymbolAlreadyDefinedException(String message) {
		super(message);
	}
}