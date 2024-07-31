# Base image
FROM openjdk:17-jdk-alpine

# Create a directory for the application
WORKDIR /app

# Copy the JAR file and application.yml
COPY build/libs/auth-service.jar app.jar
COPY src/main/resources/application.yml /config/application.yml

# Define the entrypoint
ENTRYPOINT ["java", "-jar", "/app.jar"]

# Expose the port
EXPOSE 8083