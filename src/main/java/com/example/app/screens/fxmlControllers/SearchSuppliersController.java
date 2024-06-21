package com.example.app.screens.fxmlControllers;

import com.example.app.controllers.DrugController;
import com.example.app.entities.Drug;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class SearchSuppliersController {

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

    // Initialize method to set up the TableView
    private final ObservableList<Drug> drugList = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        drugsTable.getItems().clear();
        // Set up the columns with their corresponding data properties
        drugIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        supplierIdColumn.setCellValueFactory(new PropertyValueFactory<>("supplierId"));

        drugsTable.getItems().clear();
        drugsTable.setItems(drugList); // Set up the table for viewing the fetchedDrugs
        drugsTable.getItems().clear();

        showAllDrugs();
    }

    @FXML
    public void showAllDrugs() {
        List<Drug> fetchedDrugs = DrugController.getAllDrugs();
        drugsTable.getItems().clear();
        drugsTable.getItems().addAll(fetchedDrugs);
        drugsTable.getItems().clear();
    }
}
