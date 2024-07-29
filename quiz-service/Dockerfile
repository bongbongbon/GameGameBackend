FROM --platform=linux/amd64 openjdk:17-alpine
VOLUME /tmp
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
COPY src/main/resources/application.yml /config/application.yml
ENTRYPOINT ["java", "-jar", "/app.jar", "--spring.config.location=file:/config/application.yml"]