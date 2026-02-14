# =====================
# Build stage
# =====================
FROM gradle:8.5-jdk21 AS build

WORKDIR /home/gradle/project

COPY gradlew .
COPY gradle gradle
COPY build.gradle.kts settings.gradle.kts ./
RUN gradle dependencies --no-daemon

COPY src src
RUN gradle bootJar --no-daemon

# =====================
# Runtime stage
# =====================
FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=build /home/gradle/project/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
