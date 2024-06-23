package com.example.app;

import com.example.app.screens.LoginScreen;
import javafx.application.Application;
import javafx.stage.Stage;
//import com.example.app.controllers.LoginController;

public class MainApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        new LoginScreen().start(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
