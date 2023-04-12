#FROM ubuntu:latest
#LABEL authors="root326"
#
#ENTRYPOINT ["top", "-b"]
FROM openjdk:17
COPY Deployable/todo-backend-0.0.1-SNAPSHOT.jar todo-backend-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","UserFinalTaskBoot-0.0.1-SNAPSHOT.jar"]
#CMD ["java", "-jar", "todo-backend-0.0.1-SNAPSHOT.jar"]