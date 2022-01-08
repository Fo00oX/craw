

# Craw - Webcrawler

Craw is a CLI application designed to fetch the HTML from a link, parse it and get the links found in the HTML. Craw can check if links are broken and output the result to a YAML or JSON file.

# Getting started


## How to run

There are two ways to run craw:

 - Docker
 - Gradle script

## Execution with Docker

In order to enable the possibility of having the **craw-cli** application running on any system independently, it is possible to use Docker for the deployments.

### Start script
The `start.sh` (MacOS & Linux) and `start.ps1` (Windows) scripts are useful in order to manage the application container.

Whenever there is no image and container present the image is being **built** and **started** within a corresponding docker container. 

If the container is already running, the image is going to be **rebuild** and a new container is being deployed. Keep in mind, that the image does not self-update! The update can be done by removing the image on your system and re-running the start-script.

In case the image is already present and the container has exited or has been removed, a new container with the existing image is being **started**.

#### Usage on MacOS / Linux:
```
./start.sh [OPTION]

Arguments (optional):
--rebuild   removes the image and redeploys a new container with a new image
--build     builds the craw-cli image
--run       launches a non-existent craw-cli container from a pre-built image
--start     starts an exited (existing) craw-cli container
--help      prints a help message
```

#### Usage on Windows:
```
.\start.ps1 [OPTION]

Arguments (optional):
--rebuild   removes the image and redeploys a new container with a new image
--build     builds the craw-cli image
--run       launches a non-existent craw-cli container from a pre-built image
--start     starts an exited (existing) craw-cli container
--help      prints a help message
````

### Craw-Wrapper

In order to communicate with the dockerized application the `craw-wrapper` is required which translates all CLI arguments from the host system into the container and returns the stdout of the container.

#### Usage on MacOS / Linux:
```
./craw-wrapper craw --help
./craw-wrapper craw page --help
./craw-wrapper craw check --help
./craw-wrapper craw page https://github.com
./craw-wrapper craw check https://google.com
./craw-wrapper craw page -y home/user/Documents/output.yml https://github.com
./craw-wrapper craw check -yo home/user/Documents/output https://github.com
./craw-wrapper craw page -j home/user/Documents/output.json https://github.com
./craw-wrapper craw page -jo home/user/Documents/output https://github.com
```

#### Usage on Windows:
```
./craw-wrapper craw --help
./craw-wrapper craw page --help
./craw-wrapper craw check --help
./craw-wrapper craw page https://github.com
./craw-wrapper craw check https://google.com
./craw-wrapper craw page -y D:\Documents\output.yml https://github.com
./craw-wrapper craw check -yo D:\Documents\output https://github.com
./craw-wrapper craw page -j D:\Documents\output.json https://github.com
./craw-wrapper craw page -jo D:\Documents\output https://github.com
```


## Execution with Gradle

## Commands

## Options


## Peer review guidelines

Please use GitHub issues for feedback. English is the preferred language.

## Authors

Christoph Thonhauser  
Denis Radovanovic  
G. W.  
Stefan Schindler  
Zsolt Zombori

