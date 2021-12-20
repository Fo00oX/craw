# Craw - Webcrawler

## Sources

- [Tutorial on setting up a multi-module gradle project](https://docs.gradle.org/current/samples/sample_building_java_applications_multi_project.html)

## Docker
In order to enable the possibility of having the **craw-cli** application running on any system independently, it is recommended to use Docker for the deployments.

### Dockerfile
There is a `Dockerfile` included within the root of the project folder which is required in order to build the Docker image.

### Start scripts
The `start.sh` (MacOS & Linux) and `start.ps1` (Windows) scripts are useful in order to start the application container.

#### Usage on MacOS/Linux:
```bash
./start.sh
```

#### Usage on Windows:
```powershell
.\start.ps1
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
