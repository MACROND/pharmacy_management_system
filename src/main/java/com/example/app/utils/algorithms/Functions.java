package com.example.app.utils.algorithms;

import com.example.app.controllers.DrugController;
import com.example.app.controllers.SaleController;
import com.example.app.controllers.StockController;
import com.example.app.controllers.SupplierController;
import com.example.app.entities.Drug;
import com.example.app.entities.Sale;
import com.example.app.entities.Stock;
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
 * The `Functions` class contains methods for sorting and sorting entities and
 * saving drug IDs
 * to a sales file.
 */
public class Functions {

    public static final String SALES_FILE_PATH = Paths.get("Sales.txt").toString();
    public static List<Drug> drugsCollection = DrugController.getAllDrugs();
    public static HashMap<Drug, List<Supplier>> drugsAndSuppliers = SupplierController.getSupplierAndDrugs();

    // Sorting Functions

    /**
     * The function `sortDrugsByID` returns a sorted list of drugs based on their
     * ID.
     */
    public static String sortDrugsByID() {
        int size = drugsCollection.size();
        long start = System.currentTimeMillis();
        Sorting.sort(drugsCollection, Comparators.byDrugID());
        long end = System.currentTimeMillis();

        return Functions.generateReport("Sorting Complete.\n" +
                "The sorting algorithm used is known as Insertion Sort", start, end, "Ω(n)", "O(n^2)", size,
                "Insertion Sort");
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

    public static List<Object> sortSalesByTimeAsc() {
        List<Sale> purchaseHistory = SaleController.getAllSales();
        List<Object> result = new ArrayList<>();

        int size = purchaseHistory.size();
        long start = System.currentTimeMillis();
        Sorting.sort(purchaseHistory, Comparators.byPurchaseTime());
        long end = System.currentTimeMillis();

        String report = Functions.generateReport("Sorting Complete.\n" +
                "The sorting algorithm used is Insertion Sort. Used to sort the sales records in oldest order of time.",
                start, end, "Ω(n)", "O(n^2)", size, "Insertion Sort");

        result.add(purchaseHistory);
        result.add(report);
        return result;
    }

    public static List<Object> sortSalesByTimeDesc() {
        List<Sale> purchaseHistory = SaleController.getAllSales();
        List<Object> result = new ArrayList<>();

        int size = purchaseHistory.size();
        long start = System.currentTimeMillis();
        Sorting.sortDescending(purchaseHistory, Comparators.byPurchaseTime());
        long end = System.currentTimeMillis();

        String report =  Functions.generateReport("Sorting Complete.\n" +
                        "The sorting algorithm used is Insertion Sort. Used to sort the sales records in most recent order.",
                start, end, "Ω(n)", "O(n^2)", size, "Insertion Sort");

        result.add(purchaseHistory);
        result.add(report);
        return result;
    }

    public static List<Object> sortSaleByPurchaseAmountAsc(){
        List<Sale> saleList = SaleController.getAllSales();
        List<Object> result = new ArrayList<>();

        int size = saleList.size();
        long start = System.currentTimeMillis();
        List<Sale> sortedStocklist = Sorting.sort(saleList, Comparators.byPurchaseQuantity());
        long end = System.currentTimeMillis();

        String report = Functions.generateReport("Sorting Complete.\n" +
                        "The sorting algorithm used is Insertion Sort. Used to sort the sale records in ascending order of purchase amount.",
                start, end, "Ω(n)", "O(n^2)", size, "Insertion Sort");

        result.add(saleList);
        result.add(report);
        return result;
    }

    public static List<Object> sortSaleByPurchaseAmountDesc(){
        List<Sale> saleList = SaleController.getAllSales();
        List<Object> result = new ArrayList<>();

        int size = saleList.size();
        long start = System.currentTimeMillis();
        List<Sale> sortedStocklist = Sorting.sortDescending(saleList, Comparators.byPurchaseQuantity());
        long end = System.currentTimeMillis();

        String report = Functions.generateReport("Sorting Complete.\n" +
                        "The sorting algorithm used is Insertion Sort. Used to sort the sale records in descending order of purchase amount.",
                start, end, "Ω(n)", "O(n^2)", size, "Insertion Sort");

        result.add(sortedStocklist);
        result.add(report);
        return result;
    }


    public static List<Object> sortStockByAmountLeftDesc(){
        List<Stock> stockList = StockController.getAllStock();
        List<Object> result = new ArrayList<>();

        int size = stockList.size();
        long start = System.currentTimeMillis();
        List<Stock> sortedStocklist = Sorting.sortDescending(stockList, Comparators.byAmountLeftDesc());
        long end = System.currentTimeMillis();

        String report = Functions.generateReport("Sorting Complete.\n" +
                        "The sorting algorithm used is Insertion Sort. Used to sort the stock records in descending order of amount left.",
                start, end, "Ω(n)", "O(n^2)", size, "Insertion Sort");

        result.add(sortedStocklist);
        result.add(report);
        return result;
    }

    public static List<Object> sortStockByAmountLeftAsc(){
        List<Stock> stockList = StockController.getAllStock();
        List<Object> result = new ArrayList<>();

        int size = stockList.size();
        long start = System.currentTimeMillis();
        List<Stock> sortedStocklist = Sorting.sort(stockList, Comparators.byAmountLeftDesc());
        long end = System.currentTimeMillis();

        String report = Functions.generateReport("Sorting Complete.\n" +
                        "The sorting algorithm used is Insertion Sort. Used to sort the stock records in ascending order of amount left.",
                start, end, "Ω(n)", "O(n^2)", size, "Insertion Sort");

        result.add(sortedStocklist);
        result.add(report);
        return result;
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
            // Ensuring the directory exists
            File file = new File(SALES_FILE_PATH);
            File parentDir = file.getParentFile();
            if (parentDir != null && !parentDir.exists()) {
                if (!parentDir.mkdirs()) {
                    throw new IOException("Failed to create directory: " + parentDir.getAbsolutePath());
                }
            }

            // Write drug ID to the file
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

/**
 * The function generates a report with information about the runtime, number of objects in a
 * collection, and time complexity of an algorithm used.
 * 
 * @param preText The `preText` parameter is a String that represents any additional information or
 * introductory text that you want to include at the beginning of the report. It could be a title, a
 * description, or any other relevant information you want to display before the report details.
 * @param start The `start` parameter in the `generateReport` method represents the starting time when
 * a process or algorithm began execution. It is typically a timestamp or value representing the time
 * at which the process started.
 * @param end The `end` parameter in the `generateReport` method represents the end time of the
 * algorithm execution. It is a `long` type variable that holds the timestamp when the algorithm
 * execution ends.
 * @param bestCase The `bestCase` parameter in the `generateReport` method represents the time
 * complexity of the algorithm in the best-case scenario, typically denoted using Omega notation. It
 * describes the lower bound on the running time of the algorithm when it performs optimally or most
 * efficiently.
 * @param wortCase It looks like there is a typo in the parameter name "wortCase". It should be
 * "worstCase" instead. The parameter "worstCase" typically refers to the worst-case time complexity of
 * the algorithm being used.
 * @param collectionSize The `collectionSize` parameter in the `generateReport` method represents the
 * number of objects in the collection for which the report is being generated. This parameter is used
 * to display the size of the collection in the report along with other information such as runtime,
 * time complexity, and the algorithm used.
 * @param algorithmUsed The `algorithmUsed` parameter in the `generateReport` method is a String that
 * represents the algorithm used for the operation being reported on. This could be the name or
 * description of the algorithm that was utilized in the process for which the report is being
 * generated.
 * @return The `generateReport` method returns a formatted report containing the provided preText,
 * collection size, runtime, time complexity information (best case and worst case), and the algorithm
 * used.
 */
    public static String generateReport(
            String preText,
            long start,
            long end,
            String bestCase,
            String wortCase,
            int collectionSize,
            String algorithmUsed) {
        return preText + "\nNumber of objects in the collection: " + collectionSize + "\n" +
                "Runtime: " + (end - start) + " ms.\n\n" +
                "-----Time complexity------- \n" +
                "Best case ( Omega Notation ): " + bestCase + "\n" +
                "Worst case ( Big O Notation ): " + wortCase + "\n" +
                "AlgorithmUsed: " + algorithmUsed;
    }
}
