# 🏗️ Multi-Stage Build
FROM maven:3.8.4-openjdk-17 AS builder
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# 🎯 Production Image
FROM openjdk:17-alpine
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar

# 🔒 Secure Secrets & Environment Variables
ENV MONGO_URI=${MONGO_URI}

# 🩺 Health Check
HEALTHCHECK --interval=30s --timeout=10s --start-period=10s \
  CMD curl --fail http://localhost:9090/health || exit 1

# 🚀 Run Microservice
CMD ["java", "-jar", "app.jar"]
