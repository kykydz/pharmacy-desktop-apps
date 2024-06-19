package org.app.projectpharmacy.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
    String host;
    String port;
    String dialect;
    String username;
    String password;

    public DatabaseManager(String host, String username, String password, String port) {
        this.host = host != null ? host : "jdbc:postgresql://localhost:5432/pharmacy";
        this.username = username != null ? username : "riogonzales";
        this.password = password != null ? password : "agusto10";
        this.port = port;
    }

    public Connection connect() throws SQLException {
        return DriverManager.getConnection(host, username, password);
    }
}
