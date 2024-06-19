package com.example.app.controllers;

import com.example.app.entities.Purchase;
import com.example.app.utils.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PurchaseController {

    private static final List<Purchase> purchaseList = new ArrayList<>();

    public void addPurchase(Purchase purchase) {
        String query = "INSERT INTO purchases (purchase_id, drug_id, customer_id, quantity, total_price) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, purchase.getId());
            stmt.setString(2, purchase.getDrugId());
            stmt.setString(3, purchase.getCustomerId());
            stmt.setInt(4, purchase.getQuantity());
            stmt.setDouble(5, purchase.getTotalPrice());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Purchase> getAllPurchases() {
        String query = "SELECT * FROM purchases";

        try (Connection conn = DatabaseUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Purchase purchase = new Purchase(
                        rs.getInt("purchase_id"),
                        rs.getString("drug_id"),
                        rs.getString("customer_id"),
                        rs.getDate("date").toLocalDate().atStartOfDay(), // Convert java.sql.Date to LocalDate
                        rs.getInt("quantity"),
                        rs.getDouble("total_price")
                );
                purchaseList.add(purchase);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return purchaseList;
    }

    public Purchase getPurchaseById(String purchaseId) {
        String query = "SELECT * FROM purchases WHERE purchase_id = ?";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, purchaseId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Purchase(
                        rs.getInt("purchase_id"),
                        rs.getString("drug_id"),
                        rs.getString("customer_id"),
                        rs.getDate("date").toLocalDate().atStartOfDay(),
                        rs.getInt("quantity"),
                        rs.getDouble("total_price")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
