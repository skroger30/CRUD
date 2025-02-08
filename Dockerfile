FROM eclipse-temurin:17

LABEL mentainer "jksourabh30@gmail.com"

WORKDIR /app

COPY target/TestProject-3-0.0.1-SNAPSHOT.war /app/spring-boot-docker-demo.jar

ENTRYPOINT ["java", "-jar", "spring-boot-docker-demo.jar" ]