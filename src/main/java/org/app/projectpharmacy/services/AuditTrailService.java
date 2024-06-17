package org.app.projectpharmacy.services;

import javafx.stage.Stage;
import org.app.projectpharmacy.entities.AuditTrail;
import org.app.projectpharmacy.repository.AuditTrailRepository;
import org.app.projectpharmacy.repository.CustomerRepository;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class AuditTrailService {
    private final AuditTrailRepository auditTrailRepository;

    public AuditTrailService() throws SQLException {
        this.auditTrailRepository = new AuditTrailRepository();
    }

    public List<AuditTrail> fetchAllRecord() throws SQLException {
        return auditTrailRepository.getMany(null);
    }

    public AuditTrail create(String feature, String action, String metaData) throws SQLException {
        String adminId = "admin123"; // dummy for now
        AuditTrail auditTrail = new AuditTrail(
                UUID.randomUUID().toString(),
                adminId,
                feature,
                action,
                metaData,
                Timestamp.valueOf(LocalDateTime.now()),
                Timestamp.valueOf(LocalDateTime.now())
        );
        auditTrailRepository.create(auditTrail);

        // audit log

        return auditTrail;
    }

    public List<AuditTrail> findLogByWord(String word) throws SQLException {
        return auditTrailRepository.getByWord(word);
    }
}
