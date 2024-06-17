package org.app.projectpharmacy.repository;

import org.app.projectpharmacy.entities.AuditTrail;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AuditTrailRepository extends BaseRepository<AuditTrail> {


    private static final String INSERT_QUERY =
            "INSERT INTO audit_trail (id, admin_id, feature_accessed, action, meta_data, " +
                    "created, updated) VALUES " +
                    "(?, ?, ?, ?, ?, ?, ?)";

    private static final String FIND_ALL_QUERY = "SELECT * FROM audit_trail";

    private static final String FIND_BY_ID_QUERY = "SELECT * FROM audit_trail WHERE id = ?";

    public AuditTrailRepository() throws SQLException {
        super();
    }

    @Override
    public String getTableName() {
        return "audit_trail";
    }

    @Override
    public void create(AuditTrail auditTrail) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(INSERT_QUERY)) {
            ps.setString(1, auditTrail.getId());
            ps.setString(2, auditTrail.getAdminId());
            ps.setString(3, auditTrail.getFeatureAccessed());
            ps.setString(4, auditTrail.getAction());
            ps.setString(5, auditTrail.getMetaData());
            ps.setTimestamp(6, auditTrail.getCreated());
            ps.setTimestamp(7, auditTrail.getUpdated());
            ps.executeUpdate();
        }
    }

    @Override
    public List<AuditTrail> getMany(String query, Object... params) throws SQLException {
        if (query == null) {
            query = FIND_ALL_QUERY;
        }
        return super.getMany(query, params);
    }

    @Override
    public void update(AuditTrail entity) {

    }

    public List<AuditTrail> getByWord(String word) throws SQLException {
        String findQueryByAdminId = "SELECT * FROM audit_trail WHERE admin_id like ? OR feature_accessed like ? OR " +
                "action like ? OR meta_data like ?";
        Object[] params = { word, word, word, word };
        return this.getMany(findQueryByAdminId, params);
    }

    @Override
    protected AuditTrail mapResultSetToEntity(ResultSet rs) throws SQLException {
        return new AuditTrail(
                rs.getString("id"),
                rs.getString("admin_id"),
                rs.getString("feature_accessed"),
                rs.getString("action"),
                rs.getString("meta_data"),
                rs.getTimestamp("created"),
                rs.getTimestamp("updated"));
    }
}
