# Step 1: Build Stage
FROM maven:3.8.6-openjdk-17-slim AS builder

# Set the working directory inside the container
WORKDIR /app

# Copy the pom.xml and source code into the container
COPY pom.xml /app/
COPY src /app/src

# Install dependencies and package the application (create the .war file)
RUN mvn clean package -DskipTests

# Step 2: Final Stage (Runtime)
FROM tomcat:9-jdk17-slim

# Set the working directory inside the container
WORKDIR /usr/local/tomcat/webapps

# Copy the WAR file from the build stage
COPY --from=builder /app/target/gu.war /usr/local/tomcat/webapps/gu.war

# Expose the Tomcat port (8080)
EXPOSE 8080

# Run Tomcat server
CMD ["catalina.sh", "run"]
