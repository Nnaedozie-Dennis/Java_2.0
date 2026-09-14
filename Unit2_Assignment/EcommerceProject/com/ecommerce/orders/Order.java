package com.ecommerce.orders;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.ecommerce.Customer;
import com.ecommerce.Product;

public class Order {

    private String orderID;
    private Customer customer;
    private final List<Product> products;
    private double orderTotal;
    private String orderStatus;

    public Order(String orderID, Customer customer, List<Product> products) {
        setOrderID(orderID);

        if (customer == null) {
            throw new IllegalArgumentException("Customer cannot be null.");
        }

        if (products == null || products.isEmpty()) {
            throw new IllegalArgumentException(
                "An order must contain at least one product."
            );
        }

        this.customer = customer;
        this.products = new ArrayList<>(products);
        calculateOrderTotal();
        this.orderStatus = "Pending";
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        if (orderID == null || orderID.trim().isEmpty()) {
            throw new IllegalArgumentException("Order ID cannot be empty.");
        }

        this.orderID = orderID.trim();
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        if (customer == null) {
            throw new IllegalArgumentException("Customer cannot be null.");
        }

        this.customer = customer;
    }

    public List<Product> getProducts() {
        return Collections.unmodifiableList(products);
    }

    public double getOrderTotal() {
        return orderTotal;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void calculateOrderTotal() {
        orderTotal = 0.0;

        for (Product product : products) {
            orderTotal += product.getPrice();
        }
    }

    public void updateOrderStatus(String newStatus) {
        if (newStatus == null || newStatus.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "Order status cannot be empty."
            );
        }

        orderStatus = newStatus.trim();
    }

    public String generateOrderSummary() {
        StringBuilder summary = new StringBuilder();

        summary.append("\n===== ORDER SUMMARY =====\n");
        summary.append("Order ID: ").append(orderID).append("\n");
        summary.append("Customer: ")
               .append(customer.getName())
               .append("\n");
        summary.append("Customer ID: ")
               .append(customer.getCustomerID())
               .append("\n");
        summary.append("Order Status: ")
               .append(orderStatus)
               .append("\n");

        summary.append("\nProducts:\n");

        for (Product product : products) {
            summary.append("  ")
                   .append(product.getName())
                   .append(" - $")
                   .append(String.format("%.2f", product.getPrice()))
                   .append("\n");
        }

        summary.append("\nOrder Total: $")
               .append(String.format("%.2f", orderTotal))
               .append("\n");

        return summary.toString();
    }

    public void placeOrder() {
        if (!orderStatus.equals("Pending")) {
            throw new IllegalStateException(
                "This order has already been processed."
            );
        }

        orderStatus = "Placed";
    }
}