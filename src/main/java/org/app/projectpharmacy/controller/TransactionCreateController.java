package org.app.projectpharmacy.controller;

import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

public class TransactionCreateController {
    @javafx.fxml.FXML
    private AnchorPane trxCreatePane;
    @javafx.fxml.FXML
    private Button btnTrxCreateClose;
    @javafx.fxml.FXML
    private TableColumn tableColTrxCreateStockId;
    @javafx.fxml.FXML
    private TableColumn tableColTrxCreateItemPrice;
    @javafx.fxml.FXML
    private TableView tableViewTrxCreateItems;
    @javafx.fxml.FXML
    private TableColumn tableColTrxCreateItemId;
    @javafx.fxml.FXML
    private TableColumn tableColTrxCreateItemSubTotalPrice;
    @javafx.fxml.FXML
    private TextField textInputTrxCreateCustomerName;
    @javafx.fxml.FXML
    private Button btnTrxCreateNewCustomer;
    @javafx.fxml.FXML
    private Button btnTrxCreateFindCustomer;
    @javafx.fxml.FXML
    private Button btnTrxCreateClose2;
    @javafx.fxml.FXML
    private TextField textInputTrxCreateCustomerId;
    @javafx.fxml.FXML
    private TableColumn tableColTrxCreateItemQuantity;
}
