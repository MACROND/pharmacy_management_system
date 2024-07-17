package com.example.app.controllers;

import com.example.app.entities.Drug;
import com.example.app.entities.Sale;
import com.example.app.utils.DatabaseUtil;
import com.example.app.utils.algorithms.Functions;

import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import static java.lang.Integer.parseInt;

public class SaleController {

    private static List<Sale> saleList = new ArrayList<>();

    /**
     * The `addSale` function inserts a new sale record into a database table and
     * then saves the sale
     * information to a file.
     * 
     * @param sale The `addSale` method is used to add a sale record to a database
     *             table named `sales`. The
     *             method takes a `Sale` object as a parameter, which likely
     *             contains information about the sale such
     *             as the drug ID, customer name, quantity, and total price.
     */
    public void addSale(Sale sale) {
        String query = "INSERT INTO sales (drug_id, customer_name, customer_contact, quantity, total_price) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseUtil.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, sale.getDrugId());
            stmt.setString(2, sale.getCustomerName());
            stmt.setString(3, sale.getCustomerContact());
            stmt.setInt(4, sale.getQuantity());
            stmt.setDouble(5, sale.getTotalPrice());
            stmt.executeUpdate();

            // Write sale code to Save file
            Functions.saveToSales(sale.getDrugId());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This Java function retrieves all sales data from a database table and returns
     * a list of Sale
     * objects.
     * 
     * @return The `getAllSales` method returns a List of Sale objects.
     */
    public static List<Sale> getAllSales() {
        saleList.clear();
        String query = "SELECT * FROM sales";
        try (Connection conn = DatabaseUtil.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Sale purchase = new Sale(
                        rs.getInt("sale_id"),
                        rs.getString("drug_id"),
                        rs.getTimestamp("date").toLocalDateTime(),
                        rs.getInt("quantity"),
                        rs.getDouble("total_price"),
                        rs.getString("customer_name"),
                        rs.getString("customer_contact"));
                saleList.add(purchase);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return saleList;
    }

    /**
     * The `deleteSale` method deletes a purchase from the database and updates
     * the sale and drug lists.
     *
     * @param saleID The ID of the supplier to be deleted.
     */
    public static List<Sale> deleteSale(Integer saleID) {

        saleList.clear();
        // Remove sales from sales collection
        saleList = SaleController.getAllSales();

        Iterator<Sale> iterator = saleList.iterator();
        while (iterator.hasNext()) {
            Sale sale = iterator.next();
            if (saleID.equals(sale.getId())) {
                iterator.remove();
            }
        }

        // Delete sales from databases
        String query = "DELETE FROM sales WHERE sale_id = ?";

        try (Connection conn = DatabaseUtil.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, saleID);
            stmt.executeUpdate();
            System.out.println("Sales record deleted from database.");
        } catch (SQLException e) {
            System.out.println("Couldn't delete sales record.");
            e.printStackTrace();
        }

        return saleList;
    }

    /**
     * The `searchSale` function searches for a sale record based on a purchase ID,
     * customer name, or
     * customer contact in a list of sales and returns a report on the search
     * process.
     * 
     * @param purchaseId The `searchSale` method you provided is used to search for
     *                   sales records based
     *                   on a given `purchaseId`. The `purchaseId` parameter is used
     *                   to search for a sale record that
     *                   matches either the sale ID, customer name, or customer
     *                   contact number.
     * @return The `searchSale` method returns a `List<Object>` containing the
     *         following elements:
     *         1. If a matching sale record is found based on the provided
     *         `purchaseId`, it adds a list of
     *         found sales records (`foundSales`) and a generated report as elements
     *         to the result list. The
     *         report includes information about the search operation performed,
     *         such as the time taken,
     *         complexity analysis, and details of the
     */
    public static List<Object> searchSale(String purchaseId) {
        saleList = getAllSales();
        System.out.println("Searching sales collection");
        List<Object> result = new ArrayList<>();

        int size = saleList.size();

        Iterator<Sale> iterator = saleList.iterator();
        double start = System.currentTimeMillis();
        List<Sale> foundSales = new ArrayList<>();

        while (iterator.hasNext()) {
            Sale sale = iterator.next();
            if (purchaseId.equals(Integer.toString(sale.getId())) ||
                    purchaseId.equalsIgnoreCase(sale.getCustomerName()) ||
                    purchaseId.equals(sale.getCustomerContact())) {
                double end = System.currentTimeMillis();

                foundSales.add(sale);

                System.out.println("Found sales record.");

                result.add(foundSales); // Add the list of matched sales
                result.add(Functions.generateReport(
                        "Sale record found.\nThe sales collection was traversed from " +
                                "the front to the rear of the collection while comparing the sale ID passed to that of the "
                                +
                                "sales objects in the collection using the Iterator class.",
                        start, end, "Ω(1)", "O(n)", size, "Iterator search"));

                return result;
            }
        }

        // If no match is found, add appropriate results
        double end = System.currentTimeMillis();
        System.out.println("No sales record found.");

        result.add(foundSales); // Add the empty list to the result
        result.add("No sales record matching the provided purchase ID was found.");

        return result;
    }
}
