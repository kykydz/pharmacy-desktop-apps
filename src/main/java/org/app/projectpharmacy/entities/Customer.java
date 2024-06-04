package org.app.projectpharmacy.entities;
import java.sql.Timestamp;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Customer {
    private String id;
    private String name;
    private String phoneNumber;
    private String emailAddress;
    private String description;

    private final StringProperty idProperty = new SimpleStringProperty();
    private final StringProperty nameProperty = new SimpleStringProperty();
    private final StringProperty phoneNumberProperty = new SimpleStringProperty();
    private final StringProperty emailAddressProperty = new SimpleStringProperty();
    private final StringProperty descriptionProperty = new SimpleStringProperty();
    private final Timestamp created;
    private final Timestamp updated;

    public Customer(String id, String name, String phoneNumber, String emailAddress, String description, Timestamp created, Timestamp updated) {
        this.idProperty.set(id);
        this.nameProperty.set(name);
        this.phoneNumberProperty.set(phoneNumber);
        this.emailAddressProperty.set(emailAddress);
        this.descriptionProperty.set(description);
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.description = description;
        this.created = created;
        this.updated = updated;
    }

    // Getters
    public StringProperty getIdProperty() {
        return idProperty;
    }

    public StringProperty getNameProperty() {
        return nameProperty;
    }

    public StringProperty getPhoneNumberProperty() {
        return phoneNumberProperty;
    }

    public StringProperty getEmailAddressProperty() {
        return emailAddressProperty;
    }

    public StringProperty getDescriptionProperty() {
        return descriptionProperty;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getDescription() {
        return description;
    }

    public Timestamp getCreated() {
        return created;
    }

    public Timestamp getUpdated() {
        return updated;
    }

    // Setters
    public void setId(String id) {
        this.idProperty.set(id);
    }

    public void setName(String name) {
        this.nameProperty.set(name);
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumberProperty.set(phoneNumber);
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddressProperty.set(emailAddress);
    }

    public void setDescription(String description) {
        this.descriptionProperty.set(description);
    }
}

