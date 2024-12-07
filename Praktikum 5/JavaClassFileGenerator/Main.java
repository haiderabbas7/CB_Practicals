// Datei: Main.java
import java.io.*;

public class Main {
    public static void main(String[] args) {
        // Beispiel für die Bytecode-Generierung
		// 10 FF 10 02 10 02 36 00 36 01 15 00 10 FF 9f 00 3A 15 01 10 00 9f 00 14 15 00 10 00 a0 00 1D 15 01 10 01 9f 00 06 a7 00 0B 15 01 10 01 60 a7 FF D8 15 01 10 02 60 a7 FF D0 15 00 10 01 64 15 00 15 01 10 01 64 a7 FF C1 15 01 b8 (print) b1
        String bytecode = "10 02 36 00 10 01 15 00 15 00 15 00 68 68 60 b8 (print) b1"; // Beispiel für einfaches Bytecode
        MethodObject mainMethod = new MethodObject("main", 1, bytecode);
        
        // Instanz des Generators erstellen und Klassendatei generieren
        JavaClassFileGenerator generator = new JavaClassFileGenerator("MyGeneratedClass", true, true, true);
        generator.generateClassFile(mainMethod);
    }
}
