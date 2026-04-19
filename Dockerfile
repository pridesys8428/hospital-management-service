FROM openjdk:17-oracle
WORKDIR /app
COPY target/*.jar hospitalmanagement.jar
EXPOSE 8089
ENTRYPOINT ["java", "-jar", "hospitalmanagement.jar"]
