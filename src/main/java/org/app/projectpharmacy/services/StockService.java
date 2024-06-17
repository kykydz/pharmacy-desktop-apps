package org.app.projectpharmacy.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.app.projectpharmacy.entities.Stock;
import org.app.projectpharmacy.repository.StockRepository;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.app.projectpharmacy.utils.ObjectMapperConvention.toJson;

public class StockService {
    private final StockRepository stockRepository;
    private final AuditTrailService auditTrailService;

    public StockService() throws SQLException {
        this.stockRepository = new StockRepository();
        this.auditTrailService = new AuditTrailService();
    }

    public List<Stock> fetchAllRecord() throws SQLException {
        return stockRepository.getMany(null);
    }

    public Stock create(String medicationName, Integer price, Integer stockQuantity, String stockDescription) throws SQLException, JsonProcessingException {
        Stock stock = new Stock(
                UUID.randomUUID().toString(),
                medicationName,
                stockDescription,
                price,
                stockQuantity,
                Timestamp.valueOf(LocalDateTime.now()),
                Timestamp.valueOf(LocalDateTime.now())
        );
        stockRepository.create(stock);

        this.auditTrailService.create("Stock", "Create", toJson(stock));
        return stock;
    }

    public List<Stock> findStockByName(String medicationName) throws SQLException {
        return stockRepository.getByMedicationName(medicationName);
    }

    public Stock findStockById(String stockId) throws SQLException {
        return stockRepository.getById(stockId);
    }

    public void updateStock(Stock stock) throws SQLException, JsonProcessingException {
        stockRepository.update(stock);
        this.auditTrailService.create("Stock", "Update", toJson(stock));
    }
}
