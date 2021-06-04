#!/bin/sh
mvn clean package && docker build -t academy.learnprogramming/get-your-feet-wet .
docker rm -f get-your-feet-wet || true && docker run -d -p 8080:8080 -p 4848:4848 --name get-your-feet-wet academy.learnprogramming/get-your-feet-wet 
