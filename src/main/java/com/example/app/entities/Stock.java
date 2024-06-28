package com.example.app.entities;

public class Stock {
    private String drugId;
    private String name;
    private int initialQuantity;
    private int quantityLeft;
    private int amountSold;

    // Constructor
    public Stock(String drugId, String name, int initialQuantity, int quantityLeft, int amountSold) {
        this.drugId = drugId;
        this.name = name;
        this.initialQuantity = initialQuantity;
        this.quantityLeft = quantityLeft;
        this.amountSold = amountSold;
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

    @Override
    public String toString() {
        return "Stock{" +
                "drugId='" + drugId + '\'' +
                ", name='" + name + '\'' +
                ", initialQuantity=" + initialQuantity +
                ", quantityLeft=" + quantityLeft +
                ", amountSold=" + amountSold +
                '}';
    }
}

