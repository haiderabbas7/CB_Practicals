package Compiler.Exceptions;

/**
 * String getSymbol(String id), die den Wert der Konstanten aus der
 * Symboltabelle liefert, falls die Konstante vorhanden ist, und sonst eine selbst
 * zu definierende UnknownSymbolException wirft.
 * */
public class UnknownSymbolException extends Exception {
	public UnknownSymbolException(String message) {
		super(message);
	}
}