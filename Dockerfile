FROM amazoncorretto:17-alpine-jdk
COPY target/employees-0.0.1-SNAPSHOT.jar-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]