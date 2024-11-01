# Use an official OpenJDK image as a base
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the Gradle wrapper and build files
COPY BlogWebAPI/gradlew .
COPY BlogWebAPI/gradle/ gradle/
COPY BlogWebAPI/build.gradle .
COPY BlogWebAPI/settings.gradle .

# Download the dependencies
RUN ./gradlew build -x test --parallel

# Copy the source code
COPY BlogWebAPI/src/ src/

# Build the application (avoiding tests for faster build time)
RUN ./gradlew clean build -x test

# Copy the built JAR file into the container
COPY BlogWebAPI/build/libs/*.jar blogwebapi.jar

# Expose the port your application runs on
EXPOSE 8088

# Run the application
ENTRYPOINT ["java", "-jar", "blogwebapi.jar"]
