package org.app.projectpharmacy.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.app.projectpharmacy.entities.Customer;
import org.app.projectpharmacy.entities.Stock;
import org.app.projectpharmacy.entities.Transaction;
import org.app.projectpharmacy.entities.TransactionItem;
import org.app.projectpharmacy.services.CustomerService;
import org.app.projectpharmacy.services.StockService;
import org.app.projectpharmacy.services.TransactionService;
import org.app.projectpharmacy.view.CustomerView;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class TransactionCreateController implements Initializable {
    public Button btnTrxCreateSaveRecord;
    public TableColumn<TransactionItem, String> tableColTrxCreateStockName;
    public TableColumn<TransactionItem, Integer> tableColTrxCreateItemPrice;
    public ComboBox<Stock> comboTrxCreateStockList;
    public TextArea textAreaTrxCreateStockDetail;
    public Spinner<Integer> spinnerTrxCreateStock;
    @javafx.fxml.FXML
    private AnchorPane trxCreatePane;
    @javafx.fxml.FXML
    private Button btnTrxCreateClose;
    @javafx.fxml.FXML
    private TableColumn<TransactionItem, String> tableColTrxCreateStockId;
    @javafx.fxml.FXML
    private TableColumn<TransactionItem, Integer> tableColTrxCreateItemQuantity;
    @javafx.fxml.FXML
    private TableView tableViewTrxCreateItems;
    @javafx.fxml.FXML
    private TableColumn<TransactionItem, String> tableColTrxCreateItemId;
    @javafx.fxml.FXML
    private TableColumn<TransactionItem, Integer> tableColTrxCreateItemSubTotalPrice;
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

    private final ObservableList<Stock> initialStockObsList = FXCollections.observableArrayList();
    private Stock currentSelectedComboItem;

    private final ObservableList<TransactionItem> transactionItemObsList = FXCollections.observableArrayList();
    private TransactionService transactionService;
    private final List<TransactionItem> transactionItemList = new ArrayList<>();

    private StockService stockService;

    public void btnTrxCreateFindCustomer(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        CustomerView customerView = new CustomerView();
        customerView.start(stage);
    }

    public void btnTrxCreateSaveRecord(ActionEvent actionEvent) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            stockService = new StockService();
            this._populateComboMedList();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // TODO: complete this method
    private void populateTableView(TransactionItem transactionItem) {
        transactionItemList.add(transactionItem);
        ObservableList<TransactionItem> transactionItemsObsList = FXCollections.observableArrayList();

        // get stock name from stock_id in transactionItem

        transactionItemsObsList.setAll(transactionItemList);

        tableColTrxCreateItemId.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId()));
        tableColTrxCreateStockName.setCellValueFactory(cellData -> {
            try {
                Stock stock = stockService.findStockById(cellData.getValue().getStockId());
                return new SimpleStringProperty(stock.getMedicationName());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    // TODO: complete this method
    private void _populateTableStockList() {
        // fetch all data for first load
        try {
            transactionService = new TransactionService();
            this._clearAndPopulateTableView(null);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // TODO: complete this method
    private void _clearAndPopulateTableView(List<TransactionItem> transactionItems) throws SQLException {
        tableViewTrxCreateItems.getItems().clear();
    }

    private void _populateComboMedList() throws SQLException {
        List<Stock> initialStocks = stockService.fetchAllRecord();
        initialStockObsList.addAll(initialStocks);

        comboTrxCreateStockList.setItems(initialStockObsList);

        comboTrxCreateStockList.setCellFactory(param -> new ListCell<Stock>() {
            @Override
            protected void updateItem(Stock item, boolean empty) {
                super.updateItem(item, empty);
                currentSelectedComboItem = item;

                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getMedicationName());
                }
            }
        });

        // Set renderer for selected item to display only the name
        comboTrxCreateStockList.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(Stock item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                } else {
                    currentSelectedComboItem = item;
                    setText(item.getMedicationName());

                    // set detail text area
                    _setDetailStockTextArea(item);

                    // set min max stock
                    spinnerTrxCreateStock.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,
                            item.getQuantityAvailable()));
                }
            }
        });
    }

    public void btnTrxCreateInputToTableview(ActionEvent actionEvent) {
        // TODO: complete this method to input to table view by call _populateTableStockList
    }

    private void _setDetailStockTextArea(Stock stock) {
        String id = STR."ID: \{stock.getId()} \n";
        String name = STR."Name: \{stock.getMedicationName()} \n";
        String description = STR."Desc: \{stock.getDescription()} \n";
        String price = STR."Price: \{stock.getPrice()} \n";
        String qty = STR."Qty: \{stock.getQuantityAvailable()} \n";
        String text = id + name + description + price + qty;
        textAreaTrxCreateStockDetail.setText(text);
    }
}
