package org.app.projectpharmacy.services;

import org.app.projectpharmacy.entities.Stock;
import org.app.projectpharmacy.entities.Transaction;
import org.app.projectpharmacy.entities.TransactionItem;
import org.app.projectpharmacy.repository.StockRepository;
import org.app.projectpharmacy.repository.TransactionItemRepository;
import org.app.projectpharmacy.repository.TransactionRepository;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final TransactionItemRepository transactionItemRepository;
    private final StockRepository stockRepository;

    public TransactionService() throws SQLException {
        this.transactionRepository = new TransactionRepository();
        this.transactionItemRepository = new TransactionItemRepository();
        this.stockRepository = new StockRepository();
    }

    public List<Transaction> fetchAllRecord() throws SQLException {
        return transactionRepository.getMany(null);
    }

    public Transaction create(String customerId, Integer totalPrice, String metaData, List<TransactionItem> transactionItems) throws SQLException {
        // TODO: check customerId is available or not
        // TODO: check if stock is available or not

        // save transaction record
        String transactionId = UUID.randomUUID().toString();
        Transaction transaction = new Transaction(
                transactionId,
                customerId,
                totalPrice,
                metaData,
                Timestamp.valueOf(LocalDateTime.now()),
                Timestamp.valueOf(LocalDateTime.now())
        );
        transactionRepository.create(transaction);

        // save transaction items with enriched transactionId from created above
        for (TransactionItem item : transactionItems) {
            item.setTransactionId(transactionId);
            _injectTransactionItemWithStockName(item);
        }
        transactionItemRepository.createBulk(transactionItems);

        // enrich transaction with items and return it
        transaction.setTransactionItems(transactionItems);
        return transaction;
    }

    public List<Transaction> findTransactionByCustomerId (String customerId) throws SQLException {
        return transactionRepository.getByCustomerId(customerId);
    }

    public void _injectTransactionItemWithStockName(TransactionItem transactionItem) throws SQLException {
        Stock stock = stockRepository.getById(transactionItem.getId());
        transactionItem.setStockName(stock.getMedicationName());
    }
}
