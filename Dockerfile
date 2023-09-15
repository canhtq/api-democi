FROM alpine:3.17

# Install packages.
RUN apk update && \
    apk add \
        openssl \
        tzdata \
        openjdk8 \
        curl && \
    rm -rf /var/cache/apk/*

ENV TZ=Asia/Ho_Chi_Minh
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone && mkdir /app && chmod 755 /app

WORKDIR /app
COPY target/api-1.0.jar /app/api-1.0.jar

ENTRYPOINT ["java","-Dspring.config.name=application","-Dspring.config.location=/app/","-jar","/app/api-1.0.jar","-XX:-UseConcMarkSweepGC","-XX:-UseGCOverheadLimit","-Xms1024M","-Xmx2048M"]