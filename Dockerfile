FROM openjdk:11

LABEL maintainer="coutomariel@gmail.com"
VOLUME /main-app

ADD /target/propostas*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar","/app.jar"]