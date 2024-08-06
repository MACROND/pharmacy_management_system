package com.example.app.screens.fxmlControllers;

import com.example.app.utils.algorithms.Functions;
import com.example.app.controllers.StockController;
import com.example.app.entities.Stock;
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
    private CheckBox sortAmountDesc;
    public CheckBox sortAmountAsc;

    /**
     * The handleSearch function retrieves a stock ID from a search field, uses it
     * to configure a table in
     * the main controller, and clears a report field.
     */
    @FXML
    private void handleSearch() {
        String id = searchField.getText();
        mainController.configureTableForStock(StockController.getStockById(id));
        mainController.clearReportField();

    }

    /**
     * The `handleRefresh` function clears search field, deselects sorting options,
     * updates table view,
     * and clears report field in a JavaFX application.
     */
    @FXML
    public void handleRefresh() {
        searchField.clear();
        sortAmountAsc.setSelected(false);
        sortAmountDesc.setSelected(false);
        updateTableView();
        mainController.clearReportField();
    }

    /**
     * The function `handleSortStockAmountLeftDesc` sorts a list of Stock objects by
     * amount left in
     * descending order and updates the UI accordingly.
     */
    @FXML
    public void handleSortStockAmountLeftDesc() {
        List<Stock> stockList = (List<Stock>) Functions.sortStockByAmountLeftDesc().getFirst();
        String report = (String) Functions.sortStockByAmountLeftDesc().get(1);

        if (sortAmountDesc.isSelected()) {
            sortAmountAsc.setSelected(false);
            mainController.configureTableForStock(stockList);
            mainController.configureFieldForGeneratedReport(report);
        } else {
            sortAmountAsc.setSelected(false);
            sortAmountDesc.setSelected(false);
            updateTableView();

        }
    }

    /**
     * The handleSortStockAmountLeftAsc function sorts a list of Stock objects by
     * the amount left in
     * ascending order and updates the UI accordingly.
     */
    @FXML
    public void handleSortStockAmountLeftAsc() {
        List<Stock> stockList = (List<Stock>) Functions.sortStockByAmountLeftAsc().getFirst();
        String report = (String) Functions.sortStockByAmountLeftAsc().get(1);

        if (sortAmountAsc.isSelected()) {
            sortAmountDesc.setSelected(false);
            mainController.configureTableForStock(stockList);
            mainController.configureFieldForGeneratedReport(report);
        } else {
            sortAmountDesc.setSelected(false);
            sortAmountAsc.setSelected(false);
            updateTableView();
        }
    }

    /**
     * The `updateTableView` method retrieves a list of stock items, configures a
     * table with the stock
     * data, and clears a report field in the main controller.
     */
    private void updateTableView() {
        List<Stock> stockList = StockController.getAllStock();
        mainController.configureTableForStock(stockList);
        mainController.clearReportField();
    }

}
