FROM openjdk:11-jre-slim
MAINTAINER "Docker App <docker@app.com>"
WORKDIR /PatientManagementSystem-main

COPY ./PatientManagement/target/PatientManagement-0.0.1-SNAPSHOT.jar ./
ENTRYPOINT ["java", "-jar", "/PatientManagementSystem-main/PatientManagement-0.0.1-SNAPSHOT.jar"]

EXPOSE 8080
