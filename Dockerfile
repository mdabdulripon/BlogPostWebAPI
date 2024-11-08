# Use an official OpenJDK image as a base
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the Gradle wrapper and build files
COPY BlogPostWebAPI/gradlew .
COPY BlogPostWebAPI/gradle/ gradle/
COPY BlogPostWebAPI/build.gradle .
COPY BlogPostWebAPI/settings.gradle .

# Download the dependencies
RUN ./gradlew build -x test --parallel

# Copy the source code
COPY BlogPostWebAPI/src/ src/

# Build the application (avoiding tests for faster build time)
RUN ./gradlew clean build -x test

# Copy the built JAR file into the container
COPY BlogPostWebAPI/build/libs/*.jar BlogPostWebAPI.jar

# Expose the port your application runs on
EXPOSE 8088

# Run the application
ENTRYPOINT ["java", "-jar", "BlogPostWebAPI.jar"]
