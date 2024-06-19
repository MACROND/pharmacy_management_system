package com.example.app.screens.fxmlControllers;

import com.example.app.controllers.PurchaseController;
import com.example.app.entities.Purchase;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.time.LocalDate;

import static java.lang.Integer.parseInt;

public class AddPurchaseController {

    @FXML
    private TableView<Purchase> purchaseTable;  // Specify the generic type
    @FXML
    private TableColumn<Purchase, String> purchaseIdColumn;
    @FXML
    private TableColumn<Purchase, String> drugIdColumn;
    @FXML
    private TableColumn<Purchase, String> customerIDColumn;
    @FXML
    private TableColumn<Purchase, Integer> quantityColumn;
    @FXML
    private TableColumn<Purchase, Double> totalPriceColumn;

    @FXML
    private TextField purchaseIdField;
    @FXML
    private TextField drugIdField;
    @FXML
    private TextField customerIdField;
    @FXML
    private TextField quantityField;
    @FXML
    private TextField totalPriceField;

    private final PurchaseController purchaseController = new PurchaseController();

    @FXML
    private void handleAddPurchase() {
        String id = purchaseIdField.getText();
        String drugId = drugIdField.getText();
        String customerId = customerIdField.getText();
        LocalDate date = LocalDate.now();
        int quantity = parseInt(quantityField.getText());
        double totalPrice = Double.parseDouble(totalPriceField.getText());

        Purchase purchase = new Purchase(parseInt(id), drugId, customerId, date.atStartOfDay(), quantity, totalPrice);
        purchaseController.addPurchase(purchase);

        // Clear the fields after adding
        purchaseIdField.clear();
        drugIdField.clear();
        customerIdField.clear();
        quantityField.clear();
        totalPriceField.clear();
    }
}
