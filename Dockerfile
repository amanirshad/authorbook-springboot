FROM openjdk:17-jdk-alpine
MAINTAINER aman.irshad
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]