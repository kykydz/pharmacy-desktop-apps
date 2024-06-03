package org.app.projectpharmacy.controller;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;
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
    private TableColumn<Stock, String> tableColStockListId;
    @FXML
    private TableColumn<Stock, String> tableColStockListMedicationName;
    @FXML
    private TableColumn<Stock, Number> tableColStockListPrice;
    @FXML
    private TableColumn<Stock, String> tableColStockListDescription;
    @FXML
    private TableColumn<Stock, Number> tableColStockListStock;
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
        tableViewStockList.setEditable(true);

        tableColStockListId.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId()));

        tableColStockListMedicationName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMedicationName()));
        tableColStockListMedicationName.setCellFactory(TextFieldTableCell.<Stock>forTableColumn());
        tableColStockListMedicationName.setOnEditCommit(
                t -> t.getTableView().getItems().get(
                        t.getTablePosition().getRow()).setMedicationName(t.getNewValue())
        );

        tableColStockListStock.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getQuantityAvailable()));
        tableColStockListStock.setCellFactory(TextFieldTableCell.<Stock, Number>forTableColumn(new NumberStringConverter()));
        tableColStockListStock.setOnEditCommit(
                t -> t.getTableView().getItems().get(
                        t.getTablePosition().getRow()).setQuantityAvailable((Integer) t.getNewValue())
        );

        tableColStockListPrice.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getPrice()));
        tableColStockListPrice.setCellFactory(TextFieldTableCell.<Stock, Number>forTableColumn(new NumberStringConverter()));
        tableColStockListPrice.setOnEditCommit(
                t -> t.getTableView().getItems().get(
                        t.getTablePosition().getRow()).setPrice((Integer) t.getNewValue())
        );

        tableColStockListDescription.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDescription()));
        tableColStockListDescription.setCellFactory(TextFieldTableCell.<Stock>forTableColumn());
        tableColStockListDescription.setOnEditCommit(
                t -> t.getTableView().getItems().get(
                        t.getTablePosition().getRow()).setDescription(t.getNewValue())
        );
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