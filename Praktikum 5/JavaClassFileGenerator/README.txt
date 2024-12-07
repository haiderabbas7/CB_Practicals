mit diesem code kann man aus Java bytecode ausführbare programme machen

1. in einem neuen CMD in den Ordner gehen
	cd C:\Stuff\UNI DATEIEN\FH\5. Semester\CB - Compilerbau\Materialien zu Praktikum und Übung\JavaClassFileGenerator
2. bytecode in die Main.java packen
3. javac Main.java zum Kompilieren der Klasse
4. java Main zum generieren der neuen Klasse aus dem bytecode
=> javap -v MyGeneratedClass um irgendwie den Bytecode anzuzeigen
=> java MyGeneratedClass um den Code auszuführen


ÄNDERUNGEN DIE ICH MACHEN MÖCHTE:
1. so wie beim MiniJava compiler dass man den bytecode über konsole eingeben kann, also nicht über die Main.java Datei
2. dass er kompilieren direkt macht

ABGEÄNDERT FÜR P5:
In P5 wurde ja die API mit in den Code integriert
Und die Class Datei wird automatisch erstellt
daher einfach machen java myClass zum Ausführen, oder wie oben mit javap -v