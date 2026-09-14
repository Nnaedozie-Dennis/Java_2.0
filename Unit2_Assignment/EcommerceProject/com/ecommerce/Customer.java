package com.ecommerce;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.ecommerce.orders.Order;

public class Customer {

    private String customerID;
    private String name;
    private final List<Product> shoppingCart;

    public Customer(String customerID, String name) {
        setCustomerID(customerID);
        setName(name);
        shoppingCart = new ArrayList<>();
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        if (customerID == null || customerID.trim().isEmpty()) {
            throw new IllegalArgumentException("Customer ID cannot be empty.");
        }

        this.customerID = customerID.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Customer name cannot be empty.");
        }

        this.name = name.trim();
    }

    public List<Product> getShoppingCart() {
        return Collections.unmodifiableList(shoppingCart);
    }

    public void addProduct(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null.");
        }

        shoppingCart.add(product);
    }

    public void removeProduct(String productID) {
        if (productID == null || productID.trim().isEmpty()) {
            throw new IllegalArgumentException("Product ID cannot be empty.");
        }

        boolean removed = shoppingCart.removeIf(
            product -> product.getProductID().equalsIgnoreCase(productID.trim())
        );

        if (!removed) {
            throw new IllegalStateException(
                "Product with ID " + productID + " was not found in the shopping cart."
            );
        }
    }

    public double calculateTotalCost() {
        double total = 0.0;

        for (Product product : shoppingCart) {
            total += product.getPrice();
        }

        return total;
    }

    public Order placeOrder(String orderID) {
        if (shoppingCart.isEmpty()) {
            throw new IllegalStateException(
                "Cannot place an order because the shopping cart is empty."
            );
        }

        return new Order(orderID, this, shoppingCart);
    }

    public void displayCart() {
        System.out.println("\n----- Shopping Cart -----");

        if (shoppingCart.isEmpty()) {
            System.out.println("The shopping cart is empty.");
            return;
        }

        for (Product product : shoppingCart) {
            System.out.println(product);
        }

        System.out.printf("Cart Total: $%.2f%n", calculateTotalCost());
    }

    @Override
    public String toString() {
        return String.format(
            "Customer ID: %s | Name: %s | Cart Items: %d",
            customerID, name, shoppingCart.size()
        );
    }
}