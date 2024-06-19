package com.example.app.screens.fxmlControllers;

import com.example.app.controllers.DrugController;
import com.example.app.entities.Drug;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class SearchPurchaseController {

    @FXML
    private TextField searchField;

    @FXML
    private TableView<Drug> drugsTable;

    @FXML
    private TableColumn<Drug, String> drugIdColumn;

    @FXML
    private TableColumn<Drug, String> nameColumn;

    @FXML
    private TableColumn<Drug, String> descriptionColumn;

    @FXML
    private TableColumn<Drug, Integer> quantityColumn;

    @FXML
    private TableColumn<Drug, Double> priceColumn;

    @FXML
    private TableColumn<Drug, String> supplierIdColumn;

    private ObservableList<Drug> drugList = FXCollections.observableArrayList();

    public void initialize() {
        // Initialize your columns
        drugIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        supplierIdColumn.setCellValueFactory(new PropertyValueFactory<>("supplierId"));

        // Set the items in the TableView
        drugsTable.setItems(drugList); // Set up table to display searched drug
        drugsTable.getItems().addAll(searchDrug());
    }

    public void display() {
        // Initialize your columns
        drugIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        supplierIdColumn.setCellValueFactory(new PropertyValueFactory<>("supplierId"));

        // Set the items in the TableView
        drugsTable.setItems(drugList); // Set up table to display searched drug
        drugsTable.getItems().addAll(searchDrug());
    }

    @FXML
    private List<Drug> searchDrug() {
        String searchTerm = searchField.getText();

        // Simulating search by name or ID
        List<Drug> drugs = DrugController.getAllDrugs();

        if (searchTerm.isEmpty()) {
            return drugs; // Return null if search term is empty
        }

        searchTerm = searchTerm.toLowerCase();

        // Filter drugs by name or ID
        String finalSearchTerm = searchTerm;
        return drugs.stream()
                .filter(drug -> drug.getName().toLowerCase().contains(finalSearchTerm) ||
                        drug.getId().toLowerCase().contains(finalSearchTerm))
                .toList();
    }

}

