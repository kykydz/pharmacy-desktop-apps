package org.app.projectpharmacy.repository;

import org.app.projectpharmacy.entities.Customer;
import org.app.projectpharmacy.entities.Stock;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class StockRepository extends BaseRepository<Stock> {
    private static final String INSERT_QUERY =
            "INSERT INTO stock (id, medication_name, description, price, quantity_available, created, updated) VALUES (?, ?, ?, ?, ?, ?)";

    private static final String FIND_ALL_QUERY = "SELECT * FROM stock";

    private static final String FIND_BY_ID_QUERY = "SELECT * FROM stock WHERE id = ?";

    private static final String UPDATE_QUERY =
            "UPDATE stock SET name = ?, phone_number = ?, email_address = ? WHERE id = ?";

    private static final String DELETE_QUERY = "DELETE FROM stock WHERE id = ?";

    public StockRepository(Connection connection) {
        super(connection);
    }

    @Override
    public String getTableName() {
        return "stock";
    }

    public void create(Customer customer) throws SQLException {
        String insertQuery = String.format(INSERT_QUERY,
                customer.getId(), customer.getName(), customer.getPhoneNumber(), customer.getEmailAddress(),
                customer.getDescription(), customer.getCreated(), customer.getUpdated());
        super.create(insertQuery);
    }

    @Override
    public List<Stock> getMany(String query, Object... params) throws SQLException {
        if (query == null) {
            query = FIND_ALL_QUERY;
        }
        return super.getMany(query, params);
    }

    @Override
    protected Stock mapResultSetToEntity(ResultSet rs) throws SQLException {
        return new Stock(
                rs.getString("id"),
                rs.getString("medication_name"),
                rs.getString("description"),
                rs.getInt("price"),
                rs.getInt("quantity_available"),
                rs.getTimestamp("created"),
                rs.getTimestamp("updated"));
    }
}
