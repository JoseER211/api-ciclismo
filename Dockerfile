FROM openjdk:8-jre-alpine
COPY "./target/ciclismo-0.0.1-SNAPSHOT.jar" "/ciclismo.jar"
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/ciclismo.jar"]