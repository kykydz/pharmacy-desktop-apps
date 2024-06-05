package org.app.projectpharmacy.entities;

import java.sql.Time;
import java.sql.Timestamp;

public class TransactionItem {

    private String id;
    private String transactionId;
    private String stockId;

    private Integer quantity;
    private Timestamp created;
    private Timestamp updated;

    private Stock stockData;

    private Double subTotal;

    public TransactionItem(String id, String transactionId, String stockId, Integer quantity, Timestamp created, Timestamp updated) {
        this.id = id;
        this.transactionId = transactionId;
        this.stockId = stockId;
        this.quantity = quantity;
        this.created = created;
        this.updated = updated;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getStockId() {
        return stockId;
    }

    public int getQuantity() {
        return quantity;
    }

    // Setters (optional, can be added if needed)
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public void setStockId(String stockId) {
        this.stockId = stockId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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

    public void setStockData(Stock stockData) {
        this.stockData = stockData;
    }

    public Stock getStockData() {
        return this.stockData;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public Double getSubTotal() {
        return this.subTotal;
    }

    // Additional methods (optional)
    // You can add methods for specific functionalities related to TransactionItem objects

}

