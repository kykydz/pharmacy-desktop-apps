package org.app.projectpharmacy.controller;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.app.projectpharmacy.entities.AuditTrail;
import org.app.projectpharmacy.entities.Transaction;
import org.app.projectpharmacy.services.AuditTrailService;
import org.app.projectpharmacy.services.TransactionService;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class AuditTrailLogController implements Initializable {
    @javafx.fxml.FXML
    private TableColumn<AuditTrail, String> tableColAuditTrailAction;
    @javafx.fxml.FXML
    private TableColumn<AuditTrail, String> tableColAuditTrailFeatureAccessed;
    @javafx.fxml.FXML
    private TableView<AuditTrail> tableViewAuditTrail;
    @javafx.fxml.FXML
    private AnchorPane paneAuditLog;
    @javafx.fxml.FXML
    private TableColumn<AuditTrail, String> tableColAuditTrailAdminId;
    @javafx.fxml.FXML
    private Button btnAuditTrailFindLog;
    @javafx.fxml.FXML
    private TextField inputTextAuditTrailFindLog;
    @javafx.fxml.FXML
    private TableColumn<AuditTrail, String> tableColAuditTrailCreated;
    @javafx.fxml.FXML
    private TableColumn<AuditTrail, String> tableColAuditTrailMetaData;
    @javafx.fxml.FXML
    private Button btnAuditTrailClose;

    private AuditTrailService auditTrailService;

    private final ObservableList<AuditTrail> auditTrailObservableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this._initTableView();
        try {
            auditTrailService = new AuditTrailService();
            _clearAndPopulateTableView(null, false);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @javafx.fxml.FXML
    public void btnAuditTrailFindLog(ActionEvent actionEvent) throws SQLException {
        String word = inputTextAuditTrailFindLog.getText();
        List<AuditTrail> auditTrails = auditTrailService.findLogByWord(word);

        Boolean isFindResult = !word.isEmpty();
        _clearAndPopulateTableView(auditTrails, isFindResult);
    }

    @javafx.fxml.FXML
    public void btnAuditTrailClose(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }

    private void _initTableView() {
        tableViewAuditTrail.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        tableColAuditTrailCreated.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getCreated())));
        tableColAuditTrailAdminId.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAdminId()));
        tableColAuditTrailFeatureAccessed.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFeatureAccessed()));
        tableColAuditTrailAction.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAction()));
        tableColAuditTrailMetaData.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMetaData()));

        tableViewAuditTrail.setItems(auditTrailObservableList);
    }

    private void _clearAndPopulateTableView(List<AuditTrail> auditTrails, Boolean isFindResult) throws SQLException {
        tableViewAuditTrail.getItems().clear();
        if ((auditTrails == null || auditTrails.isEmpty()) && !isFindResult) {
            auditTrails = auditTrailService.fetchAllRecord();
        }
        auditTrailObservableList.setAll(auditTrails);
        tableViewAuditTrail.setItems(auditTrailObservableList);
    }
}
