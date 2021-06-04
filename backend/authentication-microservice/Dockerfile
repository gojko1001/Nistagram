FROM maven:3.8.1-ibmjava-8-alpine AS appBuild

WORKDIR /usr/src/authentication-microservice
COPY . .
RUN mvn package -DskipTests

FROM openjdk:8-jdk-alpine AS appRuntime
WORKDIR /app
COPY --from=appBuild /usr/src/authentication-microservice/target/authentication-microservice-0.0.1-SNAPSHOT.jar ./
EXPOSE 3031
CMD java -jar authentication-microservice-0.0.1-SNAPSHOT.jar