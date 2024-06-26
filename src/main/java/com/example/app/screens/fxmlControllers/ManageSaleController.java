package com.example.app.screens.fxmlControllers;

import com.example.app.controllers.DrugController;
import com.example.app.controllers.SaleController;
import com.example.app.controllers.StockController;
import com.example.app.entities.Drug;
import com.example.app.entities.Sale;
import com.example.app.entities.Stock;
import com.example.app.utils.algorithms.Functions;
import eu.hansolo.tilesfx.addons.Switch;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static java.lang.Integer.parseInt;


public class ManageSaleController {

    private final SaleController saleController = new SaleController();
    public MainDashboardController mainController;

    public void setMainController(MainDashboardController<Sale> mainController) {
        this.mainController = mainController;
    }

    @FXML
    private TextField drugIdField;
    @FXML
    private TextField customerNameField;
    @FXML
    private TextField customerContactField;
    @FXML
    private TextField quantityField;
    @FXML
    private TextField totalPriceField;
    @FXML
    private TextField searchField;
    @FXML
    private TextField deleteField;


    @FXML
    private void handleAddSale() {
        String drugId = drugIdField.getText();
        String customerName = customerNameField.getText();
        String customerContact = customerContactField.getText();
        LocalDateTime date = LocalDate.now().atStartOfDay();
        int quantity = parseInt(quantityField.getText());

        // Record Sales data
        Drug drug = DrugController.getDrugByName(drugId).getFirst();
        double unitPrice = drug.getPrice();
        double totalPrice = unitPrice * quantity;


        Sale sale = new Sale(drugId, date, quantity, totalPrice,customerName, customerContact);
        saleController.addSale(sale);

        // Update stock data of the sold drug
        Stock stock = StockController.getStockById(drugId).getFirst();
        int quantityLeft = stock.getInitialQuantity() - quantity;
        int amountSold = stock.getAmountSold() + quantity;

        Stock stockUpdate = new Stock(drugId, stock.getName(), stock.getInitialQuantity(), quantityLeft, amountSold,
                date,
                updateStockStatus(drugId, quantityLeft));

        StockController.updateStock(stockUpdate);

        // Clear the fields after adding
        drugIdField.clear();
        customerNameField.clear();
        customerContactField.clear();
        quantityField.clear();
    }

    @FXML
    private void handleViewAllSales(){
        mainController.configureTableForSalesHistory(SaleController.getAllSales());
    }


    @FXML
    private void handleDeleteSale(){
        String id = deleteField.getText();
        SaleController.deleteSale(parseInt(id));

        deleteField.clear();
    }

    @FXML
    private void handleSearchSale(){
        String id = searchField.getText();
        mainController.configureTableForSalesHistory(SaleController.searchSale(id));

    }

    private void updateTableForViewAllSales() {
        mainController.configureTableForSalesHistory(SaleController.getAllSales());
    }

    private String updateStockStatus(String drugId, int quantityLeft) {
        Stock stockData = StockController.getAllStock().getFirst();
        assert stockData != null;
        if (quantityLeft <= 0.1 * stockData.getInitialQuantity()){
            return "Low";
        } else if (quantityLeft <= 0.8 * stockData.getInitialQuantity()){
            return "Moderate";
        } else {
            return "High";
        }
    }
}
