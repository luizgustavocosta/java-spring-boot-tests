FROM eclipse-temurin:21-alpine
VOLUME /tmp
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} tropicalflix.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/tropicalflix.jar"]