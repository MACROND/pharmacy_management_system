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

public class ViewDrugsController {

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
    private ObservableList<Drug> drugList = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        // Set up the columns with their corresponding data properties
        drugIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        supplierIdColumn.setCellValueFactory(new PropertyValueFactory<>("supplierId"));

        drugsTable.setItems(drugList); // Set up the table for viewing the fetchedDrugs
        drugsTable.getItems().clear(); // Clear already loaded drug item from the table

        boolean empty = drugsTable.getItems().isEmpty();
        if (empty) {
            showAllDrugs();
        }
    }

    @FXML
    public void showAllDrugs() {
        List<Drug> fetchedDrugs = DrugController.getAllDrugs();
        drugsTable.getItems().addAll(fetchedDrugs);
    }
}
