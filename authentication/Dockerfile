# Start with a base image containing Java runtime
FROM amazoncorretto:17


# Add Maintainer Info
LABEL maintainer="HungNguyen"

# Add a volume pointing to /tmp
VOLUME /tmp

# Make port 8090 available to the world outside this container
EXPOSE 8097

# The application's jar file
ARG JAR_FILE=target/authentication-microservice-0.0.2-SNAPSHOT.jar

# Add the application's jar to the container
ADD ${JAR_FILE} authentication-microservice.jar

# Once the container starts, run below command - Run the jar file
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/authentication-microservice.jar"]
