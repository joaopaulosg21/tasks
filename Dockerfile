FROM eclipse-temurin:17

RUN mkdir /opt/app

COPY ./target/tasks-0.0.1-SNAPSHOT.jar /opt/app

CMD ["java", "-jar", "/opt/app/tasks-0.0.1-SNAPSHOT.jar"]