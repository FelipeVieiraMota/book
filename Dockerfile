# ****************************************************************
# Example of commands to build and run ( Execute it in the shell )
# ****************************************************************
# Bash for Linux
# clear
# JAVA_HOME=$(dirname $( readlink -f $(which java) ))
# JAVA_HOME=$(realpath "$JAVA_HOME"/../)
# export JAVA_HOME
# mvn clean
# mvn package
# docker build -t motafelipe/spring-api .
# docker run -p 3000:8080  motafelipe/spring-api
#*****************************************************************

# In this case we are using .jar insted of .war
# docker build -t motafelipe/spring-api . (Never uncomment this line)

# To run in EC2 instance
FROM openjdk:12-alpine
COPY target/*.jar /backoffice.jar
CMD ["java", "-jar", "/backoffice.jar"]
ENTRYPOINT [ "sh", "-c", "java -jar backoffice.jar"]
