

# Craw - Webcrawler

Craw is a CLI application designed to fetch the HTML from a link, parse it and get the links found in the HTML. Craw can check if links are broken and output the result to a YAML or JSON file.

# Getting started

To get started use one of the following commands

```
craw -?
craw -h
craw --help
```

The output should be the available commands of craw. For detailed description please scroll to the **Commands** and **Options** section.

```
craw [-hV] COMMAND
The craw command line app provides useful functions for crawling websites
analyzing the links they contain.
  -h, -?, --help   Display this help and exit
  -V, --version    Print version information and exit
Commands:
  page                 Fetch a list of all Links present on a Webpage.
  check                Check a page for broken links.
  generate-completion  Generate bash/zsh completion script for craw.
```

# How to run

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

You can use this command to run craw

```
./gradlew :craw-cli:run --args="commands/options/url"
```

With *--args* you can specify which command, options and url you want to pass to craw.

### Usage:

```
./gradlew :craw-cli:run --args="--help"
./gradlew :craw-cli:run --args="page --help"
./gradlew :craw-cli:run --args="check --help"
./gradlew :craw-cli:run --args="page https://github.com"
./gradlew :craw-cli:run --args="page -y D:\Documents\output.yml https://github.com"

```

## Commands

**craw check**
```
craw check [-ho] [-j=jsonFile]... [-y=yamlFile]... url
Check a page for broken links.
url        The URL to analyze.
```

**craw page**
```
craw page [-ho] [-j=jsonFile]... [-y=yamlFile]... url
Fetch a list of all links present on a webpage.
url        The URL to analyze.
```

## Options

The two commands of craw support the same options. These are as follows:

```
  -h, -?, --help        Display this help and exit
  -j, --json=jsonFile   Output the collected links to a specified JSON file.
                          Automatically adds the .json file ending if it is not
                          specified already. Can be specified more than once.
  -o, --overwrite       By default, files are not overwritten if a specified
                          output file already exists. By specifying this
                          option, existing files will be overwritten if
                          required.
  -y, --yml, --yaml=yamlFile
                        Output the collected links to a specified YAML file.
                          Automatically adds the .yml file ending if it is not
                          specified already. Can be specified more than once.
```


## Peer review guidelines

Please use GitHub issues for feedback. English is the preferred language.

## Authors

Christoph Thonhauser  
Denis Radovanovic  
G. W.  
Stefan Schindler  
Zsolt Zombori
