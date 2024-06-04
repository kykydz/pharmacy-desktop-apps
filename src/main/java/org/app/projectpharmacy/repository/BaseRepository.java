package org.app.projectpharmacy.repository;

import org.app.projectpharmacy.entities.Stock;
import org.app.projectpharmacy.utils.DatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseRepository<T> {

    protected final Connection connection;

    public BaseRepository() throws SQLException {
        this.connection = new DatabaseManager(null, null, null, null).connect();
    }

    public abstract String getTableName();

    public abstract void create(T entity) throws SQLException;

    public List<T> getMany(String query, Object... params) throws SQLException {
        List<T> results = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            int parameterIndex = 1;
            for (Object param : params) {
                ps.setObject(parameterIndex++, param);
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                results.add(mapResultSetToEntity(rs));
            }
        } catch (SQLException e) {
            throw  new RuntimeException(e);
        }
        return results;
    }

    public T get(String id, String findQuery) throws SQLException {
        List<T> results = getMany(findQuery, id);
        return results.isEmpty() ? null : results.getFirst();
    }

    protected abstract T mapResultSetToEntity(ResultSet rs) throws SQLException;

    public void update(T entity, String updateQuery) throws SQLException {
    }

    public void delete(String id, String deleteQuery) throws SQLException {

    }
}
