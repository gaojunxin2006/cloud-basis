FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY --from=hengyunabc/arthas:latest /opt/arthas /opt/arthas
RUN apk add tini
ENTRYPOINT ["/sbin/tini", "--"]
RUN apk --no-cache add curl 
ADD shutdown.sh /shutdown.sh
ADD lib/ lib/
ADD config/ config/
ADD *.jar app.jar
CMD ["java","-Duser.timezone=Asia/Shanghai","-javaagent:/usr/skywalking/agent/skywalking-agent.jar","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]