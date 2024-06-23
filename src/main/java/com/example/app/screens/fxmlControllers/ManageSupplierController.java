package com.example.app.screens.fxmlControllers;

import com.example.app.controllers.SaleController;
import com.example.app.controllers.SupplierController;
import com.example.app.entities.Sale;
import com.example.app.entities.Supplier;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.util.List;

import static java.lang.Integer.parseInt;

public class ManageSupplierController {
    private final SupplierController supplierController = new SupplierController();

    private MainDashboardController<Supplier> mainController;
    public void setMainController(MainDashboardController<Supplier> mainController) {
        this.mainController = mainController;
    }

    @FXML
    private TextField searchField;
    @FXML
    private TextField deleteField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField locationField;
    @FXML
    private TextField contactField;

    @FXML
    private void handleAddSupplier() {
        String name = nameField.getText();
        String address = locationField.getText();
        String contact = contactField.getText();

        Supplier supplier = new Supplier(name, address, contact);
        supplierController.addSupplier(supplier);
        updateTableView();

        // Clear the fields after adding
        nameField.clear();
        locationField.clear();
        contactField.clear();
    }

    @FXML
    private void handleViewAllSuppliers(){
        updateTableView();
    }

    @FXML
    private void handleSearchSupplier(){
        String id = searchField.getText();
        SupplierController.deleteSupplier(parseInt(id));
        List<Supplier> supplierList = SupplierController.getAllSuppliers();

        searchField.clear();
    }

    @FXML
    private void handleDeleteSupplier(){
        String id = deleteField.getText();
        SupplierController.deleteSupplier(parseInt(id));
        updateTableView();

        deleteField.clear();
    }

    private void updateTableView(){
        List<Supplier> supplierList = SupplierController.getAllSuppliers();
        mainController.configureTableForSuppliers(supplierList);
    }

}