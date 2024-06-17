package com.example.app.entities;

import java.time.LocalDateTime;

public class Purchase {
    private String id;
    private String drugId;
    private String customerId;
    private LocalDateTime date;
    private int quantity;
    private double totalPrice;

    // Constructors
    public Purchase() {}

    public Purchase(String id) {
        this.id = id;
    }

    public Purchase(String id, String drugId, String customerId, LocalDateTime date, int quantity, double totalPrice) {
        this.id = id;
        this.drugId = drugId;
        this.customerId = customerId;
        this.date = date;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }


    // Getters, and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDrugId() {
        return drugId;
    }

    public void setDrugId(String drugId) {
        this.drugId = drugId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    // Formatting to String

    @Override
    public String toString() {
        return "Purchase{" +
                "id=" + id +
                ", drugId=" + drugId +
                ", customerId=" + customerId +
                ", date=" + date +
                ", quantity=" + quantity +
                ", totalPrice=" + totalPrice +
                '}';
    }
}

