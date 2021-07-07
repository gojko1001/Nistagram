FROM maven:3.8.1-ibmjava-8-alpine AS appBuild

WORKDIR /usr/src/webshop
COPY . .
COPY ./src/main/resources/images .
RUN ["mvn", "package", "-DskipTests"]

FROM openjdk:8-jdk-alpine AS appRuntime
WORKDIR /usr/src/webshop
COPY --from=appBuild /usr/src/webshop/target/webshop-0.0.1-SNAPSHOT.jar ./
EXPOSE 3033
CMD ["java", "-jar", "webshop-0.0.1-SNAPSHOT.jar"]
