package org.app.projectpharmacy.repository;

import org.app.projectpharmacy.entities.Customer;
import org.app.projectpharmacy.entities.Stock;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CustomerRepository extends BaseRepository<Customer> {


    private static final String INSERT_QUERY =
            "INSERT INTO customer (id, name, phone_number, email_address, description, created, updated) VALUES (?, ?, ?, ?, ?, ?, ?)";

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
    
    @Override
    public void create(Customer customer) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(INSERT_QUERY)) {
            ps.setString(1, customer.getId());
            ps.setString(2, customer.getName());
            ps.setString(3, customer.getPhoneNumber());
            ps.setString(4, customer.getPhoneNumber());
            ps.setString(5, customer.getEmailAddress());
            ps.setString(5, customer.getDescription());
            ps.setTimestamp(6, customer.getCreated());
            ps.setTimestamp(7, customer.getUpdated());
            ps.executeUpdate();
        }
    }

    @Override
    public List<Customer> getMany(String query, Object... params) throws SQLException {
        if (query == null) {
            query = FIND_ALL_QUERY;
        }
        return super.getMany(query, params);
    }

    public List<Customer> getByName(String name) throws SQLException {
        String findQueryByName = "SELECT * FROM customer WHERE name like ? ";
        Object[] params = { name };
        return this.getMany(findQueryByName, params);
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
