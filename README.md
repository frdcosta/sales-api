# Sales API Application
#### This project is a Java-based Sales API application built using Spring Boot, PostgreSQL, RabbitMQ, and Docker. The application follows the principles of clean architecture and implements authentication using JWT.

## Features
* CRUD operations for Client, Product, and Sale entities.
* RabbitMQ integration for handling the sale flow.
* JWT-based authentication.
* PostgreSQL for data storage.
* Clean architecture implementation.

## Prerequisites
* Docker and Docker Compose
* Java 17 or later
* Maven

## Using Application
* Clone the repository to your local machine:
* Build the Docker Images:
  * access the project directory with a terminal and execute the command:
    * docker-compose up --build
* 3 services will build up: RabbitMQ, Postgres and the App.

## API Endpoints
* Inside the folder /collection is the collection .json for postman. There will be all the endpoint you will need to test the application.



