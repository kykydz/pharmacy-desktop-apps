package org.app.projectpharmacy.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.app.projectpharmacy.view.CustomerView;

import java.io.IOException;
import java.util.Objects;

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

    public void btnTrxCreateFindCustomer(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        CustomerView customerView = new CustomerView();
        customerView.start(stage);
    }
}
