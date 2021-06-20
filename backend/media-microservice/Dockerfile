FROM maven:3.8.1-ibmjava-8-alpine AS appBuild

WORKDIR /usr/src/media-microservice
COPY . .
COPY ./src/main/resources/user-photos .
RUN ["mvn", "package", "-DskipTests"]

FROM openjdk:8-jdk-alpine AS appRuntime
WORKDIR /usr/src/media-microservice
COPY --from=appBuild /usr/src/media-microservice/target/media-microservice-0.0.1-SNAPSHOT.jar ./
EXPOSE 3033
CMD ["java", "-jar", "media-microservice-0.0.1-SNAPSHOT.jar"]
