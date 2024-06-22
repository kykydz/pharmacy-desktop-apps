package org.app.projectpharmacy.controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.app.projectpharmacy.entities.Stock;
import org.app.projectpharmacy.services.StockService;
import org.app.projectpharmacy.utils.Modal;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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

    private DataTransfer.stockCallback stockCallback;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            // initiate service injection
            stockService = new StockService();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void onBtnNewStockCreate(ActionEvent actionEvent) throws SQLException, IOException {
        String medicationName = inputTextNewStockMedicationName.getText();
        Integer price = Integer.parseInt(inputTextNewStockPrice.getText());
        Integer stockQuantity = Integer.parseInt(inputTextNewStockQuantity.getText());
        String stockDescription = inputTextAreaNewStockDescription.getText();

        Stock stockCreated = stockService.create(medicationName, price, stockQuantity, stockDescription);
        this.stockCallback.setStockData(stockCreated);

        new Modal().showNotification("CREATE STOCK SUCCESS", "Stock record is saved to database");

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }

    public void btnNewStockCancel(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }

    public void setStockCallback(DataTransfer.stockCallback stockCallback) {
        this.stockCallback = stockCallback;
    }
}
