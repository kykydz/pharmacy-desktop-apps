package org.app.projectpharmacy.controller;

import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

public class CustomerViewController {
    @javafx.fxml.FXML
    private Button btnCustomerViewFindTrx;
    @javafx.fxml.FXML
    private Label labelCustomerViewFindResultMssg;
    @javafx.fxml.FXML
    private AnchorPane customerViewPane;
    @javafx.fxml.FXML
    private TextField inputTextCustomerViewFindTrx;
    @javafx.fxml.FXML
    private Button btnCustomerViewClose;
    @javafx.fxml.FXML
    private TableColumn tableColCustomerViewCreated;
    @javafx.fxml.FXML
    private TableColumn tableColCustomerViewDesc;
    @javafx.fxml.FXML
    private TableView tableViewCustomerView;
    @javafx.fxml.FXML
    private TableColumn tableColCustomerViewName;
    @javafx.fxml.FXML
    private TableColumn tableColCustomerViewUpdated;
    @javafx.fxml.FXML
    private TableColumn tableColCustomerViewWANumber;
    @javafx.fxml.FXML
    private TableColumn tableColCustomerViewId;
    @javafx.fxml.FXML
    private TableColumn tableColCustomerViewEmail;
}
