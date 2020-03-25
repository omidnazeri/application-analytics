FROM openjdk:8-jdk-alpine
ARG JAR_FILE=build/libs/*.jar
RUN mkdir -p /app
COPY ${JAR_FILE} /app/app.jar
WORKDIR /app
ENTRYPOINT ["java","-Dlogging.file=app.log","-jar","app.jar","--spring.config.location=classpath:/application.properties",">","log.out","2>&1","</dev/null"]