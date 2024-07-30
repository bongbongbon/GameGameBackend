# OpenJDK 17을 기반으로 하는 Docker 이미지 사용
FROM --platform=linux/amd64 openjdk:17-alpine
VOLUME /tmp

# 애플리케이션 JAR 파일 복사
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar

# 애플리케이션이 사용할 application.yml 파일을 컨테이너의 src/main/resources 디렉토리에 복사
COPY src/main/resources/application.yml /app/src/main/resources/application.yml

# JAR 파일 실행
ENTRYPOINT ["java", "-jar", "/app.jar"]