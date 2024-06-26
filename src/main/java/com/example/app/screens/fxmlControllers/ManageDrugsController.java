package com.example.app.screens.fxmlControllers;

import com.example.app.entities.Drug;
import com.example.app.controllers.DrugController;
import com.example.app.utils.algorithms.Functions;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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

    private DrugController drugController = new DrugController();
    public MainDashboardController mainController;

    public void setMainController(MainDashboardController mainController) {
        this.mainController = mainController;
    }

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

    @FXML
    private void handleDeleteDrug() {
        String id = deleteField.getText();
        mainController.configureTableForDrugs(drugController.deleteDrug(id));

        // Clear the fields after deleting
        deleteField.clear();
    }


    @FXML
    private void handleSearchDrug() {
        String id = searchField.getText();
        mainController.configureTableForDrugs(drugController.getDrugByName(id));

        searchField.clear();
    }

    @FXML
    private void handleViewAllDrugs() {
        SortDrugs(); // Sorts the drugs by id
        updateTableView();
    }

    private void SortDrugs(){
        Functions.sortDrugsByID();
    }
}
