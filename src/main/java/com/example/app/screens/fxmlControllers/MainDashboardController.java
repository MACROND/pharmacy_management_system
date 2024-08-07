package com.example.app.screens.fxmlControllers;
import com.example.app.entities.Drug;
import com.example.app.entities.Sale;
import com.example.app.entities.Stock;
import com.example.app.entities.Supplier;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

import java.time.LocalDateTime;
import java.util.List;

public class MainDashboardController<T> {
    // Loading Middle section components dynamically
    @FXML
    private Pane middleSection;

    @FXML
    private TextArea report;

    public void loadManageDrugSection() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/app/views/ManageDrugs.fxml"));
            Parent newContent = loader.load();

            ManageDrugsController manageDrugsController = loader.getController();
            manageDrugsController.setMainController(this);

            bottomTableView.getColumns().clear();
            bottomTableView.getItems().clear();

            middleSection.getChildren().clear();
            middleSection.getChildren().add(newContent);

            report.clear();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadManageSalesSection(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/app/views/ManageSales.fxml"));
            Parent newContent = loader.load();

            ManageSaleController manageSaleController = loader.getController();
            manageSaleController.setMainController((MainDashboardController<Sale>) this);

            bottomTableView.getColumns().clear();
            bottomTableView.getItems().clear();

            middleSection.getChildren().clear();
            middleSection.getChildren().add(newContent);

            report.clear();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadManageSupplierSection(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/app/views/ManageSupplier.fxml"));
            Parent newContent = loader.load();

            ManageSupplierController manageSupplierController = loader.getController();
            manageSupplierController.setMainController((MainDashboardController<Supplier>) this);

            bottomTableView.getColumns().clear();
            bottomTableView.getItems().clear();

            middleSection.getChildren().clear();
            middleSection.getChildren().add(newContent);

            report.clear();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadManageStockSection(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/app/views/ManageStock.fxml"));
            Parent newContent = loader.load();

            ManageStockController manageStockController = loader.getController();
            manageStockController.setMainController((MainDashboardController<Stock>) this);

            bottomTableView.getColumns().clear();
            bottomTableView.getItems().clear();

            middleSection.getChildren().clear();
            middleSection.getChildren().add(newContent);

            report.clear();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Loading TableView data dynamically
    @FXML
    private TableView<T> bottomTableView;


    @FXML
    private void initialize() {
        System.out.println("MainDashboardController initialized");
        if (bottomTableView == null) {
            System.out.println("bottomTableView is null in initialize");
        } else {
            System.out.println("bottomTableView initialized");
        }
    }

    public void configureTableForDrugs(List<Drug> drugList) {
        bottomTableView.getColumns().clear();
        bottomTableView.getItems().clear();

        TableColumn<Drug, String> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn<Drug, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<Drug, String> descCol = new TableColumn<>("Description");
        descCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        TableColumn<Drug, Integer> quantityCol = new TableColumn<>("Quantity");
        quantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        TableColumn<Drug, Double> priceCol = new TableColumn<>("Price");
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        TableColumn<Drug, String> supplierIdCol = new TableColumn<>("Supplier ID");
        supplierIdCol.setCellValueFactory(new PropertyValueFactory<>("supplierId"));

        bottomTableView.getColumns().addAll(
                (TableColumn<T, ?>)idCol,
                (TableColumn<T, ?>)nameCol,
                (TableColumn<T, ?>)descCol,
                (TableColumn<T, ?>)quantityCol,
                (TableColumn<T, ?>)priceCol,
                (TableColumn<T, ?>)supplierIdCol
        );
        ObservableList<Drug> observableList = FXCollections.observableArrayList(drugList);
        bottomTableView.setItems((ObservableList<T>) observableList);
    }


    public void configureTableForSalesHistory(List<Sale> salesHistoryList) {
        bottomTableView.getColumns().clear();
        bottomTableView.getItems().clear();

        TableColumn<Sale, String> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn<Sale, String> drugIdCol = new TableColumn<>("Drug ID");
        drugIdCol.setCellValueFactory(new PropertyValueFactory<>("drugId"));
        TableColumn<Sale, Integer> quantityCol = new TableColumn<>("Quantity");
        quantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        TableColumn<Sale, Double> totalPriceCol = new TableColumn<>("Total Price");
        totalPriceCol.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
        TableColumn<Sale, String> saleDateCol = new TableColumn<>("Sale Date");
        saleDateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        TableColumn<Sale, String> customerNameCol = new TableColumn<>("Customer");
        customerNameCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        TableColumn<Sale, String> customerContactCol = new TableColumn<>("Contact");
        customerContactCol.setCellValueFactory(new PropertyValueFactory<>("customerContact"));


        bottomTableView.getColumns().addAll(
                (TableColumn<T, ?>) idCol,
                (TableColumn<T, ?>) drugIdCol,
                (TableColumn<T, ?>) quantityCol,
                (TableColumn<T, ?>) totalPriceCol,
                (TableColumn<T, ?>) saleDateCol,
                (TableColumn<T, ?>) customerNameCol,
                (TableColumn<T, ?>) customerContactCol
        );
        ObservableList<Sale> observableList = FXCollections.observableArrayList(salesHistoryList);
        bottomTableView.setItems((ObservableList<T>) observableList);
    }

    public void configureTableForSuppliers(List<Supplier> supplierList) {
        bottomTableView.getColumns().clear();
        bottomTableView.getItems().clear();

        TableColumn<Supplier, String> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn<Supplier, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Supplier, String> locationCol = new TableColumn<>("Location");
        locationCol.setCellValueFactory(new PropertyValueFactory<>("location"));

        TableColumn<Supplier, String> contactCol = new TableColumn<>("Contact");
        contactCol.setCellValueFactory(new PropertyValueFactory<>("contact"));

        bottomTableView.getColumns().addAll(
                (TableColumn<T, ?>) idCol,
                (TableColumn<T, ?>) nameCol,
                (TableColumn<T, ?>) locationCol,
                (TableColumn<T, ?>) contactCol
        );
        ObservableList<Supplier> observableList = FXCollections.observableArrayList(supplierList);
        bottomTableView.setItems((ObservableList<T>) observableList);
    }

    public void configureTableForStock(List<Stock> stockData) {
        bottomTableView.getColumns().clear();
        bottomTableView.getItems().clear();

        TableColumn<Stock, String> idCol = new TableColumn<>("Drug ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("drugId"));

        TableColumn<Stock, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Stock, Integer> initialQuantityCol = new TableColumn<>("Initial");
        initialQuantityCol.setCellValueFactory(new PropertyValueFactory<>("initialQuantity"));

        TableColumn<Stock, Integer> quantitySoldCol = new TableColumn<>("Sold");
        quantitySoldCol.setCellValueFactory(new PropertyValueFactory<>("amountSold"));

        TableColumn<Stock, Integer> quantityLeftCol = new TableColumn<>("Left");
        quantityLeftCol.setCellValueFactory(new PropertyValueFactory<>("quantityLeft"));

        TableColumn<Stock, LocalDateTime> lastUpdatedCol = new TableColumn<>("Last Updated");
        lastUpdatedCol.setCellValueFactory(new PropertyValueFactory<>("lastUpdated"));

        TableColumn<Stock, String> status = new TableColumn<>("Status");
        status.setCellValueFactory(new PropertyValueFactory<>("status"));

        bottomTableView.getColumns().addAll(
                (TableColumn<T, ?>) idCol,
                (TableColumn<T, ?>) nameCol,
                (TableColumn<T, ?>) initialQuantityCol,
                (TableColumn<T, ?>) quantitySoldCol,
                (TableColumn<T, ?>) quantityLeftCol,
                (TableColumn<T, ?>) lastUpdatedCol,
                (TableColumn<T, ?>) status
        );
        ObservableList<Stock> observableList = FXCollections.observableArrayList(stockData);
        bottomTableView.setItems((ObservableList<T>) observableList);
    }

    public void configureFieldForGeneratedReport(String genReport){
        report.clear();
        report.setText(genReport);
    }

    public void clearReportField(){
        report.clear();
    }
}
