FROM openjdk:8-jre
ARG JAR_EXPOSE=8082
EXPOSE $JAR_EXPOSE
ARG JAR_FILE
ADD $JAR_FILE /app.jar
ENTRYPOINT ["java", "-jar", "-Duser.timezone=GMT+08", "/app.jar"]


#FROM openjdk:11-jdk
#ARG JAR_FILE
#COPY ${JAR_FILE} app.jar
#EXPOSE 8082
#ENTRYPOINT ["java","-jar","/app.jar"]
#----------------------------------------
#FROM openjdk:8-jdk-alpine
#VOLUME /tmp
#COPY --from=hengyunabc/arthas:latest /opt/arthas /opt/arthas
#RUN apk add tini
#ENTRYPOINT ["/sbin/tini", "--"]
#RUN apk --no-cache add curl
#ADD shutdown.sh /shutdown.sh
#ADD lib/ lib/
#ADD config/ config/
#ADD *.jar app.jar
#CMD ["java","-Duser.timezone=Asia/Shanghai","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
#,"-javaagent:/usr/skywalking/agent/skywalking-agent.jar"