FROM openjdk:17-ea-11-jdk-slim
VOLUME /tmp
COPY build/libs/*.jar post_service.jar
ENTRYPOINT ["java", "-jar", "post_service.jar"]
