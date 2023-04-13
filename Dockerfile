# Use an official Node.js runtime as a parent image
FROM node:18-alpine AS react-build
# RUN curl -fsSLO https://get.docker.com/builds/Linux/x86_64/docker-17.04.0-ce.tgz \
#   && tar xzvf docker-17.04.0-ce.tgz \
#   && mv docker/docker /usr/local/bin \
#   && rm -r docker docker-17.04.0-ce.tgz


# Set the working directory to /app
WORKDIR /app

# Copy the package.json and package-lock.json files to the container
COPY bookmycon-ui/package*.json ./

# Install dependencies
RUN npm install --legacy-peer-deps

# Copy the rest of the frontend code to the container
COPY bookmycon-ui/ ./

# Build the React app
RUN npm run build

# Use an official OpenJDK runtime as a parent image
FROM openjdk:11.0.11-jdk-slim AS spring-build

# Set the working directory to /app
WORKDIR /app

# Copy the packaged React app to the container
COPY --from=react-build /app/build ./src/main/resources/static/

# Copy the Spring Boot project files to the container
COPY . .

# Build the Spring Boot app
RUN ./mvnw clean package 

# Expose the port that the app will run on
EXPOSE 8080

# Run the Spring Boot app
CMD ["java", "-jar", "target/my-app.jar"]
