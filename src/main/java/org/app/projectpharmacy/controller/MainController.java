package org.app.projectpharmacy.controller;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.app.projectpharmacy.entities.Stock;
import org.app.projectpharmacy.services.StockService;
import org.app.projectpharmacy.view.CustomerCreate;
import org.app.projectpharmacy.view.StockCreate;
import org.app.projectpharmacy.view.TransactionCreate;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    private StockService stockService;
    @FXML
    public Button btnStockListNewTransaction;
    @FXML
    public Button btnStockListCreateNewUser;
    public Button btnStockListNewStock;
    @FXML
    private Label welcomeText;
    @FXML
    private TextField inputTextStockListFindStock;
    @FXML
    private TableColumn<Stock, String> tableColStockListId, tableColStockListMedicationName, tableColStockListPrice, tableColStockListDescription;
    @FXML
    private TableColumn<Stock, Integer> tableColStockListStock;
    @FXML
    private TableView<Stock> tableViewStockList;
    @FXML
    private AnchorPane paneStockList;
    @FXML
    private Button btnStockListFindStock;

    private Stage primaryStage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this._initTableStockList();
        this._populateTableStockList();
    }

    private void _initTableStockList(){
        tableColStockListId.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId()));
        tableColStockListMedicationName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMedicationName()));
//        tableColStockListStock.setCellValueFactory(cellData -> new SimpleStringProperty(Integer.toString(cellData.getValue().getQuantityAvailable())));
//        tableColStockListStock.setCellFactory(TextFieldTableCell.forTableColumn());
//        tableColStockListStock.setEditable(true);
//        TableColumn<Stock, Integer> tableColStockListStock = new TableColumn<>("Stock");
        tableColStockListStock.setCellValueFactory(cellData -> cellData.getValue().);

// Use a custom cell factory for integer editing
        tableColStockListStock.setCellFactory(col -> {
            TextFieldTableCell<Stock, Integer> cell = new TextFieldTableCell<>(TextFormatter.integerProperty());
            cell.setOnEditCommit(event -> {
                Integer newValue = event.getNewValue();
                if (newValue != null) {
                    (event.getRowValue()).setQuantityAvailable(newValue); // Update quantity
                }
            });
            return cell;
        });
        tableColStockListStock.setEditable(true);

        tableColStockListPrice.setCellValueFactory(cellData -> new SimpleStringProperty(Integer.toString(cellData.getValue().getPrice())));
        tableColStockListDescription.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDescription()));
    }

    private void _populateTableStockList() {
        // fetch all data for first load
        try {
            stockService = (StockService) new StockService();
            ObservableList<Stock> stocksObsList = FXCollections.observableArrayList();
            List<Stock> stocks = stockService.fetchAllRecord();
            stocksObsList.addAll(stocks);
            tableViewStockList.setItems(stocksObsList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void onBtnStockListNewStock(ActionEvent actionEvent) throws IOException {
        StockCreate stockCreate = new StockCreate();
        stockCreate.start(primaryStage);
    }

    public void onBtnStockListNewTransaction(ActionEvent actionEvent) throws IOException {
        TransactionCreate transactionCreate = new TransactionCreate();
        transactionCreate.start(primaryStage);
    }

    public void onBtnStockListCreateNewUser(ActionEvent actionEvent) throws IOException {
        CustomerCreate customerCreate = new CustomerCreate();
        customerCreate.start(primaryStage);
    }
}