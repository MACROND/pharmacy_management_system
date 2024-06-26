package com.example.app.utils.algorithms;

import com.example.app.controllers.DrugController;
import com.example.app.controllers.SaleController;
import com.example.app.controllers.SupplierController;
import com.example.app.entities.Drug;
import com.example.app.entities.Sale;
import com.example.app.utils.comparators.DrugComparators;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

/**
 * The `Functions` class contains methods for sorting drugs and saving drug IDs
 * to a sales file.
 */
public class Functions {

    public static final String SALES_FILE_PATH = Paths.get("C:\\Users\\Maxwell Odoom Anane\\OneDrive\\Desktop\\Pharmacy\\src\\main\\java\\com\\example\\app\\files\\Sales.txt").toString();
    public static List<Drug> drugsCollection = DrugController.getAllDrugs();
    public static List<Sale> purchaseHistory = SaleController.getAllSales();
    public static HashMap<Integer, List<Drug>> drugsAndSuppliers = SupplierController.getSupplierAndDrugs();

    // Sorting Functions

    /**
     * The function `sortDrugsByID` returns a sorted list of drugs based on their
     * ID.
     *
     * @return The method `sortDrugsByID` is returning a sorted list of drugs based
     * on their ID.
     */
    public static List<Drug> sortDrugsByID() {
        return Sorting.sort(drugsCollection, DrugComparators.byID());
    }


    /**
     * The function `sortDrugsByQuantity` sorts a collection of drugs based on their
     * quantity.
     *
     * @return A list of Drug objects sorted by quantity.
     */
    public static List<Drug> sortDrugsByQuantity() {
        return Sorting.sort(drugsCollection, DrugComparators.byQuantity());
    }


    /**
     * The function `sortDrugsByPrice` sorts a collection of drugs by price using a
     * comparator.
     *
     * @return A list of Drug objects sorted by price.
     */
    public static List<Drug> sortDrugsByPrice() {
        return Sorting.sort(drugsCollection, DrugComparators.byPrice());
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
