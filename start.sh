#!/bin/bash

#check if container exists
if docker ps -a | grep -q "craw-cli"; 
then
  docker stop craw-cli
  docker rm craw-cli
fi

#check if image exists
if docker images | grep -q "craw-cli"; 
then
  docker rmi craw-cli
fi

#build the image
docker build -t craw-cli .

#start the container
docker run -d --name craw-cli craw-cli

#inspect the logs
docker logs -f craw-cli

