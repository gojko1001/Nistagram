FROM maven:3.8.1-ibmjava-8-alpine AS appBuild

WORKDIR /usr/src/user-microservice
COPY . .
RUN ["mvn", "package", "-DskipTests"]

FROM openjdk:8-jdk-alpine AS appRuntime
WORKDIR /user-microservice
COPY --from=appBuild /usr/src/user-microservice/target/user-microservice-0.0.1-SNAPSHOT.jar ./
EXPOSE 3032
CMD java -jar user-microservice-0.0.1-SNAPSHOT.jar