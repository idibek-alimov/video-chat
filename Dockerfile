FROM openjdk:17
ARG JAR_FILE=./target/*.jar
EXPOSE 8080
COPY ./target/video-chat.jar /home/run/video-chat.jar
ENTRYPOINT ["java","-jar","/home/run/video-chat.jar"]