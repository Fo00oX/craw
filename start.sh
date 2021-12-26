#!/bin/bash
function build () {
  echo "Building craw-cli image ..."
  docker build -t craw-cli .
}

function run () {
  echo "Running craw-cli container..."
  docker run -d --name craw-cli craw-cli
}

function start() {
  echo "Starting craw-cli container..."
  docker start craw-cli
}

function rebuild () {
  echo "Removing craw-cli container..."
  docker stop craw-cli
  docker rm craw-cli
  echo "Removing craw-cli image..."
  docker rmi craw-cli 
  build
  run
}

if [ $# -eq 0 ]
then
  if (docker images | grep "craw-cli");
  then
    if ! (docker ps -f status=running | grep -q "craw-cli")
    then
      if ! (docker ps -f status=exited | grep -q "craw-cli");
      then
        run
      else
        start
      fi
    else
      rebuild
    fi
  else
    build
    run
  fi
fi

if [ "$1" = "--rebuild" ]
then
  rebuild
fi

if [ "$1" = "--build" ]
then
  build
fi

if [ "$1" = "--run" ]
then
  run
fi

if [ "$1" = "--start" ]
then
  start
fi

if [ "$1" = "--help" ]
then
  echo "Usage: ./start.sh <arg>

args (optional):
--rebuild   removes the image and redeploys a new container with a new image
--build     builds the craw-cli image
--run       launches a non-existent craw-cli container from a pre-built image 
--start     starts an exited (existing) craw-cli container 
--help      prints a help message"
fi
