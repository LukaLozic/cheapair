
# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the executable JAR file and any other necessary files from the build context into the container
COPY ./target/CheapAir-1.1.1-SNAPSHOT.jar CheapAir.jar

# Specify the command to run when the container starts
CMD ["java", "-jar", "CheapAir.jar"]
