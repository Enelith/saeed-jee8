# Build
mvn clean package && docker build -t academy.learnprogramming/hello-javaee8 .

# RUN

docker rm -f hello-javaee8 || true && docker run -d -p 8080:8080 -p 4848:4848 --name hello-javaee8 academy.learnprogramming/hello-javaee8 

# Basic Deploy
ex : java -jar .\payara-micro-5.2021.4.jar --deploy E:\WORK\WORKSPACE\saeed-jee8\hello-javaee8\target\hello-javaee8.war --port 8080