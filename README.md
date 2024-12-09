# Blog Backend
This is a simple backend application for a blog to test features and concepts.

## Objectives

The main objective of this project is to test resources to build and maintain more robust applications such as:
- Circuit breaker;
- Service registry;
- Service discovery;
- Cache;
- Authorization and authentication;
- Exceptions treatment;
- External APIs usage;
- Message broker usage (RabbitMQ);
- Database use (SQL)

This first version includes the preliminary implementation of these features.

## How To Run

Required: Java 17, Spring 3+, Maven and Docker.

```bash
# clone repository
git clone git@github.com:DanielGMesquita/blog-backend.git

# run the project
docker-compose up --build
```

## Stack
- Java
- Spring Boot
- JPA / Hibernate
- Maven
- Docker