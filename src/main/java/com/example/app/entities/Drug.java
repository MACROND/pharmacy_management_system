package com.example.app.entities;

/**
 * The `Drug` class represents a drug entity with properties such as id, name,
 * description, quantity,
 * price, and supplierId, along with constructors and getter/setter methods.
 */
public class Drug {
    private String id;
    private String name;
    private String description;
    private int quantity;
    private double price;
    private String supplierId;

    // Constructors
    public Drug(String id) {
        this.id = id;
    }

    public Drug(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Drug(String id, String name, String description, int quantity, double price, String supplierId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
        this.supplierId = supplierId;
    }


    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    // Formatting to String

    @Override
    public String toString() {
        return "Drug{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", supplierId=" + supplierId +
                '}';
    }
}
