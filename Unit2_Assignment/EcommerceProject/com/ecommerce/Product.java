package com.ecommerce;

public class Product {

    private String productID;
    private String name;
    private double price;
    private String category;

    public Product(String productID, String name, double price, String category) {
        setProductID(productID);
        setName(name);
        setPrice(price);
        setCategory(category);
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        if (productID == null || productID.trim().isEmpty()) {
            throw new IllegalArgumentException("Product ID cannot be empty.");
        }

        this.productID = productID.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be empty.");
        }

        this.name = name.trim();
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price <= 0) {
            throw new IllegalArgumentException("Product price must be greater than zero.");
        }

        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        if (category == null || category.trim().isEmpty()) {
            throw new IllegalArgumentException("Product category cannot be empty.");
        }

        this.category = category.trim();
    }

    @Override
    public String toString() {
        return String.format(
            "ID: %s | Name: %s | Category: %s | Price: $%.2f",
            productID, name, category, price
        );
    }
}