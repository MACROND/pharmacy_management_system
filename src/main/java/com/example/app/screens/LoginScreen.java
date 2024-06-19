package com.example.app.screens;

import com.example.app.controllers.UserController;
import com.example.app.entities.User;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class LoginScreen extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Pharmacrond-Login");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        Label userIDLabel = new Label("User ID:");
        GridPane.setConstraints(userIDLabel, 0, 0);

        TextField userIDInput = new TextField();
        GridPane.setConstraints(userIDInput, 1, 0);

        Label passwordLabel = new Label("Password:");
        GridPane.setConstraints(passwordLabel, 0, 1);

        PasswordField passwordInput = new PasswordField();
        GridPane.setConstraints(passwordInput, 1, 1);

        Button loginButton = new Button("Login");
        GridPane.setConstraints(loginButton, 1, 2);

        Label messageLabel = new Label();
        GridPane.setConstraints(messageLabel, 1, 3);

        loginButton.setOnAction(e -> {
            String userID = userIDInput.getText();
            String password = passwordInput.getText();
            User user = UserController.login(userID, password); // User login functionality
            assert user != null;
            System.out.println(user.toString());
            if (user != null && "pharmacist".equals(user.getRole())) {
                new MainDashboard().start(new Stage());
                messageLabel.setText("Authenticated");
                primaryStage.close();
            } else {
                messageLabel.setText("Invalid credentials or insufficient permissions.");
            }
        });

        grid.getChildren().addAll(userIDLabel, userIDInput, passwordLabel, passwordInput, loginButton, messageLabel);

        Scene scene = new Scene(grid, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

