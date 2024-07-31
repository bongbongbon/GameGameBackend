FROM bellsoft/liberica-openjdk-alpine:17


CMD ["./gradlew", "clean", "build"]


VOLUME /tmp

ARG JAR_FILE=build/libs/*.jar

# Copy the application files into the container
COPY ./config/application.yml /app/config/application.yml

COPY ${JAR_FILE} app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app.jar"]