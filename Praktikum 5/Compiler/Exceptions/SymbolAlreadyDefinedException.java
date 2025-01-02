package Compiler.Exceptions;

/**
 * "void addConstant(String id, String wert), die eine Konstante mit deren Wert
 * in die Symboltabelle einträgt, falls die Konstante noch nicht vorhanden ist, und
 * sonst eine selbst zu definierende SymbolAlreadyDefinedException wirft."
 * */
public class SymbolAlreadyDefinedException extends Exception {
	public SymbolAlreadyDefinedException(String message) {
		super(message);
	}
}