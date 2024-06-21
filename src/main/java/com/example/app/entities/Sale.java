/**
 * The `Sale` class represents a sale entity with properties such as id, drugId, customerName, date,
 * quantity, and totalPrice.
 */
package com.example.app.entities;

import java.sql.Date;
import java.time.LocalDateTime;

import static java.lang.Integer.parseInt;

public class Sale {
    private int id;
    private String drugId;
    private String customerName;
    private LocalDateTime date;
    private int quantity;
    private double totalPrice;

    // Constructors
    public Sale(int id) {
        this.id = id;
    }

    public Sale(int sale_id, String drugId, String customerName, LocalDateTime date, int quantity, double totalPrice) {
        this.id = sale_id;
        this.drugId = drugId;
        this.customerName = customerName;
        this.date = date;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    public Sale(String drugId, String customerName, LocalDateTime date, int quantity, double totalPrice) {
        this.drugId = drugId;
        this.customerName = customerName;
        this.date = date;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    // Getters, and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDrugId() {
        return drugId;
    }

    public void setDrugId(String drugId) {
        this.drugId = drugId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerId) {
        this.customerName = customerId;
    }

    public LocalDateTime getDate() {
        return LocalDateTime.now();
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
        return "Sale{" +
                "id=" + id +
                ", drugId=" + drugId +
                ", customerName=" + customerName +
                ", date=" + date +
                ", quantity=" + quantity +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
