package com.shevtsov.dao.impl;

import com.shevtsov.dao.DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionInMemory implements DBConnection {
    private static final String DB_URL = "jdbc:h2:mem:test";
    private static final String LOGIN = "";
    private static final String PASSWORD = "";

    @Override
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, LOGIN, PASSWORD);
    }
}
