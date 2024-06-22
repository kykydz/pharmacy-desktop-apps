package org.app.projectpharmacy.controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.app.projectpharmacy.entities.Customer;
import org.app.projectpharmacy.services.CustomerService;
import org.app.projectpharmacy.utils.Modal;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CustomerCreateController implements Initializable {
    @javafx.fxml.FXML
    private TextField inputTextCreateCustomerEmail;
    @javafx.fxml.FXML
    private AnchorPane createCustomerPane;
    @javafx.fxml.FXML
    private Button btnCreateCustomerCancel;
    @javafx.fxml.FXML
    private TextField inputTextCreateCustomerWANumber;
    @javafx.fxml.FXML
    private TextField inputTextCreateCustomerName;
    @javafx.fxml.FXML
    private TextArea inputTextAreaCreateCustomerDescription;
    @javafx.fxml.FXML
    private Button btnCreateCustomerCreate;

    private CustomerService customerService;

    private DataTransfer.customerCallback customerCallback;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            // initiate service injection
            customerService = new CustomerService();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnCreateCustomerCreate(ActionEvent actionEvent) throws SQLException, IOException {
        String name = inputTextCreateCustomerName.getText();
        String phoneNumber = inputTextCreateCustomerWANumber.getText();
        String email = inputTextCreateCustomerEmail.getText();
        String description = inputTextAreaCreateCustomerDescription.getText();
        Customer customerCreated = customerService.create(name, phoneNumber, email, description);

        this.customerCallback.setCustomerData(customerCreated);

        new Modal().showNotification("CREATE CUSTOMER SUCCESS", "Customer record is saved to database");

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }

    public void btnCreateCustomerCancel(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }

    public void setCustomerCallback(DataTransfer.customerCallback customerCallback) {
        this.customerCallback = customerCallback;
    }
}
