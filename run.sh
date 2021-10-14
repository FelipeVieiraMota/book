#!/bin/bash

# Bash for Linux
clear
export TERM=xterm
sudo systemctl restart docker.socket docker.service
docker rm -f $(docker ps -a -q)
docker build -t motafelipe/spring-api .
# We must need to run the container in background.
docker run -p 80:8080  -d --name spring-api motafelipe/spring-api
exit
exit
exit