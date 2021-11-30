#Build-Stage 0
FROM openjdk:11 AS BUILD_IMAGE

COPY . /app

#remove existing artifacts and rebuild the project
WORKDIR /app
RUN ./gradlew clean build

#Build-Stage 1
FROM openjdk:11-jre
COPY --from=BUILD_IMAGE /app/craw-cli/build/distributions/craw-cli-1.0-SNAPSHOT.tar .
RUN tar -xf craw-cli-1.0-SNAPSHOT.tar && rm craw-cli-1.0-SNAPSHOT.tar

WORKDIR craw-cli-1.0-SNAPSHOT/bin
ENTRYPOINT ["/bin/bash","craw-cli"]

