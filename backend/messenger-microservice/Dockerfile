FROM maven:3.8.1-ibmjava-8-alpine AS appBuild

WORKDIR /usr/src/messenger-microservice
COPY . .
RUN ["mvn", "package", "-DskipTests"]

FROM openjdk:8-jdk-alpine AS appRuntime
WORKDIR /usr/src/messenger-microservice
COPY --from=appBuild /usr/src/messenger-microservice/target/messenger-microservice-0.0.1-SNAPSHOT.jar ./
EXPOSE 3034
CMD ["java", "-jar", "messenger-microservice-0.0.1-SNAPSHOT.jar"]
