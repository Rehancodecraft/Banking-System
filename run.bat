@echo off
echo Compiling Java files...
javac -cp "Libraries\sqlite-jdbc-3.42.0.0.jar" -d out Bank\*.java

if %errorlevel% neq 0 (
    echo Compilation failed!
    exit /b %errorlevel%
)

echo Running the program...
java -cp "out;Libraries\sqlite-jdbc-3.42.0.0.jar" Bank.Main
pause
