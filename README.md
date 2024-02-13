Customer API

This is a RESTful API for managing customer data.
Table of Contents

    Introduction
    Technologies
    Setup
    Usage
    Endpoints

Introduction

This Spring Boot application provides endpoints to perform CRUD operations on customer data. It includes functionalities to create, read, update, and delete customer records.
Technologies

    Java 17
    Spring Boot
    PostgreSQL
    Docker

Setup

    Clone the repository:

    bash

git clone https://github.com/your-username/customer-api.git

Navigate to the project directory:

bash

cd customer-api

Build the application:

bash

mvn clean install

Run the application:

bash

    mvn spring-boot:run

    Access the API:
    The application will be running on http://localhost:8080.

Usage

Once the application is running, you can use any HTTP client to interact with the API endpoints. You can also use tools like Postman or cURL for testing.
Endpoints

    GET /api/v1/customers: Retrieve all customers.
    GET /api/v1/customers/{customerId}: Retrieve a specific customer by ID.
    POST /api/v1/customers: Create a new customer.
    PUT /api/v1/customers/{customerId}: Update an existing customer.
    DELETE /api/v1/customers/{customerId}: Delete a customer by ID.
