package org.app.projectpharmacy.controller;

public class TransactionViewController {
    @javafx.fxml.FXML
    private AnchorPane trxHistoryPane;
    @javafx.fxml.FXML
    private TableView tableViewTrxHistory;
    @javafx.fxml.FXML
    private Button btnTrxHistoryClose;
    @javafx.fxml.FXML
    private TableColumn tableColTrxHistoryDesc;
    @javafx.fxml.FXML
    private Button btnTrxHistoryFindTrx;
    @javafx.fxml.FXML
    private Label labelTrxHistoryFindResultMssg;
    @javafx.fxml.FXML
    private TableColumn tableColTrxHistoryMedicationName;
    @javafx.fxml.FXML
    private TableColumn tableColTrxHistoryCreated;
    @javafx.fxml.FXML
    private TableColumn tableColTrxHistoryAmount;
    @javafx.fxml.FXML
    private TextField inputTextTrxHistoryFindTrx;
    @javafx.fxml.FXML
    private TableColumn tableColTrxHistoryPrice;
}
