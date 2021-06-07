# Java Enterprise Edition 8 for Beginners course
## by Luqman Saeed
Udemy : https://www.udemy.com/course/java-enterprise-edition-8/

# What is a Reference Implementation ? 
- Concrete realization of the abstract JSR
- JAX-RS reference implementation - Jersey
- Java EE itself is a JSR
- Java EE 8 is : JSR 366 - RI Glassfish 5

# What is Jakarta EE ? 
- It's basically Java EE going forward (since 2018)
- Hosted by Eclipse Foundation
- https://jakarta.ee
- https://en.wikipedia.org/wiki/Jakarta_EE

## Basic Deploy
ex : java -jar .\payara-micro-5.2021.4.jar --deploy E:\WORK\WORKSPACE\saeed-jee8\hello-javaee8\target\hello-javaee8.war --port 8080

## UberJar
ex : java -jar .\payara-micro-5.2021.4.jar --deploy E:\WORK\WORKSPACE\saeed-jee8\hello-javaee8\target\hello-javaee8.war --port 8080 --outputUberJar HelloJavaEE8.jar

# Java EE ~ Canonical/Secret API
## API 1 - Java Persistence API (JPA) : 
API for : Storing, Mapping, Retrieving data

## API 2 - Java API for RESTful Web Services (JAX-RS) : 
Expose your ressources (Endpoints) over HTTP protocol as Web Services

## API 3 - Contexts and Dependancy Injection (CDI) : 
Act as a "bridge" / glue that holds together the various disparate API Components on the Java Platform (manages the various interactions / relations of the various components)
