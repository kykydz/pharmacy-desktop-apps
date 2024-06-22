package org.app.projectpharmacy.controller;

import org.app.projectpharmacy.entities.Customer;
import org.app.projectpharmacy.entities.Stock;
import org.app.projectpharmacy.entities.Transaction;
import org.app.projectpharmacy.entities.TransactionItem;

public class DataTransfer {

    public interface customerCallback {
        void setCustomerData(Customer customer);
    }

    public interface stockCallback {
        void setStockData(Stock stock);
    }

    public interface transactionCallback {
        void setTransactionData(Transaction transaction);
    }
    public interface transactionItemCallback {
        void setTransactionItemsData(TransactionItem[] transactionItems);
    }
}
