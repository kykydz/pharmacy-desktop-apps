package org.app.projectpharmacy.repository;

import org.app.projectpharmacy.entities.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CustomerRepository extends BaseRepository<Customer> {


    private static final String INSERT_QUERY =
            "INSERT INTO customer (id, name, phone_number, email_address, description, created, updated) VALUES (?, ?, ?, ?, ?, ?)";

    private static final String FIND_ALL_QUERY = "SELECT * FROM customer";

    private static final String FIND_BY_ID_QUERY = "SELECT * FROM customer WHERE id = ?";

    private static final String UPDATE_QUERY =
            "UPDATE customer SET name = ?, phone_number = ?, email_address = ? WHERE id = ?";

    private static final String DELETE_QUERY = "DELETE FROM customer WHERE id = ?";

    public CustomerRepository() throws SQLException {
        super();
    }

    @Override
    public String getTableName() {
        return "customer";
    }
    
    public void create(Customer customer) throws SQLException {
        String insertQuery = String.format(INSERT_QUERY,
                customer.getId(), customer.getName(), customer.getPhoneNumber(), customer.getEmailAddress(),
                customer.getDescription(), customer.getCreated(), customer.getUpdated());
    }

    @Override
    public List<Customer> getMany(String query, Object... params) throws SQLException {
        if (query == null) {
            query = FIND_ALL_QUERY;
        }
        return super.getMany(query, params);
    }

    @Override
    protected Customer mapResultSetToEntity(ResultSet rs) throws SQLException {
        return new Customer(
                rs.getString("id"),
                rs.getString("name"),
                rs.getString("phone_number"),
                rs.getString("email_address"),
                rs.getString("description"),
                rs.getTimestamp("created"),
                rs.getTimestamp("updated"));
    }
}
