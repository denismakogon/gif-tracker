FROM denismakogon/java-11-opencv-debian:4.1.0-runtime

# required by javacv
RUN apt-get update && apt-get install --no-install-recommends -qy libgtk2.0
ADD giphy.gif /giphy.gif
ADD target/gif-processor-1.0-SNAPSHOT.jar /gif-processor-1.0-SNAPSHOT.jar

ENTRYPOINT ["java", "-cp", "/gif-processor-1.0-SNAPSHOT.jar", "com.giphy.app.App", "/giphy.gif"]
