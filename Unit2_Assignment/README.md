### Introduction

This assignment implements a simple e commerce system using Java packages, classes, objects, encapsulation, and exception handling. The system allows customers to view available products, add products to a shopping cart, remove products, calculate the total cost, and place orders. The classes are organized into separate packages to demonstrate how packages improve code organization and reduce naming conflicts.

The `com.ecommerce` package contains the `Product` and `Customer` classes. The `com.ecommerce.orders` package contains the `Order` class. The `Main` class is outside these packages and uses import statements to access the required classes.

### Package Organization

The program is organized as follows:

```text
com.ecommerce
    Product
    Customer

com.ecommerce.orders
    Order

Main
```

This structure separates related responsibilities and makes the program easier to maintain. The use of packages also creates a namespace for the classes and helps prevent naming conflicts.

### Encapsulation and Validation

The attributes in the classes are declared `private` so that they cannot be accessed directly from outside their classes. Public getters and setters are provided where appropriate. The constructors and methods also validate important values. For example, a product cannot have an empty ID or name, and its price cannot be zero or negative.

The customer class prevents invalid operations such as adding a null product or removing a product that is not in the shopping cart. The order class also prevents an order from being created without products and prevents an already placed order from being placed again.

### Exception Handling

The program uses Java exception handling to deal with invalid operations. `IllegalArgumentException` is used when a method receives an invalid value, while `IllegalStateException` is used when an operation cannot be performed because of the current state of the object.

This makes the program more reliable because invalid operations are detected instead of allowing incorrect information to continue through the system.

### Functionality

The main program demonstrates the complete flow of the system. It creates products and a customer, displays the available products, adds products to the customer's shopping cart, displays the cart, calculates the total cost, creates an order, places the order, and displays the order summary.

The program also demonstrates the use of `import` statements to access classes located in the `com.ecommerce` and `com.ecommerce.orders` packages.
