#!/usr/local/bin/pwsh

#check if container exists
if (docker ps -a | Select-String -Pattern "craw-cli") {
  docker stop craw-cli
  docker rm craw-cli
}

#check if image exists
if (docker images | Select-String -Pattern "craw-cli") {
  docker rmi craw-cli
}

#build the image
docker build -t craw-cli .

#start the container
docker run -d --name craw-cli craw-cli

#inspect the logs
docker logs -f craw-cli

