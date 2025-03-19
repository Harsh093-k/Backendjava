# Use an official Java runtime as a parent image
FROM openjdk:17-jdk-slim as build

# Copy the Maven project POM file
COPY . .
# Download dependencies (this will cache the dependencies if unchanged)
RUN mvn clear package -DskipTests

# Copy the entire project into the working directory
COPY . .

# Build the Spring Boot application (package it into a JAR file)
RUN mvn clean package -DskipTests

# Use a smaller base image to run the app (from OpenJDK 17)
FROM openjdk:17-jdk-slim



# Copy the JAR file built in the previous stage to the container
COPY --from=build /app/target/demo-0.0.1-SNAPSHOT.jar /app/demo.jar

# Expose port 8080 (Spring Boot default port)
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "/app/demo.jar"]
