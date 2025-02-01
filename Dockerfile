# Step 1: Build Stage
FROM maven:3.8.6-openjdk-17-slim AS builder

# Set the working directory inside the container
WORKDIR /app

# Copy the pom.xml and source code into the container
COPY pom.xml /app/
COPY src /app/src

# Install dependencies and package the application (create the JAR)
RUN mvn clean package -DskipTests

# Step 2: Final Stage (Runtime)
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=builder /app/target/ecom-1.0-SNAPSHOT.jar /app/ecom.jar

# Expose the port your app is running on (e.g., 8080 for most Spring Boot apps)
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "/app/ecom.jar"]
