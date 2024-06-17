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
import org.app.projectpharmacy.services.CustomerService;
import org.app.projectpharmacy.services.StockService;
import org.app.projectpharmacy.view.CustomerCreate;
import org.app.projectpharmacy.view.CustomerView;
import org.app.projectpharmacy.view.TransactionCreate;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.ResourceBundle;

public class CustomerViewController implements Initializable {

    public TextField inputTextCustomerViewFind;
    public Button btnCustomerViewFind;
    public Button btnCustomerViewSelect;
    public Button btnCustomerViewCreateNew;
    public TextField inputTextCustomerViewCustId;
    private CustomerService customerService;
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
    private TableColumn<Customer, String> tableColCustomerViewCreated;
    @javafx.fxml.FXML
    private TableColumn<Customer, String> tableColCustomerViewDesc;
    @javafx.fxml.FXML
    private TableView<Customer> tableViewCustomerView;
    @javafx.fxml.FXML
    private TableColumn<Customer, String> tableColCustomerViewName;
    @javafx.fxml.FXML
    private TableColumn<Customer, String> tableColCustomerViewUpdated;
    @javafx.fxml.FXML
    private TableColumn<Customer, String> tableColCustomerViewWANumber;
    @javafx.fxml.FXML
    private TableColumn<Customer, String> tableColCustomerViewId;
    @javafx.fxml.FXML
    private TableColumn<Customer, String> tableColCustomerViewEmail;

    private TransactionCreateController.DataCallback callback;

    private Stage stage;

    private final ObservableList<Customer> customersObsList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this._initTableStockList();
        this._populateTableStockList();
    }

    private void _populateTableStockList() {
        // fetch all data for first load
        try {
            customerService = new CustomerService();
            this._clearAndPopulateTableView(null, false);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void _initTableStockList() {
        tableViewCustomerView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        tableColCustomerViewId.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId()));
        tableColCustomerViewId.setCellFactory(TextFieldTableCell.<Customer>forTableColumn());
        tableColCustomerViewId.setEditable(true);


        tableColCustomerViewName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        tableColCustomerViewWANumber.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPhoneNumber()));
        tableColCustomerViewEmail.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmailAddress()));
        tableColCustomerViewDesc.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDescription()));
        tableColCustomerViewCreated.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getCreated())));
        tableColCustomerViewUpdated.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getUpdated())));
    }

    private void _clearAndPopulateTableView(List<Customer> customers, Boolean isFindResult) throws SQLException {
        tableViewCustomerView.getItems().clear();
        if ((customers == null || customers.isEmpty()) && !isFindResult) {
            customers = customerService.fetchAllRecord();
        }
        customersObsList.setAll(customers);
        tableViewCustomerView.setItems(customersObsList);
    }

    public void btnCustomerViewFind(ActionEvent actionEvent) throws SQLException {
        String name = inputTextCustomerViewFind.getText();
        List<Customer> customers = customerService.findCustomerByName(name);
        Boolean isFindResult = !name.isEmpty();
        this._clearAndPopulateTableView(customers, isFindResult);
    }

    public void onBtnCustomerViewCreateNew(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        CustomerCreate customerCreate = new CustomerCreate();
        customerCreate.start(stage);
    }

    public void btnCustomerViewSelect(ActionEvent actionEvent) {
        Customer selectedCustomer = (Customer) tableViewCustomerView.getSelectionModel().getSelectedItem();
        inputTextCustomerViewCustId.setText(selectedCustomer.getId());
        // transfer data to the caller
        callback.receiveData(selectedCustomer);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }

    public void setDataCallback(TransactionCreateController.DataCallback callback) {
        this.callback = callback;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void close() {
        if (stage != null) {
            stage.close();
        }
    }

    public void btnCustomerViewClose(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }
}
