package com.example.app.utils.algorithms;

import com.example.app.controllers.DrugController;
import com.example.app.controllers.SaleController;
import com.example.app.entities.Drug;
import com.example.app.entities.Sale;
import com.example.app.entities.Supplier;
import com.example.app.utils.comparators.DrugComparators;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

public class Functions {
    public static List<Drug> drugsCollection = DrugController.getAllDrugs();
    public static List<Sale> purchaseHistory = SaleController.getAllSales();
    public static HashMap<Drug, Supplier> drugsSuppliers =  new HashMap<>();

    // Sorting Functions
    public static List<Drug> sortDrugsByID(){
        return Sorting.sort(drugsCollection, DrugComparators.byID());
    }

    public static List<Drug> sortDrugsByQuantity(){
        return Sorting.sort(drugsCollection, DrugComparators.byQuantity());
    }

    public static List<Drug> sortDrugsByPrice(){
        return Sorting.sort(drugsCollection, DrugComparators.byPrice());
    }
}
