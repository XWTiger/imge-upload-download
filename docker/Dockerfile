FROM registry-harbor:5000/full-stack-cloud/alpine-java:8_server-jre_unlimited
MAINTAINER sugoncloud "sugoncloud@sugon.com"

RUN mkdir -p /apps

COPY sugoncloud-document-api.jar /apps

COPY sugoncloud-document-api.conf /apps

COPY  main /opt

RUN   chmod 777 /opt/main

ENV LANG en_US.utf8

CMD ["/apps/sugoncloud-document-api.jar", "start"]

EXPOSE 8080
