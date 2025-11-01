# Use the Eclipse temurin alpine official image
# https://hub.docker.com/_/eclipse-temurin
FROM maven:3.9-eclipse-temurin-21

# Create and change to the app directory.
WORKDIR /app

# Copy local code to the container image.
COPY . ./

RUN mvn -N io.takari:maven:wrapper

# Build the app.
RUN chmod +x mvnw
RUN ./mvnw -DoutputFile=target/mvn-dependency-list.log -B -DskipTests clean dependency:list install

# Run the quarkus app 
CMD ["sh", "-c", "java -jar target/quarkus-app/quarkus-run.jar"]