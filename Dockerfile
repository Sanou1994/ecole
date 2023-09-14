# Use a Java base image
FROM openjdk:17-alpine
LABEL mentainer="arouna.sanou@nest.sn"
# Set the working directory to /app
WORKDIR /app
RUN mkdir /app/logs
RUN chmod 777 /app/logs
RUN mkdir /app/ressources
RUN chmod 777 /app/ressources
#expose app port
EXPOSE 8085
# create variable
ARG JAR_FILE=target/*.jar
# Copy the Spring Boot application JAR file into the Docker image
COPY  ${JAR_FILE} /app/ecole.jar
# Run the Spring Boot application when the container starts
ENTRYPOINT  ["java", "-jar", "ecole.jar"]