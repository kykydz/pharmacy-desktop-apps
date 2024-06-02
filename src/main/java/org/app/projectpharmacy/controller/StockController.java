package org.app.projectpharmacy.controller;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

    }
}
