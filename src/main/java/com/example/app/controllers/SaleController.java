package com.example.app.controllers;

import com.example.app.entities.Sale;
import com.example.app.utils.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SaleController {

    private static final List<Sale> saleList = new ArrayList<>();

    public void addSale(Sale sale) {
        String query = "INSERT INTO sales (drug_id, customer_id, quantity, total_price) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, sale.getDrugId());
            stmt.setString(2, sale.getCustomerId());
            stmt.setInt(3, sale.getQuantity());
            stmt.setDouble(4, sale.getTotalPrice());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Sale> getAllSales() {
        String query = "SELECT * FROM sales";

        try (Connection conn = DatabaseUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Sale purchase = new Sale(
                        rs.getInt("sale_id"),
                        rs.getString("drug_id"),
                        rs.getString("customer_id"),
                        rs.getDate("date").toLocalDate().atStartOfDay(),
                        rs.getInt("quantity"),
                        rs.getDouble("total_price")
                );
                saleList.add(purchase);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return saleList;
    }

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
                        rs.getDouble("total_price")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
