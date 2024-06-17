package com.example.app.controllers;

import com.example.app.entities.User;
import com.example.app.utils.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserController {
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
