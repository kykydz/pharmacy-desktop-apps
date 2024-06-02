package org.app.projectpharmacy.entities;

import org.app.projectpharmacy.utils.NameConventionConverter;
import org.app.projectpharmacy.utils.ObjectMapperConvention;

import java.lang.reflect.Field;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Customer {
    private String id;
    private String name;
    private String phoneNumber;
    private String emailAddress;
    private String description;
    private Timestamp created;
    private Timestamp updated;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public Timestamp getUpdated() {
        return updated;
    }

    public void setUpdated(Timestamp updated) {
        this.updated = updated;
    }

    public Customer(String id, String name, String phoneNumber, String emailAddress, String description,
                    Timestamp created,
                    Timestamp updated) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.description = description;
        this.created = created;
        this.updated = created;
    }


    // Utility methods for snake_case to camelCase and vice versa
    public Map<String, Object> toMap(boolean toCamelCase) {
        return ObjectMapperConvention.toMap(this, toCamelCase); // Use generic ObjectMapper
    }

    public static Customer fromMap(Map<String, Object> map, boolean fromCamelCase) {
        return ObjectMapperConvention.fromMap(map, Customer.class, fromCamelCase); // Use generic ObjectMapper
    }
}
