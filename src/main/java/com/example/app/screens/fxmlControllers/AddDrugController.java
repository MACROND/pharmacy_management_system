package com.example.app.screens.fxmlControllers;

import com.example.app.entities.Drug;
import com.example.app.controllers.DrugController;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;


public class AddDrugController {
    @FXML
    public TableColumn dugIdColumn;
    @FXML
    public TableView drugsTable;
    @FXML
    public TableColumn nameColumn;
    @FXML
    public TableColumn descriptionColumn;
    @FXML
    public TableColumn quantityColumn;
    @FXML
    public TableColumn priceColumn;
    @FXML
    public TableColumn supplierIdColumn;


    @FXML
    private TextField idField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField descriptionField;
    @FXML
    private TextField quantityField;
    @FXML
    private TextField priceField;
    @FXML
    private TextField supplierIdField;

    private final DrugController drugController = new DrugController();

    @FXML
    private void handleAddDrug() {
        String id = idField.getText();
        String name = nameField.getText();
        String description = descriptionField.getText();
        int quantity = Integer.parseInt(quantityField.getText());
        double price = Double.parseDouble(priceField.getText());
        String supplierId = supplierIdField.getText();

        Drug drug = new Drug(id, name, description, quantity, price, supplierId);
        drugController.addDrug(drug);

        // Clear the fields after adding
        idField.clear();
        nameField.clear();
        descriptionField.clear();
        quantityField.clear();
        priceField.clear();
        supplierIdField.clear();
    }

    private void handleDeleteDrug(){
        String id = idField.getText();

        drugController.deleteDrug(id);

        // Clear the fields after adding
        idField.clear();
    }
}
