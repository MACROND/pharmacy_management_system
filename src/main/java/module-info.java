module com.example.app {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires java.sql;
    requires com.google.protobuf;
    requires java.desktop;

    exports com.example.app.screens to javafx.graphics;

    opens com.example.app to javafx.fxml;

    exports com.example.app;
    exports com.example.app.entities;

    opens com.example.app.entities to javafx.fxml;
    opens com.example.app.screens.fxmlControllers to javafx.fxml;
}