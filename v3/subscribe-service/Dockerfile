FROM openjdk:17-ea-11-jdk-slim
VOLUME /tmp
COPY build/libs/*.jar subscribe_service.jar
ENTRYPOINT ["java", "-jar", "subscribe_service.jar"]
