FROM openjdk:8
EXPOSE 8080
ADD target/jwt-0.0.1-SNAPSHOT.jar dockerimage.jar
ENTRYPOINT [ "java","-jar","dockerrimage.jar" ]