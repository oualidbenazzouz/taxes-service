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

This service exposes the following API endpoint:

- Endpoint: /api/v1/invoice/calculate
- Method: POST
- Description: Calculates the total taxes and total price for a list of items.
- Request Body: A JSON array of items, each with the following structure:
```json
[
  {
    "name": "string",
    "price": 0.0,
    "exempt": true,
    "imported": false
  }
]
```
- Response:
```json
{
  "purchasedItems": [
    {
      "name": "string",
      "price": 0.0
    }
  ],
  "totalTaxes": 0.0,
  "totalPrice": 0.0
}
```

- **Example**:
- Request :
```json
[
  {
    "name": "Book",
    "price": 12.49,
    "exempt": true,
    "imported": false
  },
  {
    "name": "Music CD",
    "price": 14.99,
    "exempt": false,
    "imported": false
  }
]
```
- Response :
```json
{
  "purchasedItems": [
    {
      "name": "Book",
      "price": 12.49
    },
    {
      "name": "Music CD",
      "price": 16.49
    }
  ],
  "totalTaxes": 1.50,
  "totalPrice": 28.98
}
```  

### Postman Collection
- A Postman collection for testing the API endpoints is available [here](postman/sales-taxes-service-collection.json).

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
- **JUnit 5**

## Author:

- **Oualid BENAZZOUZ**  
  Software Developer | [LinkedIn](https://www.linkedin.com/in/oualid-benazzouz/)

