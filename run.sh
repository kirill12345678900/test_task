#!/bin/bash

if [ $# -eq 0 ]; then
    echo "Usage: ./run.sh <path_to_tickets.json>"
    exit 1
fi

if ! command -v java &> /dev/null; then
    echo "Error: Java is not installed. Please install Java 17+"
    exit 1
fi

java -jar target/untitled2-1.0-SNAPSHOT-jar-with-dependencies.jar "$1"