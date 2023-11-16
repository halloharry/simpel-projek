# Use the official Maven image with OpenJDK 17 as the base image
FROM maven:3.8-openjdk-17-slim

# Set the working directory in the container
WORKDIR /usr/src/app

# Copy the Maven project file
COPY pom.xml .

# Download the dependencies
RUN mvn dependency:go-offline

# Copy the application source code
COPY src ./src

# Build the application
RUN mvn package

# Set the entry point for the application
CMD ["java", "-jar", "target/*.jar"]
