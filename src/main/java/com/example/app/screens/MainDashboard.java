package com.example.app.screens;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainDashboard extends Application {
    private BorderPane layout;
    private VBox navButtonsBox;
    private BorderPane rightPane;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Dashboard");

        layout = new BorderPane();
        navButtonsBox = new VBox(10);
        navButtonsBox.setPadding(new Insets(10));

        // Buttons in the left pane for main functionalities
        Button manageDrugsButton = new Button("Drugs");
        manageDrugsButton.setOnAction(e -> showNavButtons("drugs"));

        Button manageSalesButton = new Button("Sales");
        manageSalesButton.setOnAction(e -> showNavButtons("sales"));

        Button managePurchasesButton = new Button("Purchases");
        managePurchasesButton.setOnAction(e -> showNavButtons("purchases"));

        Button manageSuppliersButton = new Button("Suppliers");
        manageSuppliersButton.setOnAction(e -> showNavButtons("suppliers"));

        navButtonsBox.getChildren().addAll(manageDrugsButton, manageSalesButton, managePurchasesButton, manageSuppliersButton);
        layout.setLeft(navButtonsBox);

        // Right pane layout
        rightPane = new BorderPane();
        rightPane.setPadding(new Insets(10));

        layout.setCenter(rightPane);

        Scene scene = new Scene(layout, 1000, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void loadContent(String fxmlFile) {
        try {
            System.out.println("Loading FXML from: " + getClass().getResource(fxmlFile));
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent content = loader.load();
            rightPane.setCenter(content);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showNavButtons(String section) {
        HBox navButtons = new HBox(10);
        navButtons.setPadding(new Insets(10));

        if (section.equals("drugs")) {
            Button addDrugButton = new Button("Add Drug");
            addDrugButton.setOnAction(e -> loadContent("/com/example/app/views/AddDrugs.fxml"));

            Button viewDrugsButton = new Button("View Drugs");
            viewDrugsButton.setOnAction(e -> loadContent("/com/example/app/views/ViewDrugs.fxml"));

            navButtons.getChildren().addAll(addDrugButton, viewDrugsButton);
        } else if (section.equals("sales")) {
            // Add sales related buttons here
        } else if (section.equals("purchases")) {
            // Add purchases related buttons here
        } else if (section.equals("suppliers")) {
            Button addSupplierButton = new Button("Add Supplier");
            addSupplierButton.setOnAction(e -> loadContent("/com/example/app/views/AddSupplier.fxml"));

            Button viewSuppliersButton = new Button("View Suppliers");
            viewSuppliersButton.setOnAction(e -> loadContent("/com/example/app/views/ViewSuppliers.fxml"));

            navButtons.getChildren().addAll(addSupplierButton, viewSuppliersButton);
        }

        rightPane.setTop(navButtons);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
