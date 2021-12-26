# Craw - Webcrawler

## Sources

- [Tutorial on setting up a multi-module gradle project](https://docs.gradle.org/current/samples/sample_building_java_applications_multi_project.html)

## Docker
In order to enable the possibility of having the **craw-cli** application running on any system independently, it is recommended to use Docker for the deployments.

### Dockerfile
There is a `Dockerfile` included within the root of the project folder which is required in order to build the Docker image.

### Start script
The `start.sh` (MacOS & Linux) and `start.ps1` (Windows) scripts are useful in order to manage the application container.

Whenever there is no image and container present the image is being **built** and **started** within a corresponding docker container. 

If the container is already running, the image is going to be **rebuild** and a new container is being deployed.

In case the image is already present and the container has exited or has been removed, a new container with the existing image is being **started**.

#### Usage on MacOS / Linux:
```bash
./start.sh <arg>

args (optional):
--rebuild   removes the image and redeploys a new container with a new image
--build     builds the craw-cli image
--run       launches a non-existent craw-cli container from a pre-built image
--start     starts an exited (existing) craw-cli container
--help      prints a help message
```

#### Usage on Windows:
```powershell
.\start.ps1 <arg>


args (optional):
--rebuild   removes the image and redeploys a new container with a new image
--build     builds the craw-cli image
--run       launches a non-existent craw-cli container from a pre-built image
--start     starts an exited (existing) craw-cli container
--help      prints a help message
```

### Craw-Wrapper

In order to communicate with the dockerized application the `craw-wrapper` is required which translates all CLI arguments from the host system into the container and returns the stdout of the container.

#### Usage on MacOS/Linux:
```bash
./craw-wrapper foo bar
```

#### Usage on Windows:
```powershell
.\craw-wrapper.ps1 foo bar
```
