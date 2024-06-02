package org.app.projectpharmacy.services;

import org.app.projectpharmacy.entities.Stock;
import org.app.projectpharmacy.repository.StockRepository;
import org.app.projectpharmacy.utils.DatabaseManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class StockService {
    private final Connection connection;
    private final StockRepository stockRepository;

    public StockService() throws SQLException {
        this.connection = new DatabaseManager(null, null, null, null).connect();
        this.stockRepository = new StockRepository(connection);
    }

    public List<Stock> fetchAllRecord() throws SQLException {
        return stockRepository.getMany(null);
    }
}
