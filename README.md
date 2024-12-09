# Taxes Service

## Overview

The **Taxes Service** is a Spring Boot application designed to calculate the total taxes and prices for a list of purchased items based on specific tax rules. The application provides a RESTful API for easy integration and includes robust error handling and thorough unit testing.

## Features
- **Tax Calculation Rules**:
    - **Basic Sales Tax**: Applied at a rate of 10% on non-exempt items.
    - **Import Duty Tax**: Applied at a rate of 5% on all imported items.
    - Tax amounts are rounded up to the nearest 0.05.
- **Error Handling**:
    - Detailed error messages for invalid inputs.
    - Proper HTTP status codes (`400` for validation errors, `500` for unexpected errors).
- **API Documentation**:
    - Integrated with Swagger for easy exploration of endpoints.

## Installation

### Prerequisites
- **Java 17** or higher
- **Maven 3.6** or higher
- **Spring Boot 3.4.0**

### Steps
1. Clone the repository:
   ```bash
   git clone <repository_url>
   cd taxes-service

2. Build the project:
   ```bash
   mvn clean install

3. Run the application:
   ```bash
   mvn spring-boot:run


## API Documentation

The API documentation for this service is available through Swagger UI. Swagger provides an interactive interface to explore and test the API endpoints.
    - **Swagger UI URL**: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

    Steps to view the API documentation:
    1. Start the application by running it locally.
    2. Open your web browser and navigate to the provided Swagger UI URL.
    3. Explore the available API endpoints and their request/response structure.


## Running Tests

This project uses **JUnit 5** for unit tests and integration tests.

To run the tests for this project, use the following command:

```bash
mvn test
```


Types of tests included:
- **Unit Tests**:
  - Cover core business logic in the `SalesTaxServiceImpl`.
  - Ensure accurate tax calculations and validations.
- **Integration Tests**:
  - Validate API endpoints in the `SalesTaxControllerTest` using `MockMvc`.
  - Check responses for various inputs and status codes (e.g., `200 OK`, `400 Bad Request`, `500 Internal Server Error`).

Check test reports generated in the terminal after execution for more details.


## Technologies Used:
- **Java 17**
- **Spring Boot**
- **Maven**
- **Swagger**
- **JUnit 5**

## Author:

- **Oualid BENAZZOUZ**  
  Software Developer | [LinkedIn](https://www.linkedin.com/in/oualid-benazzouz/)

