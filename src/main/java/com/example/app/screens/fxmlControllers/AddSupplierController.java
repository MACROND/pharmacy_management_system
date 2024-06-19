package com.example.app.screens.fxmlControllers;

import com.example.app.controllers.SupplierController;
import com.example.app.entities.Supplier;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class AddSupplierController {

    @FXML
    private TableView<?> suppliersTable; // Adjust the types based on your Supplier class
    @FXML
    private TableColumn<?, ?> supplierIdColumn; // Adjust the types based on your Supplier class
    @FXML
    private TableColumn<?, ?> nameColumn; // Adjust the types based on your Supplier class
    @FXML
    private TableColumn<?, ?> addressColumn; // Adjust the types based on your Supplier class
    @FXML
    private TableColumn<?, ?> contactColumn; // Adjust the types based on your Supplier class

    @FXML
    private TextField idField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField locationField;
    @FXML
    private TextField contactField;

    private final SupplierController supplierController = new SupplierController();

    @FXML
    private void handleAddSupplier() {
        String id = idField.getText();
        String name = nameField.getText();
        String address = locationField.getText();
        String contact = contactField.getText();

        Supplier supplier = new Supplier(id, name, address, contact);
        supplierController.addSupplier(supplier);

        // Clear the fields after adding
        idField.clear();
        nameField.clear();
        locationField.clear();
        contactField.clear();
    }
}