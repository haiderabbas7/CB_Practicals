package SymbolTable;

public class SymbolAlreadyDefinedException extends Exception {

	public SymbolAlreadyDefinedException(String message) {
		super(message);
	}
}