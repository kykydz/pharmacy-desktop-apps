package org.app.projectpharmacy.services;

import org.app.projectpharmacy.entities.Transaction;
import org.app.projectpharmacy.repository.TransactionRepository;

import java.sql.SQLException;
import java.util.List;

public class TransactionHistoryService {
    private final TransactionRepository transactionRepository;

    public TransactionHistoryService() throws SQLException {
        this.transactionRepository = new TransactionRepository();
    }

    public List<Transaction> getTransactionHistory() throws SQLException {
        // Retrieve transaction history from the database
        return transactionRepository.getMany("SELECT * FROM transaction");
    }
}
