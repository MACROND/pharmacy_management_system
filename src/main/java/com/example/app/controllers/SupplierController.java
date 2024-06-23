package com.example.app.controllers;

import com.example.app.entities.Drug;
import com.example.app.entities.Supplier;
import com.example.app.utils.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import static java.lang.Integer.parseInt;


public class SupplierController {

    private static final List<Supplier> supplierList = new ArrayList<>();
    public static final HashMap<Integer, List<Drug>> supplierAndDrugs = new HashMap<>();

    /**
     * The `addSupplier` function inserts a new supplier into a database table using
     * JDBC
     * PreparedStatement.
     * 
     * @param supplier - supplier.getId(): The ID of the supplier
     */
    public void addSupplier(Supplier supplier) {
        String query = "INSERT INTO suppliers (name, location, contact) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseUtil.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, supplier.getName());
            stmt.setString(2, supplier.getLocation());
            stmt.setString(3, supplier.getContact());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * The function getAllSuppliers retrieves all supplier information from a
     * database table and returns a
     * list of Supplier objects.
     * 
     * @return The `getAllSuppliers` method returns a List of Supplier objects. The
     *         method retrieves
     *         supplier information from a database table named "suppliers", creates
     *         Supplier objects for each row
     *         in the table, adds them to the supplierList, and finally returns the
     *         list of Supplier objects.
     */
    public static List<Supplier> getAllSuppliers() {
        String query = "SELECT * FROM suppliers";
        supplierList.clear();

        try (Connection conn = DatabaseUtil.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Supplier supplier = new Supplier(
                        rs.getString("supplier_id"),
                        rs.getString("name"),
                        rs.getString("location"),
                        rs.getString("contact"));
                supplierList.add(supplier);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return supplierList;
    }

    /**
     * The function `getSupplierByName` retrieves a supplier from the database based
     * on the given name.
     * 
     * @param name The `getSupplierByName` method takes a `String` parameter `name`,
     *             which represents the
     *             name of the supplier you want to retrieve from the database. The
     *             method then queries the database to
     *             find the supplier with the specified name and returns a
     *             `Supplier` object if found, or `null` if
     * 
     * @return The `getSupplierByName` method is returning a `Supplier` object with
     *         details such as
     *         `supplier_id`, `name`, `location`, and `contact` if a supplier with
     *         the specified name is found in
     *         the database. If no supplier is found, it returns `null`.
     */
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
                        rs.getString("contact"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * The function `getSupplierAndDrugs` retrieves a mapping of suppliers to the
     * list of drugs they
     * supply.
     *
     * @return The `getSupplierAndDrugs` method returns a `HashMap` where the key is
     * a `String`
     * representing the supplier ID, and the value is a `List` of `Drug`
     * objects associated with that
     * supplier.
     */
    public static HashMap<Integer, List<Drug>> getSupplierAndDrugs() {
        supplierAndDrugs.clear();

        List<Supplier> supplierList = getAllSuppliers();
        List<Drug> drugList = DrugController.getAllDrugs();

        for (Supplier supplier : supplierList) {
            List<Drug> drugsForSupplier = new ArrayList<>();
            for (Drug drug : drugList) {
                if (Objects.equals(parseInt(drug.getSupplierId()), supplier.getId())) {
                    drugsForSupplier.add(drug);
                }
            }
            supplierAndDrugs.put(parseInt(supplier.getId()), drugsForSupplier);
        }

        return supplierAndDrugs;
    }

    public static void deleteSupplier(int supplierID) {
        HashMap<Integer, List<Drug>> supplierList = getSupplierAndDrugs();
        supplierList.remove(supplierID);
    }
}
