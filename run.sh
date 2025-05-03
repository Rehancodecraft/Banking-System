#!/bin/bash

# Compile all Java files with JDBC and OpenPDF jars
javac -cp "Libraries/sqlite-jdbc-3.42.0.0.jar:Libraries/openpdf-2.0.3.jar" -d out Bank/*.java

# Run the main class with both jars in the classpath
java -cp "out:Libraries/sqlite-jdbc-3.42.0.0.jar:Libraries/openpdf-2.0.3.jar" Bank.Main

