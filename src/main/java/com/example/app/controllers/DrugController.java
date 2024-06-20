package com.example.app.controllers;

import com.example.app.entities.Drug;
import com.example.app.entities.Supplier;
import com.example.app.utils.DatabaseUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DrugController {

    public static final List<Drug> drugList = new ArrayList<>();

    public void addDrug(Drug drug) {
        String query = "INSERT INTO drugs (drug_id, name, description, quantity, price, supplier_id) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, drug.getId());
            stmt.setString(2, drug.getName());
            stmt.setString(3, drug.getDescription());
            stmt.setInt(4, drug.getQuantity());
            stmt.setDouble(5, drug.getPrice());
            stmt.setString(6, drug.getSupplierId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Drug> getAllDrugs() {

        String query = "SELECT * FROM drugs";

        try (Connection conn = DatabaseUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Drug drug = new Drug(rs.getString("drug_id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getInt("quantity"),
                        rs.getDouble("price"),
                        rs.getString("supplier_id")
                );
                drugList.add(drug);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return drugList;
    }

    public static Drug getDrugByName(String name) {
        String query = "SELECT * FROM drugs WHERE name = ?";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return  new Drug(rs.getString("drug_id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getInt("quantity"),
                        rs.getDouble("price"),
                        rs.getString("supplier_id")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteDrug(String id){
        String query = "DELETE * FROM drugs WHERE drug_id = ? OR name = ?";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, id);
                ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                stmt.executeQuery();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
