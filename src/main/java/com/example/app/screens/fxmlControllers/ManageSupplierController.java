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

    /**
     * The handleAddSupplier function adds a new supplier to the system, updates the
     * table view, and clears
     * the input fields.
     */
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

    /**
     * The handleViewAllSuppliers function updates the TableView for viewing all
     * suppliers.
     */
    @FXML
    private void handleViewAllSuppliers() {
        updateTableView();
    }

    /**
     * The handleSearchSupplier function retrieves a supplier by ID, clears the
     * supplier list, and adds the
     * retrieved supplier to the list.
     */
    @FXML
    private void handleSearchSupplier() {
        String id = searchField.getText();
        Supplier supplier = SupplierController.getSupplierByID(parseInt(id));
        List<Supplier> supplierList = SupplierController.getAllSuppliers();
        supplierList.clear();
        supplierList.add(supplier);

        searchField.clear();
    }

    /**
     * The handleDeleteSupplier function deletes a supplier based on the ID entered
     * in a text field and
     * updates the table view.
     */
    @FXML
    private void handleDeleteSupplier() {
        String id = deleteField.getText();
        SupplierController.deleteSupplier(parseInt(id));
        updateTableView();

        deleteField.clear();
    }

    /**
     * The function `handleUpdateSupplier` retrieves a supplier by ID, populates
     * text fields with supplier
     * information, and updates the visibility of buttons.
     */
    @FXML
    private void handleUpdateSupplier() {
        int id = parseInt(updateField.getText());
        Supplier supplier = SupplierController.getSupplierByID(id);

        assert supplier != null;
        nameField.setText(supplier.getName());
        locationField.setText(supplier.getLocation());
        contactField.setText(supplier.getContact());

        addSupplierButton.setVisible(false);
        confirmUpdateButton.setVisible(true);

    }

    /**
     * The handleConfirmUpdate function updates a supplier's information in the
     * system and clears the input
     * fields.
     */
    @FXML
    private void handleConfirmUpdate() {
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

    /**
     * The `updateTableView` function retrieves a list of suppliers and updates the
     * table view with the new
     * data.
     */
    private void updateTableView() {
        List<Supplier> supplierList = SupplierController.getAllSuppliers();
        mainController.configureTableForSuppliers(supplierList);
    }

}