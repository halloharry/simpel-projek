# Use the official Ubuntu 20.04 base image
FROM ubuntu:latest

# Set the working directory to /app
WORKDIR /app

# Install OpenJDK 17
RUN apt-get update && apt-get install -y openjdk-17-jdk

# Install Maven
RUN apt-get install -y maven

# Copy the current directory contents into the container at /app
COPY . /app

# Build the application with Maven
RUN mvn clean install

# Specify the default command to run when the container starts
CMD ["java", "-jar", "target/*.jar"]
