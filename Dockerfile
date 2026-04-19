FROM openjdk:17-oracle
WORKDIR /app
COPY target/hospital-management-0.0.1-SNAPSHOT.jar app/hospital-management-0.0.1-SNAPSHOT.jar
EXPOSE 8089
ENTRYPOINT ["java", "-jar", "hospital-management-0.0.1-SNAPSHOT.jar"]
