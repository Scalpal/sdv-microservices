# Microservices course 

This folder contains : 

- the API for the school using PostgreSQL,
- the API for the students using MongoDB,
- the API for the authentication using PostgreSQL,
- the configuration microservice to distribute the right config file (application.properties) to each micro-services,
- the Eureka server to manage these microservices that has HTTP calls inbetween them, and to check their statuses.

## Features 

- Service discovery and load balancing (Spring cloud netflix eureka server)
- Gateway (Spring cloud starter gateway)
- Authentication with JWT (Jjwt)
- Configuration management (Spring cloud config server)
