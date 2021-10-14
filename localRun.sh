#!/bin/bash

# Bash for Linux
clear
JAVA_HOME=$(dirname $( readlink -f $(which java) ))
JAVA_HOME=$(realpath "$JAVA_HOME"/../)
export JAVA_HOME
mvn clean
mvn package
docker build -t motafelipe/spring-api .
docker run -p 3000:8080  --name spring-api motafelipe/spring-api