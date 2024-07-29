# Base image
FROM --platform=linux/amd64 openjdk:17-alpine

# Create directory for application configuration
RUN mkdir -p /config

# Set the location for the application jar and config
ARG JAR_FILE=build/libs/*.jar

# Copy the application jar into the container
COPY ${JAR_FILE} app.jar

# Copy the application.yml into the container
COPY src/main/resources/application.yml /config/application.yml

# Set the entry point for the application
ENTRYPOINT ["java", "-jar", "/app.jar", "--spring.config.location=file:/config/application.yml"]