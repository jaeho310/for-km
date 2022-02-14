ARG BUILD_IMAGE=maven:3.8-jdk-11
ARG RUNTIME_IMAGE=openjdk:11-jdk-slim

#############################################################################################

#############################################################################################
###              Stage where Docker is building spring boot app using maven               ###
#############################################################################################
FROM ${BUILD_IMAGE} AS build

COPY src ./src
COPY frontend ./frontend
COPY pom.xml ./

RUN mvn -B clean package -DskipTests

#############################################################################################

#############################################################################################
### Stage where Docker is running a java process to run a service built in previous stage ###
#############################################################################################
FROM ${RUNTIME_IMAGE}

COPY --from=build /target/kmong-project-*.jar /app/service.jar

CMD ["java", "-jar", "/app/service.jar"]
#############################################################################################
