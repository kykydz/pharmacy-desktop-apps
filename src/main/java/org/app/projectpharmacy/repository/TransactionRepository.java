package org.app.projectpharmacy.repository;

import org.app.projectpharmacy.entities.Transaction;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TransactionRepository extends BaseRepository<Transaction> {

    private static final String INSERT_QUERY =
            "INSERT INTO transaction (id, customer_id, total_price, meta_data, created, updated) VALUES (?, ?, ?, ?, ?, ?)";

    private static final String FIND_ALL_QUERY = "SELECT * FROM transaction";

    public TransactionRepository() throws SQLException {
        super();
    }

    @Override
    public String getTableName() {
        return "transaction";
    }

    @Override
    public void create(Transaction transaction) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(INSERT_QUERY)) {
            ps.setString(1, transaction.getId());
            ps.setString(2, transaction.getCustomerId());
            ps.setDouble(3, transaction.getTotalPrice());
            ps.setString(4, transaction.getMetaData());
            ps.setTimestamp(5, transaction.getCreated());
            ps.setTimestamp(6, transaction.getUpdated());
            ps.executeUpdate();
        }
    }

    @Override
    public List<Transaction> getMany(String query, Object... params) throws SQLException {
        if (query == null) {
            query = FIND_ALL_QUERY;
        }
        return super.getMany(query, params);
    }

    @Override
    public void update(Transaction entity) {

    }

    public List<Transaction> getByCustomerId(String customerId) throws SQLException {
        String findQueryByName = "SELECT * FROM transaction WHERE customer_id like ? ";
        Object[] params = { customerId };
        return this.getMany(findQueryByName, params);
    }

    @Override
    protected Transaction mapResultSetToEntity(ResultSet rs) throws SQLException {
        return new Transaction(
                rs.getString("id"),
                rs.getString("customer_id"),
                rs.getDouble("total_price"),
                rs.getString("meta_data"),
                rs.getTimestamp("created"),
                rs.getTimestamp("updated"));
    }
}
