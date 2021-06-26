#!/bin/sh
mvn clean package && docker build -t enelith.javaee/websockets .
docker rm -f websockets || true && docker run -d -p 8080:8080 -p 4848:4848 --name websockets enelith.javaee/websockets 
