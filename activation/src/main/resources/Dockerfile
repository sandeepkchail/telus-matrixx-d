FROM openjdk:17-jdk-alpline
ARG JAR_FILE=target/*.jar
COPY ./target/activation-0.0.1.jar activation.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]