package com.shevtsov.dao.impl;

import com.shevtsov.dao.DBConnection;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Repository
public class DBConnectionWorkDB implements DBConnection {
    private static final String DB_URL = "jdbc:h2:tcp://localhost/~/LuxoftShop";
    private static final String LOGIN = "test";
    private static final String PASSWORD = "test";

    @Override
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, LOGIN, PASSWORD);
    }
}
