package com.example.app;

import com.example.app.screens.LoginScreen;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * The MainApplication class extends Application and sets up the primary stage
 * with a LoginScreen.
 */
public class MainApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        new LoginScreen().start(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
