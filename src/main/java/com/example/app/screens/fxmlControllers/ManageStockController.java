package com.example.app.screens.fxmlControllers;

import com.example.app.controllers.SaleController;
import com.example.app.controllers.StockController;
import com.example.app.entities.Stock;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.util.List;

public class ManageStockController {

    public MainDashboardController mainController;
    public void setMainController(MainDashboardController mainController) {
        this.mainController = mainController;
    }

    @FXML
    private TextField searchField;
    @FXML
    private TextField deleteField;


    @FXML
    private void handleSearch(){
        String id = searchField.getText();
        mainController.configureTableForStock(SaleController.searchSale(id));

    }

    @FXML
    public void handleDelete(){

    }

    @FXML
    public void handleRefresh(){
        updateTableView();
    }

    private void updateTableView(){
        List<Stock> stockList = StockController.getAllStock();
        mainController.configureTableForStock(stockList);
    }
}
