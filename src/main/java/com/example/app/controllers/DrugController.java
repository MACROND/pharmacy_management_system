package com.example.app.controllers;

import com.example.app.entities.Drug;
import com.example.app.utils.DatabaseUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
     * This `getDrugByName` function retrieves a drug from a database by its name.
     * 
     * @param nameOrID The `getDrugByName` method takes a `String` parameter `name`,
     *             which represents the name
     *             of the drug you want to retrieve from the database. The method
     *             then queries the database to find the
     *             drug with the specified name and returns a `Drug` object if it
     *             exists in the database.
     * @return The method `getDrugByName` is returning a `Drug` object based on the
     *         input name provided. If
     *         a drug with the specified name is found in the database, a new `Drug`
     *         object is created using the
     *         data retrieved from the database (drug_id, name, description,
     *         quantity, price, supplier_id) and
     *         returned. If no matching drug is found, it returns `null`.
     */
    public static List<Drug> getDrugByName(String nameOrID) {
        drugList.clear();
        String query = "SELECT * FROM drugs WHERE drug_id= ? OR name= ? ";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, nameOrID);
            stmt.setString(2, nameOrID);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                 Drug searchedDrug = new Drug(rs.getString("drug_id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getInt("quantity"),
                        rs.getDouble("price"),
                        rs.getString("supplier_id")
                 );
                 drugList.add(searchedDrug);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return drugList;
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
        String query = "DELETE FROM drugs WHERE drug_id = ?";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, id);
            stmt.executeUpdate(); // Execute the DELETE statement

        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Update drugList
        return drugList = getAllDrugs();
    }
}
