package com.example.app.controllers;

import com.example.app.entities.Stock;
import com.example.app.utils.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StockController {

    // Method to create a new stock record
    public void addStock(Stock stock) {
        String query = "INSERT INTO stock (drug_id, name, initial_quantity, quantity_left, amount_sold) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, stock.getDrugId());
            preparedStatement.setString(2, stock.getName());
            preparedStatement.setInt(3, stock.getInitialQuantity());
            preparedStatement.setInt(4, stock.getQuantityLeft());
            preparedStatement.setInt(5, stock.getAmountSold());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to retrieve all stock records
    public List<Stock> getAllStock() {
        List<Stock> stockList = new ArrayList<>();
        String query = "SELECT * FROM stock";

        try (Connection connection = DatabaseUtil.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                Stock stock = new Stock(
                        resultSet.getString("drug_id"),
                        resultSet.getString("name"),
                        resultSet.getInt("initial_quantity"),
                        resultSet.getInt("quantity_left"),
                        resultSet.getInt("amount_sold")
                );
                stockList.add(stock);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return stockList;
    }

    // Method to retrieve a stock record by drug ID
    public Stock getStockById(String drugId) {
        Stock stock = null;
        String query = "SELECT * FROM stock WHERE drug_id = ?";

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, drugId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    stock = new Stock(
                            resultSet.getString("drug_id"),
                            resultSet.getString("name"),
                            resultSet.getInt("initial_quantity"),
                            resultSet.getInt("quantity_left"),
                            resultSet.getInt("amount_sold")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return stock;
    }

    // Method to update a stock record
    public void updateStock(Stock stock) {
        String query = "UPDATE stock SET name = ?, initial_quantity = ?, quantity_left = ?, amount_sold = ? WHERE drug_id = ?";

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, stock.getName());
            preparedStatement.setInt(2, stock.getInitialQuantity());
            preparedStatement.setInt(3, stock.getQuantityLeft());
            preparedStatement.setInt(4, stock.getAmountSold());
            preparedStatement.setString(5, stock.getDrugId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to delete a stock record
    public void deleteStock(String drugId) {
        String query = "DELETE FROM stock WHERE drug_id = ?";

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, drugId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
