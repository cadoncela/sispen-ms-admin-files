FROM alpine:latest
LABEL maintainer="cadoncela@dane.gov.co"
LABEL version="1"
LABEL description="Imagen base spring boot sispen-ms-admin-files"
RUN apk update && apk add inotify-tools openjdk17 curl vim

COPY ./www /app
WORKDIR /app

# create user "spring" and create a home directory for it
RUN adduser -D spring && \
    chown -R spring:spring /app && \
    chmod -R 755 /app && \
    mkdir -p /home/spring/.mvn && \
    chown -R spring:spring /home/spring

USER spring
