package com.example.app.screens.fxmlControllers;


import com.example.app.entities.Drug;
import com.example.app.controllers.DrugController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ViewDrugsController {
    @FXML
    private TableView<Drug> drugsTable;
    @FXML
    private TableColumn<Drug, String> idColumn;
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

    private final DrugController drugController = new DrugController();

    @FXML
    private void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        supplierIdColumn.setCellValueFactory(new PropertyValueFactory<>("supplierId"));

        ObservableList<Drug> drugs = FXCollections.observableArrayList(drugController.getAllDrugs());
        drugsTable.setItems(drugs);
    }
}
