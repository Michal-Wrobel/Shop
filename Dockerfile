FROM openjdk:8-jdk
VOLUME /tmp
ARG JAR_FILE=./target/Shop-0.0.1-SNAPSHOT.jar
ENV JAVA_OPTS="-XX:+UseG1GC -Xms1024m -Xmx1024m"
ENV TZ=Europe/Warsaw
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
COPY ${JAR_FILE} app.jar
COPY ./entrypoint.sh entrypoint.sh
#!/usr/bin/env bash
#required for proper logging of source in graylog
