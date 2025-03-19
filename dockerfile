# Use an official Java runtime as a parent image
FROM openjdk:17-jdk-slim as build

# Set the working directory inside the container
WORKDIR /app

# Copy the Maven project POM file
COPY pom.xml .

# Download dependencies (this will cache the dependencies if unchanged)
RUN mvn dependency:go-offline

# Copy the entire project into the working directory
COPY src ./src

# Build the Spring Boot application (package it into a JAR file)
RUN mvn clean package -DskipTests

# Use a smaller base image to run the app (from OpenJDK 17)
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file built in the previous stage to the container
COPY --from=build /app/target/demo-0.0.1-SNAPSHOT.jar /app/demo.jar

# Expose port 8080 (Spring Boot default port)
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "/app/demo.jar"]
