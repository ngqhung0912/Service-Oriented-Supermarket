# Start with a base image containing Java runtime
FROM amazoncorretto:17

# Add Maintainer Info
LABEL maintainer="HungNguyen"

# Add a volume pointing to /tmp
VOLUME /tmp

# Make port 8090 available to the world outside this container
EXPOSE 8098

# The application's jar file
ARG JAR_FILE=target/stock-microservices-0.0.1-SNAPSHOT.jar

# Add the application's jar to the container
ADD ${JAR_FILE} stock-microservices.jar

# Once the container starts, run below command - Run the jar file
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/stock-microservices.jar"]
