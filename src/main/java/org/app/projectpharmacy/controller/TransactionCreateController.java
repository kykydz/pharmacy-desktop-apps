package org.app.projectpharmacy.controller;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.app.projectpharmacy.entities.Customer;
import org.app.projectpharmacy.entities.Stock;
import org.app.projectpharmacy.entities.TransactionItem;
import org.app.projectpharmacy.services.StockService;
import org.app.projectpharmacy.services.TransactionService;
import org.app.projectpharmacy.utils.ScreenLoader;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;

public class TransactionCreateController implements Initializable {
    public Button btnTrxCreateSaveRecord;
    public TableColumn<TransactionItem, String> tableColTrxCreateStockName;
    public TableColumn<TransactionItem, Number> tableColTrxCreateItemPrice;
    public ComboBox<Stock> comboTrxCreateStockList;
    public TextArea textAreaTrxCreateStockDetail;
    public Spinner<Integer> spinnerTrxCreateStock;
    public TextField textInputTrxCreateTotalPrice;
    public TextArea textAreaTrxCreateCustomerDetail;
    public Button btnTrxCreateInputStock;
    @javafx.fxml.FXML
    private AnchorPane trxCreatePane;
    @javafx.fxml.FXML
    private Button btnTrxCreateClose;
    @javafx.fxml.FXML
    private TableColumn<TransactionItem, String> tableColTrxCreateStockId;
    @javafx.fxml.FXML
    private TableColumn<TransactionItem, Number> tableColTrxCreateItemQuantity;
    @javafx.fxml.FXML
    private TableView<TransactionItem> tableViewTrxCreateItems;
    @javafx.fxml.FXML
    private TableColumn<TransactionItem, String> tableColTrxCreateItemId;
    @javafx.fxml.FXML
    private TableColumn<TransactionItem, Number> tableColTrxCreateItemSubTotalPrice;
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

    public void btnTrxCreateClose(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }

    public interface DataCallback {
        void receiveData(Customer customer);
    }

    private Customer customerDataReceivedFromCustView;

    private final ObservableList<Stock> comboStockObsList = FXCollections.observableArrayList();
    private Stock currentSelectedComboItem;

    private double totalPrice = 0;

    private final ObservableList<TransactionItem> transactionItemObsList = FXCollections.observableArrayList();
    private TransactionService transactionService;
    private final List<TransactionItem> transactionItemList = new ArrayList<>();

    private StockService stockService;

    public void btnTrxCreateFindCustomer(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/app/projectpharmacy/customer-view.fxml"));
        Parent childRoot = loader.load();
        CustomerViewController childController = loader.getController();

        // Set DataCallback for child controller (implementing CustomerDataCallback)
        childController.setDataCallback(customer -> {
            textInputTrxCreateCustomerId.setText(customer.getId());
            textInputTrxCreateCustomerName.setText(customer.getName());

            String customerText =
                    STR."ID: \{customer.getId()}\nName: \{customer.getName()}\nPhone Number: \{customer.getPhoneNumber()}\nEmail Address: \{customer.getEmailAddress()}\nDescription: \{customer.getDescription()}\n";
            textAreaTrxCreateCustomerDetail.setText(customerText);
        });

        Stage childStage = new Stage();
        Scene childScene = new Scene(childRoot);
        new ScreenLoader().setDefaultChildWindowSize(childScene, childStage);
        childStage.setScene(childScene);
        childStage.initModality(Modality.APPLICATION_MODAL);
        childStage.showAndWait();
    }

    public void btnTrxCreateSaveRecord(ActionEvent actionEvent) throws SQLException {
        String customerId = textInputTrxCreateCustomerId.getText();
        // get records of transactionItems from tableview
        List<TransactionItem> transactionItems = tableViewTrxCreateItems.getItems();
        transactionService.create(customerId, (int) totalPrice, "", transactionItems);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            stockService = new StockService();
            transactionService = new TransactionService();
            this._populateComboMedList();
            this._initTableView();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // TODO: complete this method
    private void _initTableView() {
        tableColTrxCreateItemId.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId()));
        tableColTrxCreateStockName.setCellValueFactory(cellData -> {
            String name = cellData.getValue().getStockData().getMedicationName();
            return new SimpleStringProperty(name);
        });
        tableColTrxCreateItemQuantity.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getQuantity()));
        tableColTrxCreateItemPrice.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getStockData().getPrice()));
        tableColTrxCreateItemSubTotalPrice.setCellValueFactory(cellData -> {
            int qty = cellData.getValue().getQuantity();
            double price = cellData.getValue().getStockData().getPrice();
            double subTotal = qty * price;
            return new SimpleDoubleProperty(subTotal);
        });
    }

    private void _populateComboMedList() throws SQLException {
        List<Stock> initialStocks = stockService.fetchAllRecord();
        comboStockObsList.addAll(initialStocks);
        comboTrxCreateStockList.setItems(comboStockObsList);
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
        String transactionId = UUID.randomUUID().toString();
        String transactionItemId = UUID.randomUUID().toString();
        Integer quantity = spinnerTrxCreateStock.getValue();
        String stockId = currentSelectedComboItem.getId();

        TransactionItem transactionItem = new TransactionItem(
                transactionItemId,
                transactionId,
                stockId,
                quantity,
                Timestamp.valueOf(LocalDateTime.now()),
                Timestamp.valueOf(LocalDateTime.now())
        );
        transactionItem.setStockData(currentSelectedComboItem);
        transactionItemObsList.add(transactionItem);

        double newTotalPrice = 0.0;
        for (TransactionItem item : transactionItemObsList) {
            newTotalPrice += item.getQuantity() * item.getStockData().getPrice();
        }
        totalPrice = newTotalPrice;
        textInputTrxCreateTotalPrice.setText(String.valueOf(totalPrice));
        tableViewTrxCreateItems.setItems(transactionItemObsList);
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

    public void onTextInputTrxCreateTotalPrice(ActionEvent actionEvent) {
    }

    public void setCustomerSelected(Customer customer) {
        this.customerDataReceivedFromCustView = customer;
    }
}
