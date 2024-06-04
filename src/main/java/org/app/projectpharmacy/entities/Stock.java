package org.app.projectpharmacy.entities;


import java.sql.Timestamp;

public class Stock {

    private final String id;
    private String medicationName;
    private String description;
    private Integer price;

    private Integer quantityAvailable;
    private final Timestamp created;
    private final Timestamp updated;

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

    public Integer getPrice() {
        return price;
    }

    public Integer getQuantityAvailable() {
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
}
