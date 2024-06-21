package com.example.app.controllers;

import com.example.app.entities.Sale;
import com.example.app.utils.DatabaseUtil;
import com.example.app.utils.algorithms.Functions;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SaleController {

    private static final List<Sale> saleList = new ArrayList<>();

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
        String query = "INSERT INTO sales (drug_id, customer_name, quantity, total_price) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseUtil.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, sale.getDrugId());
            stmt.setString(2, sale.getCustomerName());
            stmt.setInt(3, sale.getQuantity());
            stmt.setDouble(4, sale.getTotalPrice());
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
        String query = "SELECT * FROM sales";

        try (Connection conn = DatabaseUtil.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Sale purchase = new Sale(
                        rs.getString("drug_id"),
                        rs.getString("customer_id"),
                        rs.getDate("date").toLocalDate().atStartOfDay(),
                        rs.getInt("quantity"),
                        rs.getDouble("total_price"));
                saleList.add(purchase);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return saleList;
    }

    /**
     * This Java function retrieves a sale record from a database based on the
     * provided sale ID.
     * 
     * @param purchaseId The `purchaseId` parameter in the `getSaleById` method is
     *                   used to specify the
     *                   unique identifier of the sale that you want to retrieve
     *                   from the database. This method executes a
     *                   SQL query to select a sale record from the `sales` table
     *                   based on the provided `sale_id`.
     * 
     * @return The `getSaleById` method is returning a `Sale` object based on the
     *         `purchaseId` provided as
     *         a parameter. If a sale with the specified `sale_id` (purchaseId) is
     *         found in the database, a new
     *         `Sale` object is created using the data retrieved from the database
     *         (sale_id, drug_id, customer_id,date, quantity, total_price)
     */
    public Sale getSaleById(String purchaseId) {
        String query = "SELECT * FROM sales WHERE sale_id = ?";

        try (Connection conn = DatabaseUtil.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, purchaseId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Sale(
                        rs.getInt("sale_id"),
                        rs.getString("drug_id"),
                        rs.getString("customer_id"),
                        rs.getDate("date").toLocalDate().atStartOfDay(),
                        rs.getInt("quantity"),
                        rs.getDouble("total_price"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
