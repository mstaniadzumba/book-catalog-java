#Use an official OpenJDK runtime as a base image
FROM eclipse-temurin:17-jre-alpine

#set a working directory in the container
WORKDIR /app

#COpy the packaged JAR file into the container
COPY target/book-catalog-1.0-SNAPSHOT.jar app.jar

#Run the jar file when the container starts
ENTRYPOINT ["java", "-jar", "app.jar"]