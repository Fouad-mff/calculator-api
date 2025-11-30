# Calculator REST API

This project contains the backend RESTful service for the Web Calculator Application. It is responsible for receiving mathematical expressions, evaluating them, and returning the calculated result.

---

# Technology Stack

- Language: Java Development Kit (JDK) 21
- Framework: Jakarta EE 10
- Server: Wildfly 36.0.1
- Build Tool: Maven
- Calculation Engine: exp4j

#Getting Started

## Prerequisites

* JDK 21 installed.
* Apache Maven installed.
* Wildfly 36.0.1 running and configured in your IDE (NetBeans recommended).

### Building and Deployment

1.  Clone the Repository:
    git clone https://github.com/Fouad-mff/calculator-api.git

2.  Build the Project: Compile the WAR file using Maven:

    cd calculator-api
    mvn clean install

3.  Deployment: Deploy the generated 'calculator-api-1.0-SNAPSHOT.war' file to your Wildfly server's 'standalone/deployments/' directory.

## REST Endpoint

The service is exposed as a single JAX-RS resource that accepts a GET request with a query parameter.

- Base URL: http://localhost:8080/calculator-api-1.0-SNAPSHOT/api/calculate
- HTTP Method: "GET"
- Query Parameter: expression (The URL-encoded mathematical string, e.g., `46%2B87%2F3`)
- Produces: "text/plain"

### Example Request

GET /calculator-api-1.0-SNAPSHOT/api/calculate?expression=5*2.5

### Example Response

12.5

# Related Project

- Frontend UI: This API is consumed by the user interface project: https://github.com/Fouad-mff/calculator-ui.git