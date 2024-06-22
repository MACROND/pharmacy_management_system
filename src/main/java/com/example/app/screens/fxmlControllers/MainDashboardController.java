package com.example.app.screens.fxmlControllers;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;

public class MainDashboardController {

    @FXML
    private StackPane middleSection;

    public void loadManageDrugSection() {
        loadFXML("/com/example/app/views/ManageDrugs.fxml");
    }

    public void loadManageSalesSection() {
        loadFXML("/com/example/app/views/ManageSales.fxml");
    }

    public void loadManageSupplierSection() {
        loadFXML("/com/example/app/views/ManageSupplier.fxml");
    }


    private void loadFXML(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent newContent = loader.load();
            middleSection.getChildren().clear();
            middleSection.getChildren().add(newContent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
