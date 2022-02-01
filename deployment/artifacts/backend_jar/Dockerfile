FROM openjdk:11
COPY ./backend_jar .
ENTRYPOINT ["java", "-Dspring.profiles.active=prod",  "-jar", "/backend.jar"]