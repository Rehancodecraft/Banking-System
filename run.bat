@echo off
echo Compiling Java files...

javac -cp "Libraries\sqlite-jdbc-3.42.0.0.jar;Libraries\openpdf-2.0.3.jar" -d out Bank\*.java

if %errorlevel% neq 0 (
    echo Compilation failed!
    exit /b %errorlevel%
)

echo Running the program...
java -cp "out;Libraries\sqlite-jdbc-3.42.0.0.jar;Libraries\openpdf-2.0.3.jar" Bank.Main

pause

