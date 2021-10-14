#!/bin/bash

##################
# Bash for Linux #
##################
clear

######################################
# 1) Cleaning *.jar                  #
######################################

echo "######################################"
echo "1) Cleaning *.jar"
echo "######################################"
echo
mvn clean
echo
echo "Cleaning *.jar finished"
echo
echo "# -- END -- #"
echo
echo
echo
echo
echo
######################################

# -- END -- #

######################################
# 2) Packing *.jar                   #
######################################
echo "######################################"
echo "2) Packing *.jar"
echo "######################################"
echo
mvn package
echo
echo "Packing *.jar finished"
echo
echo "# -- END -- #"
echo
echo
echo
echo
echo
######################################

# -- END -- #

######################################
# 3) Changing current directory #
######################################
echo "######################################"
echo "3) Changing current directory "
echo "######################################"
echo
cd ~/workspace/Canada/AWS-KEYS/

echo "Current Directory"
pwd
echo
ls -alF
echo
echo "# -- END -- #"
echo
echo
echo
echo
echo
################################

# -- END -- #

################################################################
# 4) Coping jar file from local machine to ec2 instance        #
################################################################
echo "######################################"
echo "4) Coping jar file from local machine to ec2 instance"
echo "######################################"
scp -i "BackOfficeStudents.pem" ~/workspace/Canada/backoffice/target/backoffice-0.0.1-SNAPSHOT.jar ubuntu@ec2-3-87-208-55.compute-1.amazonaws.com:/home/ubuntu/target
echo "Copied jar file from local machine to ec2 instance"
echo "~/workspace/Canada/backoffice/target/backoffice-0.0.1-SNAPSHOT.jar"
echo
echo "# -- END -- #"
echo
echo
echo
echo
echo
################################################################

# -- END -- #

################################################################
# 5) Coping jar file from local machine to ec2 instance        #
################################################################
echo "######################################"
echo "5) Coping latest 'Dockerfile' file from local machine to ec2 instance"
echo "######################################"
scp -i "BackOfficeStudents.pem" ~/workspace/Canada/backoffice/Dockerfile ubuntu@ec2-3-87-208-55.compute-1.amazonaws.com:/home/ubuntu
echo "Copied latest 'Dockerfile' file from local machine to ec2 instance"
echo "~/workspace/Canada/backoffice/Dockerfile"
echo
echo "# -- END -- #"
echo
echo
echo
echo
echo
################################################################

# -- END -- #

################################################################
# 6) Coping jar file from local machine to ec2 instance        #
################################################################
echo "######################################"
echo "6) Coping latest 'run.sh' file from local machine to ec2 instance"
echo "######################################"
scp -i "BackOfficeStudents.pem" ~/workspace/Canada/backoffice/run.sh ubuntu@ec2-3-87-208-55.compute-1.amazonaws.com:/home/ubuntu
echo "Copied latest 'run.sh' file from local machine to ec2 instance"
echo "~/workspace/Canada/backoffice/run.sh"
echo
echo "# -- END -- #"
echo
################################################################
echo
echo
echo "######################################"
echo "Local Process Finished - SUCCESS"
echo "######################################"
echo
echo
echo "######################################"
echo "7) Starting Process in EC2 side"
echo "######################################"
echo
echo "Executing ./run.sh in EC2 side"
ssh -i "BackOfficeStudents.pem" ubuntu@ec2-3-87-208-55.compute-1.amazonaws.com '/home/ubuntu/run.sh'
echo
echo "######################################"
echo "Finished Process in EC2 side - SUCCESS"
echo "######################################"