package org.app.projectpharmacy.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.app.projectpharmacy.entities.Stock;
import org.app.projectpharmacy.entities.Transaction;
import org.app.projectpharmacy.entities.TransactionItem;
import org.app.projectpharmacy.repository.StockRepository;
import org.app.projectpharmacy.repository.TransactionItemRepository;
import org.app.projectpharmacy.repository.TransactionRepository;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.app.projectpharmacy.utils.ObjectMapperConvention.toJson;


public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final TransactionItemRepository transactionItemRepository;
    private final StockRepository stockRepository;
    private AuditTrailService auditTrailService;

    public TransactionService() throws SQLException {
        this.transactionRepository = new TransactionRepository();
        this.transactionItemRepository = new TransactionItemRepository();
        this.stockRepository = new StockRepository();
        this.auditTrailService = new AuditTrailService();
    }

    public List<Transaction> fetchAllRecord() throws SQLException {
        return transactionRepository.getMany(null);
    }

    public Transaction create(String customerId, Integer totalPrice, String metaData, List<TransactionItem> transactionItems) throws SQLException, JsonProcessingException {
        // TODO: check customerId is available or not and handle error
        // TODO: check if stock is available or not and handle error

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

        // add audit log
        this.auditTrailService.create("Transaction", "Create", toJson(transaction));

        // save transaction items with enriched transactionId from created above
        List<String> transactionIdList = new ArrayList<>();
        for (TransactionItem transactionItem : transactionItems) {
            transactionItem.setTransactionId(transactionId);
            // TODO: check if stock is available or not and handle error
            Stock stock = stockRepository.getById(transactionItem.getId());
            transactionItem.setStockData(stock);
            transactionIdList.add(transactionItem.getId());
        }
        transactionItemRepository.createBulk(transactionItems);

        // add audit log
        this.auditTrailService.create("Transaction Item", "Create", toJson(transactionIdList));

        // enrich transaction with transactionItems and return it
        transaction.setTransactionItems(transactionItems);
        return transaction;
    }

    public List<Transaction> findTransactionByCustomerId (String customerId) throws SQLException {
        return transactionRepository.getByCustomerId(customerId);
    }
}
