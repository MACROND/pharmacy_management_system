package com.example.app.screens.fxmlControllers;

import com.example.app.controllers.DrugController;
import com.example.app.controllers.SaleController;
import com.example.app.controllers.SupplierController;
import com.example.app.entities.Drug;
import com.example.app.entities.Sale;
import com.example.app.entities.Supplier;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
    private TextField updateField;
    @FXML
    private Button confirmUpdateButton;
    @FXML
    private Button addSupplierButton;

    @FXML
    private void handleAddSupplier() {
        String name = nameField.getText();
        String location = locationField.getText();
        String contact = contactField.getText();

        Supplier supplier = new Supplier(name, location, contact);
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
        Supplier supplier = SupplierController.getSupplierByID(parseInt(id));
        List<Supplier> supplierList = SupplierController.getAllSuppliers();
        supplierList.clear();
        supplierList.add(supplier);

        searchField.clear();
    }

    @FXML
    private void handleDeleteSupplier(){
        String id = deleteField.getText();
        SupplierController.deleteSupplier(parseInt(id));
        updateTableView();

        deleteField.clear();
    }

    @FXML
    private void handleUpdateSupplier(){
        int id = parseInt(updateField.getText());
        Supplier supplier = SupplierController.getSupplierByID(id);

        assert supplier != null;
        nameField.setText(supplier.getName());
        locationField.setText(supplier.getLocation());
        contactField.setText(supplier.getContact());

        addSupplierButton.setVisible(false);
        confirmUpdateButton.setVisible(true);

    }

    @FXML
    private void handleConfirmUpdate(){
        int id = parseInt(updateField.getText());
        String name = nameField.getText();
        String location = locationField.getText();
        String contact = contactField.getText();

        Supplier supplier = new Supplier(id, name, location, contact);
        SupplierController.updateSupplier(supplier);

        updateTableView();

        // Clear the fields after adding
        nameField.clear();
        locationField.clear();
        contactField.clear();

        updateField.clear();
        addSupplierButton.setVisible(true);
        confirmUpdateButton.setVisible(false);
    }

    private void updateTableView(){
        List<Supplier> supplierList = SupplierController.getAllSuppliers();
        mainController.configureTableForSuppliers(supplierList);
    }

}