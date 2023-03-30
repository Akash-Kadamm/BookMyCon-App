# Use an official Node.js runtime as a parent image
FROM node:14.17-alpine AS react-build

# Set the working directory to /app
WORKDIR /app

# Copy the package.json and package-lock.json files to the container
COPY frontend/package*.json ./

# Install dependencies
RUN npm install --silent

# Copy the rest of the frontend code to the container
COPY frontend/ ./

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
RUN ./mvnw package -DskipTests

# Expose the port that the app will run on
EXPOSE 8080

# Run the Spring Boot app
CMD ["java", "-jar", "target/my-app.jar"]