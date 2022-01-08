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

function stop(){
  echo "Stopping craw-cli container..."
  docker stop craw-cli
}

function cleanup(){
  echo "Cleaning up the setup..."
  docker stop craw-cli
  docker rm craw-cli
  docker rmi craw-cli
}

function clean(){
  echo "Cleaning the setup..."
  docker stop craw-cli
  docker rm craw-cli
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

if [ "$1" = "--stop" ]
then
  stop
fi

if [ "$1" = "--cleanup" ]
then
  cleanup
fi

if [ "$1" = "--clean" ]
then
  clean
fi

if [ "$1" = "--help" ]
then
  echo "Usage: ./start.sh [OPTION]

Arguments (optional):
--rebuild   removes the image and redeploys a new container with a new image
--build     builds the craw-cli image
--run       launches a non-existent craw-cli container from a pre-built image 
--start     starts an exited (existing) craw-cli container 
--stop      stops the craw-cli container
--clean     stops the container and removes the container
--cleanup   stops the container, removes the container and removes the image
--help      prints a help message"
fi
