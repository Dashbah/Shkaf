FROM openjdk:17
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
CMD ["java", "Main"]
ENTRYPOINT ["java","-jar","/app.jar"]