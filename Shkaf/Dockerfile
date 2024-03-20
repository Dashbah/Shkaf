# Stage 1: Build the application with Gradle
FROM gradle:8.6 AS builder
WORKDIR /app
COPY . /app/.
RUN gradle build -x test

# Stage 2: Run the application with OpenJDK 21
FROM openjdk:21
WORKDIR /app
COPY --from=builder /app/build/libs/*.jar /app/*.jar
EXPOSE 8090
ENTRYPOINT ["java", "-jar", "/app/*.jar"]