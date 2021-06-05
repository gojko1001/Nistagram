FROM maven:3.8.1-ibmjava-8-alpine AS appBuild

WORKDIR /usr/src/nistagram-monolith
COPY . .
RUN mvn package -DskipTests

FROM openjdk:8-jdk-alpine AS appRuntime
WORKDIR /app
COPY --from=appBuild /usr/src/nistagram-monolith/target/nistagram-monolith-0.0.1-SNAPSHOT.jar ./
EXPOSE 3030
CMD java -jar nistagram-monolith-0.0.1-SNAPSHOT.jar