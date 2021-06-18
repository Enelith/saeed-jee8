# Build
mvn clean package && docker build -t enelith.javaee/fundamentals-jpa .

# RUN

docker rm -f fundamentals-jpa || true && docker run -d -p 8080:8080 -p 4848:4848 --name fundamentals-jpa enelith.javaee/fundamentals-jpa 