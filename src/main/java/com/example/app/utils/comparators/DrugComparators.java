package com.example.app.utils.comparators;


import com.example.app.entities.Drug;
import java.util.Comparator;

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
