@echo off

rem RUF FOLGENDEN BEFEHL AUF:   ..\execute.bat <Name der .jj Datei, zb P2.jj>
rem Überprüfe, ob ein Parameter übergeben wurde
if "%~1"=="" (
    echo Bitte den Namen der Datei angeben, z.B. P2.jj.
    exit /b 1
)

rem Überprüfe, ob die angegebene Datei existiert
if not exist "%~1" (
    echo Die angegebene Datei %~1 existiert nicht.
    exit /b 1
)

rem Lösche alle Dateien außer der angegebenen .jj Datei und anderen .jj Dateien
for %%f in (*) do (
    if not "%%f"=="%~1" if not "%%~xf"==".jj" del "%%f"
)

rem Führe javacc mit der angegebenen Datei aus
javacc %~1

rem Kompiliere alle Java-Dateien
javac *.java