package com.example.app.controllers;

import com.example.app.entities.Sale;
import com.example.app.utils.DatabaseUtil;
import com.example.app.utils.algorithms.Functions;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
     * This Java function retrieves a sale record from a database based on the
     * provided sale ID.
     *
     * @param purchaseId The `purchaseId` parameter in the `getSaleById` method is
     *                   used to specify the
     *                   unique identifier of the sale that you want to retrieve
     *                   from the database. This method executes a
     *                   SQL query to select a sale record from the `sales` table
     *                   based on the provided `sale_id`.
     */
    public void searchSale(String purchaseId) {
        String query = "SELECT * FROM sales WHERE sale_id = ?";

        try (Connection conn = DatabaseUtil.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, purchaseId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                rs.getInt("sale_id");
                rs.getString("drug_id");
                rs.getDate("date").toLocalDate().atStartOfDay();
                rs.getInt("quantity");
                rs.getDouble("total_price");
                rs.getString("customer_name");
                rs.getString("customer_contact");
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * The `deleteSale` method deletes a purchase from the database and updates
     * the sale and drug lists.
     *
     * @param saleID The ID of the supplier to be deleted.
     */
    public static void deleteSale(int saleID) {
        String query = "DELETE FROM sales WHERE sale_id = ?";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, saleID);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Update sale
        saleList = SaleController.getAllSales();
    }
}