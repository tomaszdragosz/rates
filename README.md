# Rates API

Written using: Java 8, Maven, Git, Spring Boot, Hibernate, Lombok, Swagger, H2

Tested with: Spock

## Prepare local environment

### Build application

```
mvn clean package
```

## Run Service
```
java -jar target/rates-0.0.1-SNAPSHOT.jar
``` 
You can find swagger API under: http://localhost:8080/swagger-ui.html

## API operations

### Load rates
```
curl -X GET "http://localhost:8080/rates/load" -H  "accept: */*"
```

### Get rate
```
curl -X GET "http://localhost:8080/rates/2021-08-05?currency=GBP" -H  "accept: */*"
```
