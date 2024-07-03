package com.example.app.utils.comparators;


import com.example.app.entities.Drug;
import java.util.Comparator;

/**
 * The `DrugComparators` class provides static methods to create comparators for sorting `Drug` objects
 * by ID, name, price, and quantity.
 */
public class DrugComparators {
    public static Comparator<Drug> byID() {
        return Comparator.comparing(Drug::getId);
    }

    public static Comparator<Drug> byName() {
        return Comparator.comparing(Drug::getName);
    }

    public static Comparator<Drug> byPrice() {
        return Comparator.comparingDouble(Drug::getPrice);
    }

    public static Comparator<Drug> byQuantity() {
        return Comparator.comparingDouble(Drug::getQuantity);
    }
}
