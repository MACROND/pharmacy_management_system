package com.example.app.screens;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class LoginScreen extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("MacrondPharmacy - Sign in");

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/app/views/LoginScreen.fxml")));
        Scene scene = new Scene(root, 700, 492);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
