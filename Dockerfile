# Use a base image with Java
FROM openjdk:17-jdk-alpine

# Add a volume pointing to /tmp
VOLUME /tmp

# The application's JAR file
ARG JAR_FILE=target/Movies-Library2-0.0.1-SNAPSHOT.jar

# Add the application's JAR file to the container
ADD ${JAR_FILE} Movies-Library2-0.0.1-SNAPSHOT.jar

# Expose
EXPOSE 8080

# Run the JAR file
ENTRYPOINT ["java", "-jar", "/Movies-Library2-0.0.1-SNAPSHOT.jar"]
