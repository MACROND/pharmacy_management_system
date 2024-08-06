package com.example.app.utils.comparators;

import com.example.app.entities.Drug;
import com.example.app.entities.Sale;
import com.example.app.entities.Stock;

import java.util.Comparator;

/**
 * The `Comparators` class provides static methods to create Comparator
 * instances for sorting Drug and
 * Sale objects based on different properties.
 */
public class Comparators {
    public static Comparator<Drug> byDrugID() {
        return Comparator.comparing(Drug::getId);
    }

    public static Comparator<Drug> byDrugName() {
        return Comparator.comparing(Drug::getName);
    }

    public static Comparator<Drug> byDrugPrice() {
        return Comparator.comparingDouble(Drug::getPrice);
    }

    public static Comparator<Drug> byDrugQuantity() {
        return Comparator.comparingDouble(Drug::getQuantity);
    }

    public static Comparator<Sale> byPurchaseTime() {
        return Comparator.comparing(Sale::getDate);
    }

    public static Comparator<Sale> byPurchaseQuantity() {
        return Comparator.comparing(Sale::getQuantity);
    }


    public static Comparator<Stock> byAmountLeftDesc(){return Comparator.comparingInt(Stock::getQuantityLeft);}
}
