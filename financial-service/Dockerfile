FROM eclipse-temurin:21-alpine
VOLUME /tmp
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} finance-service.jar
ENTRYPOINT ["java","-jar","/finance-service.jar"]