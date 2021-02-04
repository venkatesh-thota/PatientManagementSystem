FROM openjdk:11-jre-slim
MAINTAINER "Docker App <docker@app.com>"
WORKDIR /PatientManagementSystem

#COPY PatientManagement/target/PatientManagement-0.0.1-SNAPSHOT.jar ./app.jar
ENTRYPOINT ["java", "-jar", "/PatientManagementSystem/PatientManagement-0.0.1-SNAPSHOT.jar"]

EXPOSE 8080
