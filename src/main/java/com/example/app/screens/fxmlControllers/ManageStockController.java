package com.example.app.screens.fxmlControllers;

import com.example.app.controllers.SaleController;
import com.example.app.controllers.StockController;
import com.example.app.entities.Stock;
import com.example.app.utils.algorithms.Functions;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
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
    private CheckBox sortAmountDesc;
    public CheckBox sortAmountAsc;


    @FXML
    private void handleSearch(){
        String id = searchField.getText();
        mainController.configureTableForStock(SaleController.searchSale(id));
        mainController.clearReportField();

    }

    @FXML
    public void handleDelete(){

    }

    @FXML
    public void handleRefresh(){
        searchField.clear();
        deleteField.clear();
        sortAmountAsc.setSelected(false);
        sortAmountDesc.setSelected(false);
        updateTableView();
        mainController.clearReportField();
    }

    @FXML
    public void handleSortStockAmountLeftDesc(){
        List<Stock> stockList = (List<Stock>) Functions.sortStockByAmountLeftDesc().getFirst();
        String report = (String) Functions.sortStockByAmountLeftDesc().get(1);

        if (sortAmountDesc.isSelected()){
            sortAmountAsc.setSelected(false);
            mainController.configureTableForStock(stockList);
            mainController.configureFieldForGeneratedReport(report);
        } else{
            sortAmountAsc.setSelected(false);
            sortAmountDesc.setSelected(false);
            updateTableView();

        }
    }

    @FXML
    public void handleSortStockAmountLeftAsc(){
        List<Stock> stockList = (List<Stock>) Functions.sortStockByAmountLeftAsc().getFirst();
        String report = (String) Functions.sortStockByAmountLeftAsc().get(1);

        if (sortAmountAsc.isSelected()) {
            sortAmountDesc.setSelected(false);
            mainController.configureTableForStock(stockList);
            mainController.configureFieldForGeneratedReport(report);
        } else{
            sortAmountDesc.setSelected(false);
            sortAmountAsc.setSelected(false);
            updateTableView();
        }
    }

    private void updateTableView(){
        List<Stock> stockList = StockController.getAllStock();
        mainController.configureTableForStock(stockList);
        mainController.clearReportField();
    }


}
