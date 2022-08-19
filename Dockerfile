FROM openjdk:11
MAINTAINER vachan
COPY target/employee-service-1.0.0.jar employee-service.jar
ENTRYPOINT ["java","-jar","/employee-service.jar"]