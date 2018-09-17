# abuser
ABstract User is to provide basic CRUD with reactive Spring boot 2 web flux programming , reactive spring security and MongoDB

## Prerequisite
1. JDK 1.8 
2. mongoDB 3.6 or Higher

This is sample Abstract IDM developed with MongoDB . 
functionalities include
* User Management
* User Authorization & Authentication

## Running 
change the MongoDB properties in 
mvn clean install or mvn spring-boot:run 

on mongoDB 


## API
### Get User

curl "http://localhost:8070/idm/user/{UserId}" -u 'admin:password'

