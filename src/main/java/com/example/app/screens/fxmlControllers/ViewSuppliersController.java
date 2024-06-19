package com.example.app.screens.fxmlControllers;

import com.example.app.controllers.SupplierController;
import com.example.app.entities.Supplier;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class ViewSuppliersController {

    @FXML private TableView<Supplier> supplierTable;

    @FXML private TableColumn<Supplier, String> idColumn;

    @FXML private TableColumn<Supplier, String> nameColumn;

    @FXML private TableColumn<Supplier, String> locationColumn;

    @FXML private TableColumn<Supplier, String> contactColumn;

    // Initialize method to set up the TableView
    private final ObservableList<Supplier> suppliersList = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        contactColumn.setCellValueFactory(new PropertyValueFactory<>("supplierId"));

        supplierTable.getItems().clear();
        supplierTable.setItems(suppliersList); // Set up the table for viewing the fetchedSuppliers
        showAllSuppliers();
    }

    @FXML
    public void showAllSuppliers() {
        List<Supplier> fetchedSuppliers = SupplierController.getAllSuppliers();
        supplierTable.getItems().clear();
        supplierTable.getItems().addAll(fetchedSuppliers);
    }
}
