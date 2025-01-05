@echo off
rem RUF FOLGENDEN BEFEHL AUF:   ..\execute.bat, alternativ auch mit Angabe der .jj Datei
rem Überprüfe, ob ein Parameter übergeben wurde
if "%~1"=="" (
    rem Zähle die Anzahl der .jj Dateien im Verzeichnis
    for /f %%a in ('dir /b *.jj 2^>nul ^| find /c /v ""') do set JJ_COUNT=%%a

    rem Wenn keine .jj Datei vorhanden ist
    if "%JJ_COUNT%"=="0" (
        echo Keine .jj Datei im Verzeichnis gefunden.
        exit /b 1
    )

    rem Wenn mehr als eine .jj Datei vorhanden ist
    if "%JJ_COUNT%" gtr "1" (
        echo Mehr als eine .jj Datei im Verzeichnis gefunden. Bitte eine Datei angeben.
        exit /b 1
    )

    rem Wenn genau eine .jj Datei vorhanden ist, setze diese als Parameter
    for %%f in (*.jj) do set JJ_FILE=%%f
) else (
    set JJ_FILE=%~1
)

rem Überprüfe, ob die angegebene Datei existiert
if not exist "%JJ_FILE%" (
    echo Die angegebene Datei %JJ_FILE% existiert nicht.
    exit /b 1
)

rem Lösche alle Dateien außer der angegebenen .jj Datei und anderen .jj Dateien
for %%f in (*) do (
    if not "%%f"=="%JJ_FILE%" if not "%%~xf"==".jj" del "%%f"
)

rem Lösche alle .class Dateien im aktuellen Verzeichnis und allen Unterverzeichnissen
rem del /s *.class
del *.class
del ./Compiler/*.class

rem Führe javacc mit der angegebenen Datei aus
javacc %JJ_FILE%

rem Überprüfe, ob javacc erfolgreich war
if errorlevel 1 (
    echo Fehler beim Ausführen von javacc.
    exit /b 1
)

rem Überprüfe, ob .java Dateien vorhanden sind
for /f %%a in ('dir /b *.java 2^>nul ^| find /c /v ""') do set JAVA_COUNT=%%a

if "%JAVA_COUNT%"=="0" (
    echo Keine .java Dateien zum Kompilieren gefunden.
    exit /b 1
)

javac ./Compiler/Exceptions/*.java

rem Kompiliere alle Java-Dateien
javac *.java

