package org.app.projectpharmacy.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import org.app.projectpharmacy.utils.ScreenLoader;

import java.io.IOException;

public class TransactionCreate extends Application {

    private Object primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/app/projectpharmacy/transaction-create.fxml"));

        ScreenLoader screenLoader = new ScreenLoader();
        screenLoader.LoadChildWindow(fxmlLoader, primaryStage);
    }
}
