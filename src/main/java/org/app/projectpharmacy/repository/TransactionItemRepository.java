package org.app.projectpharmacy.repository;

import org.app.projectpharmacy.entities.Transaction;
import org.app.projectpharmacy.entities.TransactionItem;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TransactionItemRepository extends BaseRepository<TransactionItem> {

    private static final String INSERT_QUERY =
            "INSERT INTO transaction_item (id, transaction_id, stock_id, quantity, created, updated) VALUES (?, ?, ?," +
                    " ?, ?, ?)";

    private static final String FIND_ALL_QUERY = "SELECT * FROM transaction_item";

    public TransactionItemRepository() throws SQLException {
        super();
    }

    @Override
    public String getTableName() {
        return "transaction_item";
    }

    @Override
    public void create(TransactionItem transactionItem) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(INSERT_QUERY)) {
            ps.setString(1, transactionItem.getId());
            ps.setString(2, transactionItem.getTransactionId());
            ps.setString(3, transactionItem.getStockId());
            ps.setInt(4, transactionItem.getQuantity());
            ps.setTimestamp(6, transactionItem.getCreated());
            ps.setTimestamp(7, transactionItem.getUpdated());
            ps.executeUpdate();
        }
    }

    public void createBulk(List<TransactionItem> transactionItems) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(INSERT_QUERY)) {
            for (TransactionItem transactionItem : transactionItems) {
                ps.setString(1, transactionItem.getId());
                ps.setString(2, transactionItem.getTransactionId());
                ps.setString(3, transactionItem.getStockId());
                ps.setInt(4, transactionItem.getQuantity());
                ps.setTimestamp(5, transactionItem.getCreated());
                ps.setTimestamp(6, transactionItem.getUpdated());
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    @Override
    public List<TransactionItem> getMany(String query, Object... params) throws SQLException {
        if (query == null) {
            query = FIND_ALL_QUERY;
        }
        return super.getMany(query, params);
    }

    public List<TransactionItem> getTransactionItemByCustomerId(String transactionId) throws SQLException {
        String findQueryByName = "SELECT * FROM transaction_item WHERE transaction_id = ? ";
        Object[] params = { transactionId };
        return this.getMany(findQueryByName, params);
    }

    @Override
    protected TransactionItem mapResultSetToEntity(ResultSet rs) throws SQLException {
        return new TransactionItem(
                rs.getString("id"),
                rs.getString("transaction_id"),
                rs.getString("stock_id"),
                rs.getInt("quantity"),
                rs.getTimestamp("created"),
                rs.getTimestamp("updated"));
    }
}
