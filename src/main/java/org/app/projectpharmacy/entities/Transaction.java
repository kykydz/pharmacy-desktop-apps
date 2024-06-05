package org.app.projectpharmacy.entities;

import java.sql.Timestamp;
import java.util.List;

public class Transaction {

    private final String id;
    private final String customerId;
    private final double totalPrice;
    private final String metaData;
    private Timestamp created;
    private Timestamp updated;
    private List<TransactionItem> transactionItems;

    public Transaction(String id, String customerId, double totalPrice, String metaData, Timestamp created, Timestamp updated) {
        this.id = id;
        this.customerId = customerId;
        this.totalPrice = totalPrice;
        this.metaData = metaData;
        this.created = created;
        this.updated = updated;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public String getMetaData() {
        return metaData;
    }

    public Timestamp getCreated() {
        return created;
    }

    public Timestamp getUpdated() {
        return updated;
    }

    // Setters (optional, can be added if needed)
    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public void setUpdated(Timestamp updated) {
        this.updated = updated;
    }

    public void setTransactionItems (List<TransactionItem> transactionItems) {
        this.transactionItems = transactionItems;
    }

    public List<TransactionItem> getTransactionItems() {
        return this.transactionItems;
    }

    // Additional methods (optional)
    // You can add methods for specific functionalities related to Transaction objects

}

