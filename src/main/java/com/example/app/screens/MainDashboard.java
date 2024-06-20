package com.example.app.screens;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainDashboard extends Application {
    private BorderPane layout;
    private BorderPane bottomPane;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("PharMacrond-Dashboard");

        layout = new BorderPane();

        // Create a MenuBar
        MenuBar menuBar = new MenuBar();

        // Drugs menu
        Menu drugsMenu = new Menu("Drugs");
        MenuItem addDrugItem = new MenuItem("Add Drug");
        addDrugItem.setOnAction(e -> loadContent("/com/example/app/views/AddDrugs.fxml"));
        MenuItem viewDrugsItem = new MenuItem("View Drugs");
        viewDrugsItem.setOnAction(e -> loadContent("/com/example/app/views/ViewDrugs.fxml"));
        MenuItem searchDrugItem = new MenuItem("Search Drug");
        searchDrugItem.setOnAction((e -> loadContent("/com/example/app/views/SearchDrug.fxml")));
        drugsMenu.getItems().addAll(addDrugItem, viewDrugsItem, searchDrugItem);

        // Sales menu
        Menu salesMenu = new Menu("Sales");
        MenuItem addSalesItem = new MenuItem("Add Sales");
        addSalesItem.setOnAction(e -> loadContent("/com/example/app/views/AddSale.fxml"));
        MenuItem viewSalesItem = new MenuItem("View Sales");
        viewSalesItem.setOnAction(e -> loadContent("/com/example/app/views/ViewSales.fxml"));
        salesMenu.getItems().addAll(addSalesItem, viewSalesItem);

        // Suppliers menu
        Menu suppliersMenu = new Menu("Suppliers");
        MenuItem addSuppliersItem = new MenuItem("Add Suppliers");
        addSuppliersItem.setOnAction(e -> loadContent("/com/example/app/views/AddSupplier.fxml"));
        MenuItem viewSuppliersItem = new MenuItem("View Suppliers");
        viewSuppliersItem.setOnAction(e -> loadContent("/com/example/app/views/ViewSuppliers.fxml"));
        suppliersMenu.getItems().addAll(addSuppliersItem, viewSuppliersItem);

        // Add menus to the MenuBar
        menuBar.getMenus().addAll(drugsMenu, salesMenu, suppliersMenu);

        // Set the MenuBar at the top of the layout
        layout.setTop(menuBar);

        // Buttom pane layout
        bottomPane = new BorderPane();
        bottomPane.setPadding(new Insets(10));

        layout.setCenter(bottomPane);

        Scene scene = new Scene(layout, 1000, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void loadContent(String fxmlFile) {
        try {
            System.out.println("Loading FXML from: " + getClass().getResource(fxmlFile));
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent content = loader.load();
            bottomPane.setCenter(content);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
