# Start with a base image containing Java runtime
FROM maven

# Add Maintainer Info
LABEL maintainer="saharpilevar@gmail.com"
COPY src ./src
COPY pom.xml ./
WORKDIR ./
#building the project
RUN mvn clean
RUN  mvn install
# Make port 8080 available to the world outside this container
EXPOSE 8080

# Run the jar file
ENTRYPOINT ["java","-jar","./target/ElectionManager-1.0-SNAPSHOT.jar"]