package com.example.demo.databaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private final String url = "jdbc:postgresql://localhost/";
    private final String user = "brentleytrucker";
    private final String password = "";

    public Connection connect() {
        try {
            return DriverManager.getConnection(url, user, password);
        }
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
