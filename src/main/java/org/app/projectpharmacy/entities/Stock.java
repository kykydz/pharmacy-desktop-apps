package org.app.projectpharmacy.entities;

import java.sql.Time;
import java.sql.Timestamp;

public class Stock {

    private String id;
    private String medicationName;
    private String description;
    private int price;
    private int quantityAvailable;
    private Timestamp created;
    private Timestamp updated;

    public Stock(String id, String medicationName, String description, int price, int quantityAvailable, Timestamp created, Timestamp updated) {
        this.id = id;
        this.medicationName = medicationName;
        this.description = description;
        this.price = price;
        this.quantityAvailable = quantityAvailable;
        this.created = created;
        this.updated = updated;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getMedicationName() {
        return medicationName;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantityAvailable() {
        return quantityAvailable;
    }

    public Timestamp getCreated() {
        return created;
    }

    public Timestamp getUpdated() {
        return updated;
    }

    // Setters (without setting updated timestamp for simplicity)
    public void setMedicationName(String medicationName) {
        this.medicationName = medicationName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setQuantityAvailable(int quantityAvailable) {
        this.quantityAvailable = quantityAvailable;
    }

    // You can add a method to update the updated timestamp if needed
    public void updateTimestamp() {
        this.updated = new Timestamp(System.currentTimeMillis());
    }
}
