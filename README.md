# About the application

A simple fintech app which can register users in 3 roles (ADMIN, TELLER and CUSTOMER) with
ability of creating TELLER users who can register customers and and open bank account for them.
As a user with CUSTOMER role, you can submit 3 types of banking transactions including DEPOSIT, TRANSFER and WITHDRAW
from your acount.

# Software Architecture

This application has 3 modules including Account-Management, Authentication-Management and Commons module.
The mentioned modules could be considered as 3 different independent services in a microservice architecture
which have correlation among each-other.

# Application functionality at start

In order to check the available features of the application, when it starts, it checks the database to see if theere's
any users or roles.
If there's no role and users, it creates the following users:

admin: has the ADMIN role (username: admin, password: admin)
teller: has the TELLER role (username: teller, password: teller)
customer1: has the CUSTOMER role (username: akbar, password: akbar)
customer2: has the CUSTOMER role (username: asghar, password: asghar)

in order to work with the app, users must go through login process by sending a post request to:

http://localhost:9002/snapp-pay/api/auth/login

With payload {"username": "", "password": ""}

NOTE: username and password values have to be entered in the payload.

# Swagger documentation url

The OpenAPI documentation can be found from the below url:

http://localhost:9002/snapp-pay/api/swagger-ui/index.html

### Technologies used:

- Spring Boot
- Spring security
- Lombok
- Persistence: Spring Data JPA
