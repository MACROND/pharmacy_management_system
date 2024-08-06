package com.example.app.screens.fxmlControllers;

import com.example.app.controllers.StockController;
import com.example.app.entities.Drug;
import com.example.app.controllers.DrugController;
import com.example.app.entities.Stock;
import com.example.app.utils.algorithms.Functions;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.List;

public class ManageDrugsController {

    @FXML
    public TextField searchField;
    @FXML
    public TextField deleteField;
    @FXML
    private TextField idField;
    @FXML
    private TextField nameField;
    @FXML
    private TextArea descriptionField;
    @FXML
    private TextField quantityField;
    @FXML
    private TextField priceField;
    @FXML
    private TextField supplierIdField;
    @FXML
    private TextField updateField;
    @FXML
    public Button addDrugButton;
    @FXML
    private Button confirmUpdateButton;

    private DrugController drugController = new DrugController();
    public MainDashboardController mainController;

    public void setMainController(MainDashboardController mainController) {
        this.mainController = mainController;
    }

    /**
     * The `handleAddDrug` function in Java handles the addition of a new drug by
     * retrieving input
     * values, creating a `Drug` object, updating the stock, refreshing the view,
     * and clearing input
     * fields.
     */
    @FXML
    private void handleAddDrug() {
        String id = idField.getText();
        String name = nameField.getText();
        String description = descriptionField.getText();
        int quantity = Integer.parseInt(quantityField.getText());
        double price = Double.parseDouble(priceField.getText());
        String supplierId = supplierIdField.getText();

        Drug drug = new Drug(id, name, description, quantity, price, supplierId);
        Stock stock = new Stock(id, name, quantity, 0, 0);

        drugController.addDrug(drug);
        StockController.addStock(stock); // Add new drug details to stock table
        updateTableView();

        // Clear the fields after adding
        idField.clear();
        nameField.clear();
        descriptionField.clear();
        quantityField.clear();
        priceField.clear();
        supplierIdField.clear();
    }

    @FXML
    private void updateTableView() {
        List<Drug> drugList = DrugController.getAllDrugs();
        mainController.configureTableForDrugs(drugList);
    }

    /**
     * The handleDeleteDrug function deletes a drug based on the provided ID,
     * updates the table display,
     * clears the report field, and then clears the input field.
     */
    @FXML
    private void handleDeleteDrug() {
        String id = deleteField.getText();
        mainController.configureTableForDrugs(drugController.deleteDrug(id));
        mainController.clearReportField();

        // Clear the fields after deleting
        deleteField.clear();
    }

    /**
     * The handleSearchDrug function retrieves drug information based on a search
     * query and updates the
     * user interface accordingly.
     */

    @FXML
    private void handleSearchDrug() {
        String id = searchField.getText();
        List<Object> result = DrugController.getDrugByName(id);
        List<Drug> drugList = (List<Drug>) result.getFirst();
        String report = (String) result.get(1);
        mainController.configureTableForDrugs(drugList);
        mainController.configureFieldForGeneratedReport(report);

        searchField.clear();
    }

    @FXML
    private void handleViewAllDrugs() {
        SortDrugsByID(); // Sorts the drugs by id
        updateTableView();
    }

    /**
     * The handleUpdateDrug function retrieves drug information based on an ID,
     * populates fields with
     * the data, and configures the UI for updating the drug entry.
     */
    @FXML
    private void handleUpdateDrug() {
        String id = updateField.getText();
        List<Drug> drugs = (List<Drug>) DrugController.getDrugByName(id).getFirst();
        Drug drug = drugs.getFirst();

        String report = (String) DrugController.getDrugByName(id).get(1);

        idField.setText(drug.getId());
        nameField.setText(drug.getName());
        descriptionField.setText(drug.getDescription());
        quantityField.setText(Integer.toString(drug.getQuantity()));
        priceField.setText(Double.toString(drug.getPrice()));
        supplierIdField.setText(drug.getSupplierId());

        addDrugButton.setVisible(false);
        confirmUpdateButton.setVisible(true);
        mainController.configureFieldForGeneratedReport(report);

    }

    // The `handleConfirmUpdate()` method is responsible for updating a drug's
    // information based on the
    // input fields provided by the user. It retrieves the values from the input
    // fields (idField,
    // nameField, descriptionField, quantityField, priceField, supplierIdField),
    // creates a new Drug
    // object with these values, and then updates the drug using the
    // `drugController.updateDrug(drug)`
    // method. It also updates the stock information for the specified drug by
    // creating a new Stock
    // object and updating it using `StockController.updateStock(stock)`.
    @FXML
    private void handleConfirmUpdate() {
        String id = idField.getText();
        String name = nameField.getText();
        String description = descriptionField.getText();
        int quantity = Integer.parseInt(quantityField.getText());
        double price = Double.parseDouble(priceField.getText());
        String supplierId = supplierIdField.getText();

        Drug drug = new Drug(id, name, description, quantity, price, supplierId);
        Stock stock = new Stock(id, name, quantity);
        drugController.updateDrug(drug);
        StockController.updateStock(stock); // Immediately update stock data for the specified drug

        updateTableView();

        // Clear the fields after adding
        idField.clear();
        nameField.clear();
        descriptionField.clear();
        quantityField.clear();
        priceField.clear();
        supplierIdField.clear();

        updateField.clear();
        addDrugButton.setVisible(true);
        confirmUpdateButton.setVisible(false);
        mainController.clearReportField();
    }

    private void SortDrugsByID() {
        String report = Functions.sortDrugsByID();
        mainController.configureFieldForGeneratedReport(report);
    }
}
