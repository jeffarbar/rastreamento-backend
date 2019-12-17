FROM openjdk:8-jdk-alpine
RUN mkdir /app
COPY target/*.jar /app/send.jar
WORKDIR /app
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "send.jar"]