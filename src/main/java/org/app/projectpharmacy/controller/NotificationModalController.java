package org.app.projectpharmacy.controller;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class NotificationModalController {
    @javafx.fxml.FXML
    private Button buttonNotifOk;
    @javafx.fxml.FXML
    private Label labelNotifMessage;
    @javafx.fxml.FXML
    private Label labelNotifTitle;

    private String notifTittle = "CREATION SUCCESS";
    private String notifMessage = "";

    public void setContent(String notifTitle, String notifMessage) {
        labelNotifTitle.setText(notifTitle);
        labelNotifMessage.setText(notifMessage);
    }

    public void onButtonNotifOk(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }
}
