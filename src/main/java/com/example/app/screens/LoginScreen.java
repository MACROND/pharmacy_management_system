package com.example.app.screens;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.image.Image;

import java.net.URL;
import java.util.Objects;

/**
 * The LoginScreen class in Java sets up a GUI window for a pharmacy
 * application's sign-in screen.
 */
public class LoginScreen extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            primaryStage.setTitle("MacrondPharmacy");

            // Load the FXML file
            URL fxmlResource = getClass().getResource("/com/example/app/views/LoginScreen.fxml");
            System.out.println("FXML Resource: " + fxmlResource);
            if (fxmlResource == null) {
                throw new NullPointerException("FXML file not found.");
            }
            FXMLLoader loader = new FXMLLoader(fxmlResource);
            Parent root = loader.load();

            // Load the icon image
            URL imageResource = getClass().getResource("/com/example/app/views/images/logo.png");
            System.out.println("Image Resource: " + imageResource);
            if (imageResource != null) {
                Image icon = new Image(imageResource.toString());
                primaryStage.getIcons().add(icon);
            } else {
                System.out.println("Image resource not found.");
            }

            Scene scene = new Scene(root, 700, 492);

            // Bind the root node's size to the stage's size
            if (root instanceof AnchorPane) {
                AnchorPane anchorPane = (AnchorPane) root;
                anchorPane.prefWidthProperty().bind(primaryStage.widthProperty());
                anchorPane.prefHeightProperty().bind(primaryStage.heightProperty());
            }

            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
