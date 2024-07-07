package com.example.app.controllers;

import com.example.app.entities.Drug;
import com.example.app.entities.Supplier;
import com.example.app.utils.DatabaseUtil;

import java.sql.*;
import java.util.*;

import static java.lang.Integer.parseInt;


public class SupplierController {

    private static final List<Supplier> supplierList = new ArrayList<>();
    public static final HashMap<Drug, List<Supplier>> supplierAndDrugs = new HashMap<>();

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
                PreparedStatement stmt = conn.prepareStatement(query)){
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
                        rs.getInt("supplier_id"),
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
     * @param id The `getSupplierByName` method takes a `String` parameter `name`,
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
    public static Supplier getSupplierByID(int id) {
        String query = "SELECT * FROM suppliers WHERE supplier_id = ?";

        try (Connection conn = DatabaseUtil.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Supplier(
                        rs.getInt("supplier_id"),
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

    public static HashMap<Drug, List<Supplier>> getSupplierAndDrugs(){
        supplierAndDrugs.clear();

        List<Drug> drugList = DrugController.getAllDrugs();
        List<Supplier> supplierList = getAllSuppliers();

        for (Drug drug : drugList){
            List<Supplier> suppliersOfDrug = new ArrayList<>();
            for (Supplier supplier : supplierList){
                if (Objects.equals(supplier.getId(), parseInt(drug.getSupplierId()))){
                    suppliersOfDrug.add(supplier);
                }
            }
            supplierAndDrugs.put(drug, suppliersOfDrug);
        }
        return supplierAndDrugs;
    }

    public static void deleteSupplier(int supplierID) {
        supplierList.clear();
        
    }

    public static void updateSupplier(Supplier supplier) {
        String query = "UPDATE suppliers SET  name = ?, location = ?, contact = ? WHERE supplier_id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, supplier.getName());
                stmt.setString(2, supplier.getLocation());
                stmt.setString(3, supplier.getContact());
                stmt.setInt(4, supplier.getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static List<Supplier> searchSupplierByDrugAndSupplierData(String drugNameOrID, String supplierInfo) {
        List<Supplier> matchingSuppliers = new ArrayList<>();

        // Iterate over the map entries using an iterator
        Iterator<Map.Entry<Drug, List<Supplier>>> iterator = supplierAndDrugs.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Drug, List<Supplier>> entry = iterator.next();
            Drug drug = entry.getKey();
            List<Supplier> suppliers = entry.getValue();

            // Check if the drug name matches
            if (drugNameOrID.equalsIgnoreCase(drug.getName()) || drugNameOrID.equals(drug.getId())) {
                // Iterate over the suppliers using an iterator
                Iterator<Supplier> supplierIterator = suppliers.iterator();
                while (supplierIterator.hasNext()) {
                    Supplier supplier = supplierIterator.next();
                    // Check if the supplier's location matches
                    if (
                        supplierInfo.equalsIgnoreCase(supplier.getLocation()) ||
                        supplierInfo.equalsIgnoreCase(supplier.getName()) ||
                        supplierInfo.equalsIgnoreCase(supplier.getContact()) ||
                        supplierInfo.equals(Integer.toString(supplier.getId())))
                    {
                        matchingSuppliers.add(supplier);
                    }
                }
            }
        }

        if (matchingSuppliers.isEmpty()) {
            System.out.println("No matching suppliers found for the specified drug and location.");
        } else {
            System.out.println("Matching suppliers found.");
        }

        return matchingSuppliers;
    }

}
