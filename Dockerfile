#Build-Stage 0
FROM openjdk:11 AS BUILD_IMAGE

COPY . /app

WORKDIR /app

#remove existing artifacts and rebuild the project
RUN ./gradlew clean build

RUN tar -xf craw-cli/build/distributions/craw-cli-1.0-SNAPSHOT.tar 

#Build-Stage 1
FROM openjdk:11-jre
COPY --from=BUILD_IMAGE /app/craw-cli-1.0-SNAPSHOT /craw-cli-1.0-SNAPSHOT/

#create user 'craw'
RUN useradd craw --home-dir /craw-cli-1.0-SNAPSHOT/bin

#change user from 'root' to 'craw'
USER craw

#add application folder to $PATH
ENV PATH="/craw-cli-1.0-SNAPSHOT/bin:$PATH"

#Run the application infinitely
ENTRYPOINT ["tail", "-f", "/dev/null"]
