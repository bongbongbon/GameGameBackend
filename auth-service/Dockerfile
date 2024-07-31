# 1. Gradle을 사용하여 소스 코드 빌드를 위한 이미지
FROM gradle:jdk17 as builder

# 소스 코드를 이미지에 복사
WORKDIR /app
COPY . .

# Gradle을 사용하여 애플리케이션 빌드
RUN gradle build

# 최종 이미지를 위한 OpenJDK 이미지
FROM openjdk:17-jdk

# 빌드된 JAR 파일을 복사
ARG JAR_FILE=./build/libs/nsfServer-0.0.1-SNAPSHOT.jar
COPY --from=builder /app/${JAR_FILE} app.jar
COPY ./src/main/resources/application.yml /app/config/application.yml

EXPOSE 8083

# 시스템 진입점 정의
ENTRYPOINT ["java","-jar","/app.jar"]