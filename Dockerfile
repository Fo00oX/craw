FROM gradle:7.3-jdk11

COPY . /app

#remove existing artifacts and rebuild the project
WORKDIR /app
RUN gradle clean build

#unarchive the bundled application
WORKDIR craw-cli/build/distributions
RUN tar -xf craw-cli-1.0-SNAPSHOT.tar

#execute the application
WORKDIR craw-cli-1.0-SNAPSHOT/bin
ENTRYPOINT ["/bin/bash","craw-cli"]