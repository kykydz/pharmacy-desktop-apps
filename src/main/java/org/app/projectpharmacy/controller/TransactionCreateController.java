package org.app.projectpharmacy.controller;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class TransactionCreateController {
    @javafx.fxml.FXML
    private TextField inputTextMedicationName;
    @javafx.fxml.FXML
    private TextArea inputTextAreaNewTrxDescription;
    @javafx.fxml.FXML
    private Button btnNewTrxCreate;
    @javafx.fxml.FXML
    private TextField inputTextNewTrxPrice;
    @javafx.fxml.FXML
    private TextField inputTextNewTrxStock;
    @javafx.fxml.FXML
    private AnchorPane newTrxPane;
    @javafx.fxml.FXML
    private Button btnNewTrxCancel;
}
