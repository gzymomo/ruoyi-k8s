FROM openjdk:8-jdk
LABEL maintainer=leifengyang


#docker run -e PARAMS="--server.port 8080"
ENV PARAMS="--server.port=8080 --spring.profiles.active=prod --spring.cloud.nacos.discovery.server-addr=nacos-comment.registry:8848 --spring.cloud.nacos.config.server-addr=nacos-comment.registry:8848 --spring.cloud.sentinel.datasource=nacos-comment.registry:8848 --spring.cloud.nacos.config.namespace=3c6513e9-57b4-475d-917d-2a6651856ab4 --spring.cloud.nacos.config.file-extension=yml"
RUN /bin/cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && echo 'Asia/Shanghai' >/etc/timezone

COPY target/*.jar /app.jar
EXPOSE 8080

#
ENTRYPOINT ["/bin/sh","-c","java -Dfile.encoding=utf8 -Djava.security.egd=file:/dev/./urandom -jar app.jar ${PARAMS}"]