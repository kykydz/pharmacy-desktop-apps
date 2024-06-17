package org.app.projectpharmacy.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class ScreenLoader {
    public void LoadWindow(FXMLLoader fxmlLoader, Stage stage) throws IOException {
        Scene scene = new Scene(fxmlLoader.load());

        // Get the scene's root node
        Parent root = scene.getRoot();

        // Calculate preferred size based on layout bounds (same as before)
        double prefWidth = root.prefWidth(-1);
        double prefHeight = root.prefHeight(-1);

        // set minimum width and height
        if (prefHeight < 100) prefHeight = 200;
        if (prefWidth < 100) prefWidth = 300;

        // Adjust for window decorations (optional)
        prefWidth += 16;
        prefHeight += 39;

        stage.setTitle("Pharmacy Apps");
        stage.setWidth(prefWidth);
        stage.setHeight(prefHeight);
        stage.setScene(scene);
        stage.show();
    }

    public <T> void LoadChildWindow(FXMLLoader fxmlLoader, Stage primaryStage, T information) throws IOException {
        Scene scene = new Scene(fxmlLoader.load());

        // Get the scene's root node
        Parent root = scene.getRoot();

        // Calculate preferred size based on layout bounds (same as before)
        double prefWidth = root.prefWidth(-1);
        double prefHeight = root.prefHeight(-1);

        // Adjust for window decorations (optional)
        prefWidth += 16;
        prefHeight += 39;

        // Adjust as child stage
        Stage childStage = new Stage();

        childStage.setUserData(information);

        childStage.initOwner(primaryStage);
        childStage.initModality(Modality.APPLICATION_MODAL);
        childStage.setWidth(prefWidth);
        childStage.setHeight(prefHeight);
        childStage.setScene(scene);
        childStage.showAndWait();
    }

    public void setDefaultChildWindowSize(Scene scene, Stage childStage) {
        // Get the scene's root node
        Parent root = scene.getRoot();

        // Calculate preferred size based on layout bounds (same as before)
        double prefWidth = root.prefWidth(-1);
        double prefHeight = root.prefHeight(-1);

        // Adjust for window decorations (optional)
        prefWidth += 16;
        prefHeight += 39;

        childStage.setWidth(prefWidth);
        childStage.setHeight(prefHeight);
    }
}
