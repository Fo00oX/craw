# Craw - Webcrawler
The craw command line app provides useful functions for crawling websites analyzing the links they contain.

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

# Getting started

To get started use one of the following commands

```
craw -?
craw -h
craw --help
```

The output shows the available commands and options of craw. For detailed description please scroll to the **Commands**
and **Options** section. These options are available for every subcommand, so when you're stuck, try them!

```
craw [-hV] COMMAND
The craw command line app provides useful functions for crawling websites analyzing the links they contain.
  -h, -?, --help   Display this help and exit
  -V, --version    Print version information and exit
Commands:
  page                 Fetch a list of all Links present on a Webpage.
  check                Check a page for broken links.
  generate-completion  Generate bash/zsh completion script for craw.
```

# How to run

There are two ways to run craw:

- Gradle script
- Docker

## Execution with Gradle

### Build application with gradle

The craw command line application is built using the included gradle wrapper.

To build the application, you can run the following command.

```
./gradlew build
```

This compiles the application into a zip-file and tarball containing both the application and launch scripts. These can
be found in the `craw-cli/build/distributions` folder. The zip files or tarball can be copied to a target machine and
extracted to install craw.

Examples:

```
craw --help
craw page --help
craw check --help
craw page https://github.com
craw page -o --yaml D:\Documents\output.yml https://github.com
```

### Execute directly with gradle

Alternatively, you can build and execute the source code directly:

```
./gradlew :craw-cli:run --args="commands/options/url"
```

With *--args* you can specify which command, options and url you want to pass to craw.

Examples:

```
./gradlew :craw-cli:run --args="--help"
./gradlew :craw-cli:run --args="page https://github.com"
```

## Execution with Docker

### Start script

The `start.sh` (MacOS & Linux) and `start.ps1` (Windows) enable the simple management of the application container.

Whenever there is no image and container present the image is being **built** and **started** within a corresponding
docker container.

If the container is already running, the image is going to be **rebuild** and a new container is being deployed. Keep in
mind, that the image does not self-update! To rebuild the image, call the start script like
this: ```start.sh --rebuild``` or ```start.ps1 --rebuild```.

In case the image is already present and the container has exited or has been removed, a new container with the existing
image is being **started**.

#### Usage on MacOS / Linux:
```
./start.sh [OPTION]

Arguments (optional):
--rebuild   removes the image and redeploys a new container with a new image
--build     builds the craw-cli image
--run       launches a non-existent craw-cli container from a pre-built image
--start     starts an exited (existing) craw-cli container
--stop      stops the craw-cli container
--clean     stops the container and removes the container
--cleanup   stops the container, removes the container and removes the image
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
--stop      stops the craw-cli container
--clean     stops the container and removes the container
--cleanup   stops the container, removes the container and removes the image
--help      prints a help message
```

### Craw-Wrapper

The `craw-wrapper` script is used to forward commands to the application container. 

#### Examples on MacOS / Linux:

```
./craw-wrapper --help
./craw-wrapper page --help
./craw-wrapper check --help
./craw-wrapper page https://github.com
./craw-wrapper check https://google.com
./craw-wrapper page -y home/user/Documents/output.yml https://github.com
./craw-wrapper check -yo home/user/Documents/output https://github.com
./craw-wrapper page -j home/user/Documents/output.json https://github.com
./craw-wrapper page -jo home/user/Documents/output https://github.com
```

#### Examples on Windows:

```
./craw-wrapper --help
./craw-wrapper page --help
./craw-wrapper check --help
./craw-wrapper page https://github.com
./craw-wrapper check https://google.com
./craw-wrapper page -y D:\Documents\output.yml https://github.com
./craw-wrapper check -yo D:\Documents\output https://github.com
./craw-wrapper page -j D:\Documents\output.json https://github.com
./craw-wrapper page -jo D:\Documents\output https://github.com
```

## Peer review guidelines

Please use GitHub issues for feedback. English is the preferred language.

## Authors

Christoph Thonhauser [chranditho]  
Denis Radovanovic [SycSin]  
Gerald Wilding    
Stefan Schindler    
Zsolt Zombori [tacsi566]
