package com.example.app.utils.algorithms;

import com.example.app.controllers.DrugController;
import com.example.app.controllers.SaleController;
import com.example.app.controllers.SupplierController;
import com.example.app.entities.Drug;
import com.example.app.entities.Sale;
import com.example.app.entities.Supplier;
import com.example.app.utils.comparators.Comparators;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * The `Functions` class contains methods for sorting and sorting entities and saving drug IDs
 * to a sales file.
 */
public class Functions {

    public static final String SALES_FILE_PATH = Paths.get("Sales.txt").toString();
    public static List<Drug> drugsCollection = DrugController.getAllDrugs();
    public static List<Sale> purchaseHistory = SaleController.getAllSales();
    public static HashMap<Drug, List<Supplier>> drugsAndSuppliers = SupplierController.getSupplierAndDrugs();

    // Sorting Functions

    /**
     * The function `sortDrugsByID` returns a sorted list of drugs based on their
     * ID.
     */
    public static void sortDrugsByID() {
        Sorting.sort(drugsCollection, Comparators.byDrugID());
    }

    /**
     * The function `sortDrugsByQuantity` sorts a collection of drugs based on their
     * quantity.
     *
     * @return A list of Drug objects sorted by quantity.
     */
    public static List<Drug> sortDrugsByQuantity() {
        return Sorting.sort(drugsCollection, Comparators.byDrugQuantity());
    }


    /**
     * The function `sortDrugsByPrice` sorts a collection of drugs by price using a
     * comparator.
     *
     * @return A list of Drug objects sorted by price.
     */
    public static List<Drug> sortDrugsByPrice() {
        return Sorting.sort(drugsCollection, Comparators.byDrugPrice());
    }

/**
 * The function `searchDrugByID` searches for a drug in a collection by its ID and returns a list
 * containing the result.
 * 
 * @param drug_id The `searchDrugByID` method takes an integer `drug_id` as a parameter. This method
 * searches for a drug in a collection based on the provided `drug_id` using a custom search method. It
 * then adds the found drug to a list and returns the list of drugs that match the given
 * @return This method returns a List of Drug objects that match the provided drug_id. The method
 * performs a custom search on the drugsCollection using the DrugComparators.byID() comparator to find
 * the Drug object with the specified drug_id. The found Drug object is then added to a new List and
 * returned as the result.
 */
    public static List<Drug> searchDrugByID(int drug_id){
        List<Drug> resultList = new ArrayList<>();
        Drug result = Searching.customSearch(drugsCollection, drug_id, Comparators.byDrugID());
        resultList.add(result);
        return resultList;
    }


    
    /**
     * The `saveToSales` function saves a drug ID to a file located at a specified
     * path.
     *
     * @param drugId The `drugId` parameter in the `saveToSales` method is a unique
     *               identifier for a drug
     *               that needs to be saved to a sales file. This method ensures
     *               that the directory for the sales file
     *               exists, creates it if necessary, and then appends the drugId to
     *               the file along.
     */
    public static void saveToSales(String drugId) {
        BufferedWriter writer = null;
        try {
            // Ensure the directory exists
            File file = new File(SALES_FILE_PATH);
            File parentDir = file.getParentFile();
            if (parentDir != null && !parentDir.exists()) {
                if (!parentDir.mkdirs()) {
                    throw new IOException("Failed to create directory: " + parentDir.getAbsolutePath());
                }
            }

            // Write the drug ID to the file
            writer = new BufferedWriter(new FileWriter(SALES_FILE_PATH, true));
            System.out.println("Writing Sales ID in Sales file...");
            writer.write(drugId);
            writer.newLine();
            System.out.println("Sales ID written in Sales file.");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
