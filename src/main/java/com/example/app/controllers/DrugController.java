package com.example.app.controllers;

import com.example.app.entities.Drug;
import com.example.app.utils.DatabaseUtil;
import com.example.app.utils.algorithms.Functions;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DrugController {

    public static List<Drug> drugList = new ArrayList<>();

    /**
     * The `addDrug` function inserts a new drug entry into a database table with
     * information such as drug
     * ID, name, description, quantity, price, and supplier ID.
     * 
     * @param drug The `addDrug` method is used to insert a new drug entry into a
     *             database table named
     *             `drugs`. The method takes a `Drug` object as a parameter.
     */
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

    /**
     * The function getAllDrugs retrieves all drug records from the database and
     * returns them as a list of
     * Drug objects.
     * 
     * @return The `getAllDrugs` method returns a List of Drug objects containing
     *         information about drugs
     *         retrieved from the database.
     */
    public static List<Drug> getAllDrugs() {
        drugList.clear();
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
                        rs.getString("supplier_id"));
                drugList.add(drug);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return drugList;
    }

    /**
     * The function `getDrugByName` searches for a drug by name or ID in a list of
     * drugs and returns a
     * report on the search operation.
     * 
     * @param nameOrID The `getDrugByName` method takes a parameter `nameOrID`,
     *                 which is used to search
     *                 for a drug in the drug list based on either the drug's name
     *                 or ID. The method iterates through
     *                 the list of drugs to find a match with the provided
     *                 `nameOrID`.
     * @return The `getDrugByName` method returns a List<Object> containing the drug
     *         that matches the
     *         provided name or ID, along with a report detailing the operation
     *         performed to find the drug. If
     *         the drug is found, the method returns a list with the found drug and
     *         the report. If the drug is
     *         not found, the method returns an empty list with a message indicating
     *         that the drug couldn't be
     *         found.
     */
    public static List<Object> getDrugByName(String nameOrID) {
        drugList.clear();
        drugList = DrugController.getAllDrugs();
        int size = drugList.size();
        List<Object> result = new ArrayList<>();

        double start = System.currentTimeMillis();
        for (Drug drug : drugList) {
            if (nameOrID.equals(drug.getId()) || nameOrID.equals(drug.getName())) {
                drugList.clear();
                double end = System.currentTimeMillis();
                drugList.add(drug);
                System.out.println("Found Drug: " + drug.toString());
                result.add(drugList);
                result.add(Functions.generateReport(
                        "Found the drug\nThis operation was performed, by traversing the dugs collection" +
                                "and comparing the passed drug name or ID to each drug object",
                        start, end, "Ω(1)", "O(n)", size, "Custom Loop"));
                return result;
            }
        }

        System.out.println("Couldn't find drug");
        drugList.clear();
        result.add(drugList);
        result.add("Couldn't find the drug");
        return result;
    }

    /**
     * The `deleteDrug` function deletes a drug from the database based on the
     * provided drug ID and updates
     * the drug list variable with the latest drug list after the deletion.
     * 
     * @param id The `id` parameter in the `deleteDrug` method represents the unique
     *           identifier of the drug
     *           that you want to delete from the database. This identifier is used
     *           to specify which drug record
     *           should be deleted from the `drugs` table in the database.
     */
    public List<Drug> deleteDrug(String id) {
        drugList.clear();
        // Remove drug from drugs collection
        drugList = DrugController.getAllDrugs();

        for (Drug drug : drugList) {
            if (id.equals(drug.getId())) {
                drugList.remove(drug);
                System.out.println("Removed drug from Drugs collection");
            } else {
                System.out.println("Couldn't find drug");
            }
        }

        // Delete drug object from database
        String query = "DELETE FROM drugs WHERE drug_id = ?";

        try (Connection conn = DatabaseUtil.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, id);
            stmt.executeUpdate(); // Execute the DELETE statement
            System.out.println("Removed drug from the database");

        } catch (SQLException e) {
            System.out.println("Couldn't remove drug");
            e.printStackTrace();
        }

        // Update drugList
        return drugList;
    }

    /**
     * The function `updateDrug` updates a drug record in the database with the
     * provided Drug object.
     * 
     * @param drug The `updateDrug` method is used to update a drug entry in a
     *             database table named
     *             `drugs`. The method takes a `Drug` object as a parameter, which
     *             contains information about the drug
     *             to be updated.
     */
    public void updateDrug(Drug drug) {
        String query = "UPDATE drugs SET  drug_id = ?, name = ?, description = ?, quantity = ?, price = ? , supplier_id = ? WHERE drug_id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, drug.getId());
            stmt.setString(2, drug.getName());
            stmt.setString(3, drug.getDescription());
            stmt.setInt(4, drug.getQuantity());
            stmt.setDouble(5, drug.getPrice());
            stmt.setString(6, drug.getSupplierId());
            stmt.setString(7, drug.getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
