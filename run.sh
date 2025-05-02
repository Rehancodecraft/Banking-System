#!/bin/bash

# Compile all Java files with JDBC jar
javac -cp "Libraries/sqlite-jdbc-3.42.0.0.jar" -d out Bank/*.java


# Run the main class with the JDBC jar in the classpath
java -cp "out:Libraries/sqlite-jdbc-3.42.0.0.jar" Bank.Main


