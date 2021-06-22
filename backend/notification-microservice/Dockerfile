FROM maven:3.8.1-ibmjava-8-alpine AS appBuild

WORKDIR /usr/src/notification-microservice
COPY . .
RUN ["mvn", "package", "-DskipTests"]

FROM openjdk:8-jdk-alpine AS appRuntime
WORKDIR /notification-microservice
COPY --from=appBuild /usr/src/notification-microservice/target/notification-microservice-0.0.1-SNAPSHOT.jar ./
EXPOSE 3032
CMD java -jar notification-microservice-0.0.1-SNAPSHOT.jar