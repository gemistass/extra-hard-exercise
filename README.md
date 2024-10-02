# intra-soft-exercise

Simple API for storing and retrieving accounts, beneficiaries and transactions from mongodb

## Included
- postman collection for easy testing
- API documentation with openapi specification (can be previewed with swagger UI or Redoc)
- data (accounts, beneficiaries and transaction) within resources 

#### Note:  modified/added some transactions for testing since there were no transcactions on last month

## requirements
- active mongod service 
- free port 27017
- default mongo, without username and password (configurable in properties)

## mongoshell
```
➜  ~ mongosh 
> show dbs
> use test
> db.accounts.find()
> db.beneficiaries.find()
> db.transactions.find()
```
## Developed and tested on

- Java version: 21.0.4  runtime:java-21-openjdk-amd64
- Apache Maven 3.6.3
- Spring CLI v3.3.4
- mongod version: "6.0.18"
- Ubuntu 22.04.5 LTS jammy
- vscode 1.93.1

## Run Spring Boot application
```
mvn spring-boot:run
```
