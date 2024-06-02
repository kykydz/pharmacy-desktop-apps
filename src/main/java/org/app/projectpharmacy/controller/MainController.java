package org.app.projectpharmacy.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

public class MainController {
    @FXML
    private Label welcomeText;
    @FXML
    private TextField inputTextStockListFindStock;
    @FXML
    private TableColumn tableColStockListMedicationName;
    @FXML
    private TableColumn tableColStockListDescription;
    @FXML
    private TableView tableViewStockList;
    @FXML
    private Button btnCreateStockListNewStock;
    @FXML
    private AnchorPane paneStockList;
    @FXML
    private TableColumn tableColStockListPrice;
    @FXML
    private Button btnStockListFindStock;
    @FXML
    private TableColumn tableColStockListId;
    @FXML
    private TableColumn tableColStockListStock;
    @FXML
    private Button btnCreateStockListNewTransaction;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}