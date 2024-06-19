package com.example.app.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Sale {
    private String sale_id;
    private LocalDateTime date;
    private List<Drug> drugsSold;
    private double totalAmount;

    public Sale(String saleId, LocalDateTime saleDateTime) {
        this.sale_id = saleId;
        this.date = saleDateTime;
        this.drugsSold = new ArrayList<>();
        this.totalAmount = 0.0;
    }

    public String getSaleId() {
        return sale_id;
    }

    public LocalDateTime getSaleDateTime() {
        return date;
    }

    public List<Drug> getDrugsSold() {
        return drugsSold;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void addDrug(Drug drug, int quantity) {
        drugsSold.add(drug);
        totalAmount += drug.getPrice() * quantity;
    }

    @Override
    public String toString() {
        return "Sale{" +
                "saleId='" + sale_id + '\'' +
                ", saleDateTime=" + date +
                ", drugsSold=" + drugsSold +
                ", totalAmount=" + totalAmount +
                '}';
    }
}

