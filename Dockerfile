# syntax=docker/dockerfile:1
FROM eclipse-temurin:21-jdk-jammy AS build

WORKDIR /malanka-build

COPY --chmod=777 . .

RUN ./gradlew bootJar

FROM eclipse-temurin:21-jre-jammy@sha256:d1c536be5ba42ea6d793b8eb67b8ced61fc66ae2c168d6c612113ebca661dd96 AS final

WORKDIR /malanka-app

COPY --from=build /malanka-build/build/libs/*.jar spring-boot.jar

EXPOSE 8080

ENTRYPOINT [ "java", "-jar", "spring-boot.jar" ]
