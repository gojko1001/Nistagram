FROM maven:3.8.1-ibmjava-8-alpine AS appBuild

WORKDIR /usr/src/proxy-server
COPY . .
RUN ["mvn", "package", "-DskipTests"]

FROM openjdk:8-jdk-alpine AS appRuntime
#WORKDIR /proxy-server
COPY --from=appBuild /usr/src/proxy-server/target/proxy-server-0.0.1-SNAPSHOT.jar ./
EXPOSE 8762
CMD java -jar proxy-server-0.0.1-SNAPSHOT.jar