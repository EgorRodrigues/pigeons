FROM openjdk:8-alpine
MAINTAINER Your Name <you@example.com>

ADD target/pigeons-0.0.1-SNAPSHOT-standalone.jar /pigeons/app.jar

EXPOSE 8080

CMD ["java", "-jar", "/pigeons/app.jar"]
