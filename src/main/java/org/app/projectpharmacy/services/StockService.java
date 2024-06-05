package org.app.projectpharmacy.services;

import org.app.projectpharmacy.entities.Stock;
import org.app.projectpharmacy.repository.StockRepository;
import org.app.projectpharmacy.utils.DatabaseManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class StockService {
    private final StockRepository stockRepository;

    public StockService() throws SQLException {
        this.stockRepository = new StockRepository();
    }

    public List<Stock> fetchAllRecord() throws SQLException {
        return stockRepository.getMany(null);
    }

    public Stock create(String medicationName, Integer price, Integer stockQuantity, String stockDescription) throws SQLException {
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
        return stock;
    }

    public List<Stock> findStockByName(String medicationName) throws SQLException {
        return stockRepository.getByMedicationName(medicationName);
    }

    public Stock findStockById(String stockId) throws SQLException {
        return stockRepository.getById(stockId);
    }
}
