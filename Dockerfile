# Step 1: Build Stage
FROM maven:3.8-amazoncorretto-8-debian AS builder

# Set the working directory inside the container
WORKDIR /app

# Copy the pom.xml and source code into the container
COPY pom.xml /app/
COPY src /app/src

# Install dependencies and package the application (create the .war file)
RUN mvn clean package -DskipTests

# Step 2: Final Stage (Runtime)
FROM tomcat:9-jre8-slim

# Remove the default ROOT application
RUN rm -rf /usr/local/tomcat/webapps/*

# Copy the WAR file from the build stage and rename it to ROOT.war
COPY --from=builder /app/target/gu.war /usr/local/tomcat/webapps/ROOT.war

# Expose the Tomcat port (8080)
EXPOSE 8080

# Run Tomcat server
CMD ["catalina.sh", "run"]