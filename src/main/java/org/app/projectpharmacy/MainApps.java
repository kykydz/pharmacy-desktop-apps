package org.app.projectpharmacy;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import org.app.projectpharmacy.utils.ScreenLoader;

import java.io.IOException;

public class MainApps extends Application {
    private Stage primaryStage;

    @Override
    public void start(Stage stage) throws IOException {
        this.primaryStage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/app/projectpharmacy/main-apps.fxml"));

        ScreenLoader screenLoader = new ScreenLoader();
        screenLoader.LoadWindow(fxmlLoader, primaryStage);
    }

    public static void main(String[] args) {
        launch();
    }
}