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
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;
import java.util.Objects;

public class LoginScreen extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Macrond-Login");

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
            User user = UserController.login(userID, password);
            assert user != null;
            System.out.println(user.toString());
            if ("pharmacist".equals(user.getRole())) {
                messageLabel.setText("Authenticated");
                primaryStage.close();
                loadMainDashboard();
            } else {
                messageLabel.setText("Invalid credentials or insufficient permissions.");
            }
        });

        grid.getChildren().addAll(userIDLabel, userIDInput, passwordLabel, passwordInput, loginButton, messageLabel);

        Scene scene = new Scene(grid, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void loadMainDashboard() {
        try {
            Stage dashboardStage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/app/views/MainDashboard.fxml"));
            Parent dashboardRoot = loader.load();
            Scene dashboardScene = new Scene(dashboardRoot, 800, 700 );
            dashboardScene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/example/app/views/style.css")).toExternalForm());
            dashboardStage.setScene(dashboardScene);
            dashboardStage.setTitle("PharMacrond-Dashboard");
            dashboardStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
