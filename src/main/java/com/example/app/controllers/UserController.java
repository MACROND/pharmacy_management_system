package com.example.app.controllers;

import com.example.app.entities.User;
import com.example.app.utils.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserController {
/**
 * The `login` function attempts to retrieve a user from the database based on the provided user ID and
 * password, returning a `User` object if found.
 * 
 * @param user_id The `user_id` parameter in the `login` method is used to specify the user ID of the
 * user trying to log in. This user ID is then used in the SQL query to retrieve the user's information
 * from the database.
 * @param password The `login` method you provided is a Java method that attempts to authenticate a
 * user by querying the database with the provided `user_id` and `password`. If a matching user is
 * found in the database, it creates a new `User` object with the user's information and returns it.
 * @return The `login` method is returning a `User` object if the user with the provided `user_id` and
 * `password` is found in the database. If a matching user is found, a new `User` object is created
 * using the user's information (user_id, firstName, lastName, password, role) retrieved from the
 * database and returned. If no matching user is found, the method
 */
    public static User login(String user_id, String password) {
        try (Connection conn = DatabaseUtil.getConnection()) {
            String sql = "SELECT * FROM users WHERE user_id = ? AND password = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, user_id);
                stmt.setString(2, password);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    System.out.println("Getting user");
                    return new User(rs.getString("user_id"), rs.getString("firstName"), rs.getString("lastName"), rs.getString("password"), rs.getString("role"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
