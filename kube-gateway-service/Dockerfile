FROM openjdk:jdk-alpine
ENV APP_FILE kube-gateway-service-0.0.1-SNAPSHOT.jar
ENV APP_HOME /usr/apps
EXPOSE 8100
COPY target/$APP_FILE $APP_HOME/
WORKDIR $APP_HOME
ENTRYPOINT ["sh", "-c"]
CMD ["exec java -jar $APP_FILE"]