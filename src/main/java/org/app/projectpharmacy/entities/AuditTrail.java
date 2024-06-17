package org.app.projectpharmacy.entities;

import java.sql.Timestamp;

public class AuditTrail {
    private String id;
    private String adminId;
    private String featureAccessed;
    private String action;
    private String metaData;
    private final Timestamp created;
    private final Timestamp updated;

    public AuditTrail(String id, String adminId, String featureAccessed, String action, String metaData, Timestamp created, Timestamp updated) {
        this.id = id;
        this.adminId = adminId;
        this.featureAccessed = featureAccessed;
        this.action = action;
        this.metaData = metaData;
        this.created = created;
        this.updated = updated;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public String getFeatureAccessed() {
        return featureAccessed;
    }

    public void setFeatureAccessed(String featureAccessed) {
        this.featureAccessed = featureAccessed;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getMetaData() {
        return metaData;
    }

    public void setMetaData(String metaData) {
        this.metaData = metaData;
    }

    public Timestamp getCreated() {
        return created;
    }

    // No setter for created as it's auto-generated

    public Timestamp getUpdated() {
        return updated;
    }

    // No setter for updated as it's auto-generated
}
