package org.app.projectpharmacy.controller;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;
import org.app.projectpharmacy.entities.Customer;
import org.app.projectpharmacy.entities.Stock;
import org.app.projectpharmacy.services.StockService;
import org.app.projectpharmacy.utils.ScreenLoader;
import org.app.projectpharmacy.view.CustomerCreate;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    public Button btnStockListCustomersView;
    public Button btnStockListTransactionList;
    public Label labelStockListTime;
    public Button btnStockListAuditLog;
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

    private final ObservableList<Stock> stocksObsList = FXCollections.observableArrayList();

    private Stage primaryStage;

    public interface DataCallback {
        void receiveData(Customer customer);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this._initTableStockList();
        this._populateTableStockList();
    }

    private void _initTableStockList(){
        this._setEditableTableStockList(false);
        tableColStockListId.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId()));
        tableColStockListMedicationName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMedicationName()));
        tableColStockListStock.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getQuantityAvailable()));
        tableColStockListPrice.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getPrice()));
        tableColStockListDescription.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDescription()));
    }

    private void _setEditableTableStockList(Boolean isEditable) {
        if (!isEditable) {
            tableViewStockList.setEditable(false);
            return;
        }
        tableViewStockList.setEditable(false);

        tableColStockListMedicationName.setCellFactory(TextFieldTableCell.<Stock>forTableColumn());
        tableColStockListMedicationName.setOnEditCommit(
                t -> t.getTableView().getItems().get(
                        t.getTablePosition().getRow()).setMedicationName(t.getNewValue())
        );
        tableColStockListStock.setCellFactory(TextFieldTableCell.<Stock, Number>forTableColumn(new NumberStringConverter()));
        tableColStockListStock.setOnEditCommit(
                t -> t.getTableView().getItems().get(
                        t.getTablePosition().getRow()).setQuantityAvailable((Integer) t.getNewValue())
        );
        tableColStockListPrice.setCellFactory(TextFieldTableCell.<Stock, Number>forTableColumn(new NumberStringConverter()));
        tableColStockListPrice.setOnEditCommit(
                t -> t.getTableView().getItems().get(
                        t.getTablePosition().getRow()).setPrice((Integer) t.getNewValue())
        );
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
            this._clearAndPopulateTableView(null, false);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void onBtnStockListNewStock(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/app/projectpharmacy/stock-create.fxml"));
        Parent childRoot = loader.load();

        Stage childStage = new Stage();
        Scene childScene = new Scene(childRoot);
        new ScreenLoader().setDefaultChildWindowSize(childScene, childStage);
        childStage.setTitle("Create New Stock");
        childStage.setScene(childScene);
        childStage.initModality(Modality.APPLICATION_MODAL);
        childStage.showAndWait();
    }

    public void onBtnStockListNewTransaction(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/app/projectpharmacy/transaction-create.fxml"));
        Parent childRoot = loader.load();

        Stage childStage = new Stage();
        Scene childScene = new Scene(childRoot);
        new ScreenLoader().setDefaultChildWindowSize(childScene, childStage);
        childStage.setTitle("Create New Transaction");
        childStage.setScene(childScene);
        childStage.initModality(Modality.APPLICATION_MODAL);
        childStage.showAndWait();
    }

    public void onBtnStockListCreateNewUser(ActionEvent actionEvent) throws IOException {
        CustomerCreate customerCreate = new CustomerCreate();
        customerCreate.start(primaryStage);
    }

    public void onBtnStockListFindStock(ActionEvent actionEvent) throws SQLException {
        String medicationName = inputTextStockListFindStock.getText();
        List<Stock> stocks = stockService.findStockByName(medicationName);
        Boolean isFindResult = !medicationName.isEmpty();
        this._clearAndPopulateTableView(stocks, isFindResult);
    }

    private void _clearAndPopulateTableView(List<Stock> stocks, Boolean isFindResult) throws SQLException {
        tableViewStockList.getItems().clear();
        if ((stocks == null || stocks.isEmpty()) && !isFindResult) {
            stocks = stockService.fetchAllRecord();
        }
        stocksObsList.setAll(stocks);
        tableViewStockList.setItems(stocksObsList);
    }

    public void onBtnStockListCustomersView(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/app/projectpharmacy/customer-view.fxml"));
        Parent childRoot = loader.load();

        Stage childStage = new Stage();
        Scene childScene = new Scene(childRoot);
        new ScreenLoader().setDefaultChildWindowSize(childScene, childStage);
        childStage.setTitle("Customer List");
        childStage.setScene(childScene);
        childStage.initModality(Modality.APPLICATION_MODAL);
        childStage.showAndWait();
    }

    public void btnStockListTransactionList(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/app/projectpharmacy/transaction-view.fxml"));
        Parent childRoot = loader.load();

        Stage childStage = new Stage();
        Scene childScene = new Scene(childRoot);
        new ScreenLoader().setDefaultChildWindowSize(childScene, childStage);
        childStage.setTitle("Transaction List");
        childStage.setScene(childScene);
        childStage.initModality(Modality.APPLICATION_MODAL);
        childStage.showAndWait();
    }

    public void btnStockListAuditLog(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/app/projectpharmacy/audit-trail.fxml"));
        Parent childRoot = loader.load();

        Stage childStage = new Stage();
        Scene childScene = new Scene(childRoot);
        new ScreenLoader().setDefaultChildWindowSize(childScene, childStage);
        childStage.setTitle("Audit Log");
        childStage.setScene(childScene);
        childStage.initModality(Modality.APPLICATION_MODAL);
        childStage.showAndWait();
    }
}