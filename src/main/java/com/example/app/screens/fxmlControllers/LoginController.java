package com.example.app.screens.fxmlControllers;

import com.example.app.entities.User;
import com.example.app.controllers.UserController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class LoginController {

    @FXML
    private TextField userIDInput;

    @FXML
    private PasswordField passwordInput;

    @FXML
    private Button loginButton;

    @FXML
    private Label messageLabel;


    public void initialize() {
        loginButton.setOnAction(e -> handleLogin());
    }

    @FXML
    private void handleLogin() {
        String userID = userIDInput.getText();
        String password = passwordInput.getText();
        User user = UserController.login(userID, password);

        if (user != null && "pharmacist".equals(user.getRole())) {
            messageLabel.setText("Authenticated");
            Stage stage = (Stage) loginButton.getScene().getWindow();
            stage.close();
            loadMainDashboard();
        } else {
            messageLabel.setText("Invalid credentials or insufficient permissions.");
        }
    }

    private void loadMainDashboard() {
        System.out.println("Loading dashboard");
        try {
            Stage dashboardStage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/app/views/MainDashboard.fxml"));
            Parent dashboardRoot = loader.load();

            // Load the icon image
            URL imageResource = getClass().getResource("/com/example/app/views/images/logo.png");
            System.out.println("Image Resource: " + imageResource);
            if (imageResource != null) {
                Image icon = new Image(imageResource.toString());
                dashboardStage.getIcons().add(icon);
            } else {
                System.out.println("Image resource not found.");
            }

            Scene dashboardScene = new Scene(dashboardRoot, 1200, 700  );
            dashboardStage.setScene(dashboardScene);
            dashboardStage.setTitle("PharMacrond-Dashboard");
            dashboardStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
