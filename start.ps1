function build {
  echo "Building craw-cli image ..."
  docker build -t craw-cli .
}

function run {
  echo "Running craw-cli container..."
  docker run -d --name craw-cli craw-cli
}

function startup {
  echo "Starting craw-cli container..."
  docker start craw-cli
}

function stop {
  echo "Stopping craw-cli container..."
  docker stop craw-cli
}

function cleanup {
  echo "Cleaning up the setup..."
  docker stop craw-cli
  docker rm craw-cli
  docker rmi craw-cli
}

function clean {
  echo "Cleaning the setup..."
  docker stop craw-cli
  docker rm craw-cli
}

function rebuild {
  echo "Removing craw-cli container..."
  docker stop craw-cli
  docker rm craw-cli
  echo "Removing craw-cli image..."
  docker rmi craw-cli
  build
  run
}

if ( $args.count -eq 0 ) {
  if (docker images | Select-String -Pattern "craw-cli") {
    if (!(docker ps -f status=running | Select-String -Pattern "craw-cli")) {
      if (!(docker ps -f status=exited | Select-String -Pattern "craw-cli")) {
        run
      }
      else {
        startup
      }
    }
    else {
      rebuild
    }
  }
  else{
    build
    run
  }
}

if ( $args[0] -eq "--rebuild") {
  rebuild
}

if ( $args[0] -eq "--build" ) {
  build
}

if ( $args[0] -eq "--run" ) {
  run
}

if ( $args[0] -eq "--start" ) {
  startup
}

if ( $args[0] -eq "--stop" ) {
  stop
}

if ( $args[0] -eq "--cleanup" ) {
  cleanup
}

if ( $args[0] -eq "--clean" ) {
  clean
}

if ( $args[0] -eq "--help" ) {
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
}

