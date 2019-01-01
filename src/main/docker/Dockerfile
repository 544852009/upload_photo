FROM java:8
VOLUME /tmp
ADD http://luojie.com:8080/job/upload/lastSuccessfulBuild/artifact/target/upload-photo-0.0.1-SNAPSHOT.jar java.jar app.jar
RUN bash -c 'touch /app.jar'
EXPOSE 8080
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]