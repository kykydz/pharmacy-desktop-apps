package org.app.projectpharmacy.controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.app.projectpharmacy.services.CustomerService;
import org.app.projectpharmacy.services.StockService;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            // initiate service injection
            customerService = new CustomerService();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnCreateCustomerCreate(ActionEvent actionEvent) throws SQLException {
        String name = inputTextCreateCustomerName.getText();
        String phoneNumber = inputTextCreateCustomerWANumber.getText();
        String email = inputTextCreateCustomerEmail.getText();
        String description = inputTextAreaCreateCustomerDescription.getText();
        customerService.create(name, phoneNumber, email, description);
    }
}
