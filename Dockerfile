FROM openjdk:11
MAINTAINER "Caldelas"
ADD target/aplazo-docker.jar aplazo-docker.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/aplazo-docker.jar"]