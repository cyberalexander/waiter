## Overview 
The application is aimed to demonstrate Test Driven Development technique in Action.
First the unit tests [RestaurantTest.java](./src/test/java/com/exercise/waiter/RestaurantTest.java) 
were implemented and then the actual business logic was written in order to pass the tests.

The application represents basic algorithm of waiter's job:
1. The table registered with the amount of actual clients/customers (see [Restaurant#initTable](./src/main/java/com/exercise/waiter/Restaurant.java))
2. Then the application (waiter) noting the orders for each customer one by one (see [Restaurant#customerSays](./src/main/java/com/exercise/waiter/Restaurant.java))
3. Then when all the orders are made, waiter creating final oder for the whole table (see [Restaurant#createOrder](./src/main/java/com/exercise/waiter/Restaurant.java))

### Getting started
```bash
$ mvn -U test
```

### Tech stack:
- Java 17
- Maven
- JUnit 4