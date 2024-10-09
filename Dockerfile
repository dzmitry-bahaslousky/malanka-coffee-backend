# syntax=docker/dockerfile:1
FROM eclipse-temurin:21-jre-jammy@sha256:d1c536be5ba42ea6d793b8eb67b8ced61fc66ae2c168d6c612113ebca661dd96

WORKDIR /malanka-app

COPY /build/libs/*.jar spring-boot.jar

EXPOSE 8080

ENTRYPOINT [ "java", "-jar", "spring-boot.jar" ]
