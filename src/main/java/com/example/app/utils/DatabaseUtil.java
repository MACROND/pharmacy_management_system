package com.example.app.utils;

/*
* Checks connection to sql database
*/
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil {
    private static final String URL = "jdbc:mysql://localhost:3306/pharmacy";
    private static final String USER = "root";
    private static final String PASSWORD = "macrond";

    public static Connection getConnection() throws SQLException {
        System.out.println("Connected to database");
        return DriverManager.getConnection(URL, USER, PASSWORD);

    }
}

