package org.app.projectpharmacy.repository;

import org.app.projectpharmacy.entities.Stock;

import java.sql.*;
import java.util.List;

public class StockRepository extends BaseRepository<Stock> {
    private static final String INSERT_QUERY =
            "INSERT INTO stock (id, medication_name, description, price, quantity_available, created, updated) VALUES" +
                    " (?, ?, ?, ?, ?, ?, ?)";

    private static final String FIND_ALL_QUERY = "SELECT * FROM stock";

    private static final String FIND_BY_ID_QUERY = "SELECT * FROM stock WHERE id = ?";

    private static final String UPDATE_QUERY =
            "UPDATE stock SET name = ?, phone_number = ?, email_address = ? WHERE id = ?";

    private static final String DELETE_QUERY = "DELETE FROM stock WHERE id = ?";

    public StockRepository() throws SQLException {
        super();
    }

    @Override
    public String getTableName() {
        return "stock";
    }

    @Override
    public void create(Stock stock) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(INSERT_QUERY)) {
            ps.setString(1, stock.getId());
            ps.setString(2, stock.getMedicationName());
            ps.setString(3, stock.getDescription());
            ps.setInt(4, stock.getPrice());
            ps.setInt(5, stock.getQuantityAvailable());
            ps.setTimestamp(6, stock.getCreated());
            ps.setTimestamp(7, stock.getUpdated());
            ps.executeUpdate();
        }
    }

    @Override
    public List<Stock> getMany(String query, Object... params) throws SQLException {
        if (query == null) {
            query = FIND_ALL_QUERY;
        }
        return super.getMany(query, params);
    }

    public Stock getById(String id) throws SQLException {
        return super.get(id, FIND_BY_ID_QUERY);
    }

    public List<Stock> getByMedicationName(String medicationName) throws SQLException {
        String findQueryByMedicationName = "SELECT * FROM stock WHERE medication_name = ? ";
        Object[] params = { medicationName };
        return this.getMany(findQueryByMedicationName, params);
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
