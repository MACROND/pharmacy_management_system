package com.example.app.screens.fxmlControllers;

import com.example.app.controllers.SaleController;
import com.example.app.controllers.StockController;
import com.example.app.entities.Stock;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

import java.util.List;

public class ManageStockController {

    public MainDashboardController mainController;

<<<<<<< HEAD

=======
>>>>>>> 05098dd413ce01381a2a20a39f7b6f1adc3f5ba9
    public void setMainController(MainDashboardController mainController) {
        this.mainController = mainController;
    }

    @FXML
    private TextField searchField;
    @FXML
    private CheckBox sortAmountDesc;
    public CheckBox sortAmountAsc;

    @FXML
    private void handleSearch() {
        String id = searchField.getText();
        mainController.configureTableForStock(StockController.getStockById(id));
        mainController.clearReportField();

    }

    @FXML
<<<<<<< HEAD
    public void handleRefresh(){
        searchField.clear();
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
=======
    public void handleDelete() {

    }

    @FXML
    public void handleRefresh() {
        updateTableView();
>>>>>>> 05098dd413ce01381a2a20a39f7b6f1adc3f5ba9
    }

    private void updateTableView() {
        List<Stock> stockList = StockController.getAllStock();
<<<<<<< HEAD
=======
        // stocklist = Functions.
>>>>>>> 05098dd413ce01381a2a20a39f7b6f1adc3f5ba9
        mainController.configureTableForStock(stockList);
        mainController.clearReportField();
    }


}
