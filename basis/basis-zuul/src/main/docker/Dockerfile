FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY --from=hengyunabc/arthas:latest /opt/arthas /opt/arthas
RUN apk add tini
ENTRYPOINT ["/sbin/tini", "--"]
ADD lib/ lib/
ADD config/ config/
ADD *.jar app.jar
CMD ["java","-Duser.timezone=Asia/Shanghai","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]