ARG BUILD_IMAGE=maven:3.8-jdk-11
ARG RUNTIME_IMAGE=openjdk:11-jdk-slim

#############################################################################################

#############################################################################################
###              Stage where Docker is building spring boot app using maven               ###
#############################################################################################
FROM dependencies as build

COPY src ./src
COPY frontend ./frontend

RUN mvn -B clean package
#        -DproxySet=${PROXY_SET} \
#        -DproxyHost=${PROXY_HOST} \
#        -DproxyPort=${PROXY_PORT}

#############################################################################################

#############################################################################################
### Stage where Docker is running a java process to run a service built in previous stage ###
#############################################################################################
FROM ${RUNTIME_IMAGE}

COPY --from=build /target/iris-monitoring-*.jar /app/service.jar

CMD ["java", "-jar", "/app/service.jar"]
#############################################################################################