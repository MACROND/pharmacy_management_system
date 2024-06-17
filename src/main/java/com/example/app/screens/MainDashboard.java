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
        Button manageDrugsButton = new Button("Manage Drugs");
        manageDrugsButton.setOnAction(e -> showNavButtons("drugs"));

        Button manageSalesButton = new Button("Manage Sales");
        manageSalesButton.setOnAction(e -> showNavButtons("sales"));

        navButtonsBox.getChildren().addAll(manageDrugsButton, manageSalesButton);
        layout.setLeft(navButtonsBox);

        // Right pane layout
        rightPane = new BorderPane();
        rightPane.setPadding(new Insets(10));

        layout.setCenter(rightPane);

        Scene scene = new Scene(layout, 1000, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showNavButtons(String section) {
        HBox navButtons = new HBox(10);
        navButtons.setPadding(new Insets(10));

        if (section.equals("drugs")) {
            Button addDrugButton = new Button("Add Drug");
            addDrugButton.setOnAction(e -> loadContent("AddDrug.fxml"));

            Button viewDrugsButton = new Button("View Drugs");
            viewDrugsButton.setOnAction(e -> loadContent("ViewDrugs.fxml"));

            navButtons.getChildren().addAll(addDrugButton, viewDrugsButton);
        }
//        else if (section.equals("sales")) {
//            // Add sales related buttons here
//
//        }

        rightPane.setTop(navButtons);
    }

    private void loadContent(String fxmlFile) {
        try {
            Parent content = FXMLLoader.load(getClass().getResource(fxmlFile));
            rightPane.setCenter(content);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
