#FROM ubuntu:latest
#LABEL authors="root326"
#
#ENTRYPOINT ["top", "-b"]
CMD ["java", "-jar", "/Deployable/todo-backend-0.0.1-SNAPSHOT.jar"]