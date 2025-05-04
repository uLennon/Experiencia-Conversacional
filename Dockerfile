FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

COPY target/chatbot-0.0.1-SNAPSHOT.jar chatbot.jar

EXPOSE 8080