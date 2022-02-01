FROM openjdk:11
COPY ./taskrunner_jar .
ENTRYPOINT ["java",  "-Dspring.profiles.active=prod", "-jar", "/taskrunner.jar"]