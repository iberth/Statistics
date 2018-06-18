# Statistics API

This is a REST API to manage Transactions and its Statistics. The project has been implemented using the following frameworks/libraries:

* Spring Boot.
* H2 Database Engine. (In memory database)

## Prerequisites

Is needed to have installed:

* Java 1.8
* Apache Maven 3.2.1 or newer.

## Packaging

Run the following command to build and package the tool.
```
mvn clean install
```

## Running

Run the following command to start the server.
```
java -jar target/statistics-0.0.1.jar
```

## Usage

### Save Transactions
Request:
```
HTTP Method: POST 
Endpoint URL: http://localhost:8080/transactions
Payload:
{
  "amount": 14.3,
  "timestamp": 1529289673345
}
```
Responses:
```
201: Created successfully
204: Transaction is older than 60 seconds.
```

### Get Statistics
Request:
```
HTTP Method: GET 
Endpoint URL: http://localhost:8080/statistics
```
Response:
```
200: Ok
Payload:
{
    "sum": 40.900000000000006,
    "avg": 13.633333333333335,
    "max": 20.3,
    "min": 10.3,
    "count": 3
}
```

## TO DO

* Add documentation.


## Author

* **Iberth Fernandez** - *Initial work*
