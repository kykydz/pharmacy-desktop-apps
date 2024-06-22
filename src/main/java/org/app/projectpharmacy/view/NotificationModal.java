package org.app.projectpharmacy.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import org.app.projectpharmacy.utils.ScreenLoader;

import java.io.IOException;

public class NotificationModal extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/app/projectpharmacy/customer-view.fxml"));

        ScreenLoader screenLoader = new ScreenLoader();
        screenLoader.LoadChildWindow(fxmlLoader, primaryStage, null);
    }
}
