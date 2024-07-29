# OpenJDK 17을 기반으로 하는 Docker 이미지 사용
FROM --platform=linux/amd64 openjdk:17-alpine

# /tmp 디렉토리를 데이터 볼륨으로 설정
VOLUME /tmp

# JAR 파일 복사
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar

# 환경 변수 설정
ENV SPRING_DATASOURCE_URL=jdbc:mysql://gamegamemsa.c72auqk86wix.ap-northeast-2.rds.amazonaws.com:3306/gamegame?serverTimezone=Asia/Seoul
ENV SPRING_DATASOURCE_USERNAME=admin
ENV SPRING_DATASOURCE_PASSWORD=Gamegame1!
ENV SPRING_DATASOURCE_DRIVER_CLASS_NAME=com.mysql.cj.jdbc.Driver

# 컨테이너 시작 시 실행할 명령을 설정
ENTRYPOINT ["java","-jar","/app.jar"]