package org.app.projectpharmacy.controller;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.app.projectpharmacy.entities.Transaction;
import org.app.projectpharmacy.services.TransactionService;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class TransactionViewController implements Initializable {
    @javafx.fxml.FXML
    private AnchorPane trxHistoryPane;
    @javafx.fxml.FXML
    private TableView<Transaction> tableViewTrxHistory;
    @javafx.fxml.FXML
    private Button btnTrxHistoryClose;
    @javafx.fxml.FXML
    private TableColumn<Transaction, String> tableColTrxHistoryDesc;
    @javafx.fxml.FXML
    private Button btnTrxHistoryFindTrx;
    @javafx.fxml.FXML
    private Label labelTrxHistoryFindResultMssg;
    @javafx.fxml.FXML
    private TableColumn<Transaction, String> tableColTrxHistoryCustomerId;
    @javafx.fxml.FXML
    private TableColumn<Transaction, String> tableColTrxHistoryCreated;
    @javafx.fxml.FXML
    private TableColumn<Transaction, Number> tableColTrxHistoryAmount;
    @javafx.fxml.FXML
    private TextField inputTextTrxHistoryFindTrx;

    private TransactionService transactionService;

    private final ObservableList<Transaction> transactionObservableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // set some control
        labelTrxHistoryFindResultMssg.setVisible(false);

        this._initTableView();
        try {
            transactionService = new TransactionService();
            _clearAndPopulateTableView(null);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnTrxHistoryFindTrx(ActionEvent actionEvent) throws SQLException {
        String customerId = inputTextTrxHistoryFindTrx.getText();
        List<Transaction> transactions = transactionService.findTransactionByCustomerId(customerId);

        _clearAndPopulateTableView(transactions);
    }

    private void _initTableView() {
        tableViewTrxHistory.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        tableColTrxHistoryCreated.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getCreated())));
        tableColTrxHistoryCustomerId.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCustomerId()));
        tableColTrxHistoryAmount.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getTotalPrice()));
        tableColTrxHistoryDesc.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMetaData()));

        tableViewTrxHistory.setItems(transactionObservableList);
    }

    private void _clearAndPopulateTableView(List<Transaction> transactions) throws SQLException {
        tableViewTrxHistory.getItems().clear();
        if (transactions == null || transactions.isEmpty()) {
            transactions = transactionService.fetchAllRecord();
        }
        transactionObservableList.setAll(transactions);
        tableViewTrxHistory.setItems(transactionObservableList);
    }

    public void btnTrxHistoryClose(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }
}
