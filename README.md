> ### Spring Boot Pet Project. Implements an RESTful API for receiving and processing orders.
# What is it

The application uses:
* Spring Boot
* Spring JPA 
* H2 Database
* Spring MVC

It can accept and process orders, and create customer accounts.

# Database

It uses an H2 in memory database

# How to use it

Run Spring Boot:

    ./gradlew bootRun

It has 2 preloaded Customers and 2 preloaded Orders.
To work with customers, use:

    http://localhost:8080/customers
    
To work with orders, use:
    
    http://localhost:8080/orders
    
Examples of requests:

    $ curl localhost:8080/customers/1
    $ curl -v -X POST localhost:8080/customers -H 'Content-Type:application/json' -d '{"firstName": "Ivan", "lastName": "Ivanov", "phone": "15554443322"}'
    $ curl -X POST localhost:8080/orders -H 'Content-type:application/json' -d '{"description": "TV", "status": "IN_PROGRESS", "customerId": 1}'
To cancel or complete the order:

    $ curl -v -X PUT localhost:8080/orders/4/complete
    $ curl -v -X PUT localhost:8080/orders/4/cancel