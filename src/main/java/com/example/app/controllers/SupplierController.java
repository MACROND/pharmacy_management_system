package com.example.app.controllers;

import com.example.app.entities.Supplier;
import com.example.app.utils.DatabaseUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SupplierController {

    private static final List<Supplier> supplierList = new ArrayList<>();

    public void addSupplier(Supplier supplier) {
        String query = "INSERT INTO suppliers (supplier_id, name, location, contact) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, supplier.getId());
            stmt.setString(2, supplier.getName());
            stmt.setString(3, supplier.getLocation());
            stmt.setString(4, supplier.getContact());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Supplier> getAllSuppliers() {
        String query = "SELECT * FROM suppliers";

        try (Connection conn = DatabaseUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Supplier supplier = new Supplier(
                        rs.getString("supplier_id"),
                        rs.getString("name"),
                        rs.getString("location"),
                        rs.getString("contact")
                );
                supplierList.add(supplier);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return supplierList;
    }

    public Supplier getSupplierByName(String name) {
        String query = "SELECT * FROM suppliers WHERE name = ?";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Supplier(
                        rs.getString("supplier_id"),
                        rs.getString("name"),
                        rs.getString("location"),
                        rs.getString("contact")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
