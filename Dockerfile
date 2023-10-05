# Use an official OpenJDK runtime as a parent image
FROM amazoncorretto:17-alpine-jdk

# Set the working directory in the container
WORKDIR /app

# Copy the Spring Boot JAR file into the container
COPY target/Employee-service.jar /app/app.jar

# Expose the port your Spring Boot app runs on
EXPOSE 9501

# Command to run your application
CMD ["java", "-jar", "app.jar"]