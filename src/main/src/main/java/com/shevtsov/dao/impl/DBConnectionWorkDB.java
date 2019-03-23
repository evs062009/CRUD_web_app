package com.shevtsov.dao.impl;

import com.shevtsov.dao.DBConnection;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Repository
public class DBConnectionWorkDB implements DBConnection {

    @Override
    public Connection getConnection() throws SQLException {
        String url = "jdbc:h2:tcp://localhost/~/LuxoftShop";
        String login = "test";
        String password = "test";
        return DriverManager.getConnection(url, login, password);
    }
}
