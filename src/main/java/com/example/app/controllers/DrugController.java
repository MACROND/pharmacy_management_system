package com.example.app.controllers;

import com.example.app.entities.Drug;
import com.example.app.utils.DatabaseUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DrugController {

    public void addDrug(Drug drug) {
        String query = "INSERT INTO drugs (id, name, description, quantity, price, supplier_id) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, drug.getId());
            stmt.setString(1, drug.getName());
            stmt.setString(2, drug.getDescription());
            stmt.setInt(3, drug.getQuantity());
            stmt.setDouble(4, drug.getPrice());
            stmt.setString(5, drug.getSupplierId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Drug> getAllDrugs() {
        List<Drug> drugs = new ArrayList<>();
        String query = "SELECT * FROM drugs";

        try (Connection conn = DatabaseUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Drug drug = new Drug(rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getInt("quantity"),
                        rs.getDouble("price"),
                        rs.getString("supplier_id")
                );
                drugs.add(drug);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return drugs;
    }
}
