package com.example.app.entities;

import java.time.LocalDateTime;

public class Stock {
    private String drugId;
    private String name;
    private int initialQuantity;
    private int quantityLeft;
    private int amountSold;
    private LocalDateTime lastUpdated;
    private String status;

    // Constructor
    public Stock(String drugId, String name, int initialQuantity, int quantityLeft, int amountSold, LocalDateTime lastUpdated, String status) {
        this.drugId = drugId;
        this.name = name;
        this.initialQuantity = initialQuantity;
        this.quantityLeft = quantityLeft;
        this.amountSold = amountSold;
        this.lastUpdated = lastUpdated;
        this.status = status;
    }

    public Stock(String drugId, String name, int initialQuantity, int quantityLeft, int amountSold) {
        this.drugId = drugId;
        this.name = name;
        this.initialQuantity = initialQuantity;
        this.quantityLeft = quantityLeft;
        this.amountSold = amountSold;
    }


    public Stock(String drugId) {
        this.drugId = drugId;
    }



    // Getters and Setters
    public String getDrugId() {
        return drugId;
    }

    public void setDrugId(String drugId) {
        this.drugId = drugId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getInitialQuantity() {
        return initialQuantity;
    }

    public void setInitialQuantity(int initialQuantity) {
        this.initialQuantity = initialQuantity;
    }

    public int getQuantityLeft() {
        return quantityLeft;
    }

    public void setQuantityLeft(int quantityLeft) {
        this.quantityLeft = quantityLeft;
    }

    public int getAmountSold() {
        return amountSold;
    }

    public void setAmountSold(int amountSold) {
        this.amountSold = amountSold;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "drugId='" + drugId + '\'' +
                ", name='" + name + '\'' +
                ", initialQuantity=" + initialQuantity +
                ", amountSold=" + amountSold +
                ", quantityLeft=" + quantityLeft +
                ", status=" + status +
                '}';
    }
}

