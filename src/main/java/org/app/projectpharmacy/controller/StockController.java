package org.app.projectpharmacy.controller;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.app.projectpharmacy.entities.Stock;
import org.app.projectpharmacy.services.StockService;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class StockController implements Initializable {
    @javafx.fxml.FXML
    private TextField inputTextNewStockMedicationName;
    @javafx.fxml.FXML
    private TextField inputTextNewStockPrice;
    @javafx.fxml.FXML
    private TextArea inputTextAreaNewStockDescription;
    @javafx.fxml.FXML
    private TextField inputTextNewStockQuantity;
    @javafx.fxml.FXML
    private AnchorPane newStockPane;
    @javafx.fxml.FXML
    private Button btnNewStockCancel;
    @javafx.fxml.FXML
    private Button btnNewStockCreate;

    private StockService stockService;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            // initiate service injection
            stockService = new StockService();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void onBtnNewStockCreate(ActionEvent actionEvent) throws SQLException {
        String medicationName = inputTextNewStockMedicationName.getText();
        Integer price = Integer.parseInt(inputTextNewStockPrice.getText());
        Integer stockQuantity = Integer.parseInt(inputTextNewStockQuantity.getText());
        String stockDescription = inputTextAreaNewStockDescription.getText();

        stockService.create(medicationName, price, stockQuantity, stockDescription);
    }
}
