package com.example.app.screens.fxmlControllers;

import com.example.app.controllers.SaleController;
import com.example.app.entities.Sale;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.time.LocalDate;
import java.time.LocalDateTime;
import static java.lang.Integer.parseInt;


public class AddSaleController {

    private final SaleController saleController = new SaleController();

    @FXML
    public TableView<Sale> saleTable;  // Specify the generic type
    @FXML
    public TableColumn<Sale, String> saleIdColumn;
    @FXML
    public TableColumn<Sale, String> drugIdColumn;
    @FXML
    public TableColumn<Sale, String> customerNameColumn;
    @FXML
    public TableColumn<Sale, LocalDateTime> dateColumn;
    @FXML
    private TableColumn<Sale, Integer> quantityColumn;
    @FXML
    private TableColumn<Sale, Double> totalPriceColumn;


    @FXML
    private TextField drugIdField;
    @FXML
    private TextField customerNameField;
    @FXML
    private TextField quantityField;
    @FXML
    private TextField totalPriceField;

    @FXML
    private void handleAddSale() {
        String drugId = drugIdField.getText();
        String customerName = customerNameField.getText();
        LocalDate date = LocalDate.now();
        int quantity = parseInt(quantityField.getText());
        double totalPrice = Double.parseDouble(totalPriceField.getText()) * quantity;

        Sale sale = new Sale(drugId, customerName, date.atStartOfDay(), quantity, totalPrice);
        saleController.addSale(sale);

        // Clear the fields after adding
        drugIdField.clear();
        customerNameField.clear();
        quantityField.clear();
        totalPriceField.clear();
    }
}
