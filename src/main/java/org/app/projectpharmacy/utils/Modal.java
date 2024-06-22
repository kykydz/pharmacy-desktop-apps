package org.app.projectpharmacy.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.app.projectpharmacy.controller.NotificationModalController;

import java.io.IOException;

public class Modal {
    public void showNotification(String notifTitle, String notifMessage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/app/projectpharmacy/notification-modal.fxml"));
        Parent childRoot = loader.load();

        Stage childStage = new Stage();
        Scene childScene = new Scene(childRoot);

        NotificationModalController notificationModalController = loader.getController();
        notificationModalController.setContent(notifTitle, notifMessage);

        new ScreenLoader().setDefaultChildWindowSize(childScene, childStage);
        childStage.setTitle("Create New Stock");
        childStage.setScene(childScene);
        childStage.initModality(Modality.APPLICATION_MODAL);
        childStage.showAndWait();
    }
}
