package com.shevtsov.dao.impl;

import com.shevtsov.dao.ProductDao;
import com.shevtsov.domain.Product;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDBDao implements ProductDao {
    private static final ProductDao INSTANCE = new ProductDBDao();

    private ProductDBDao() {
        try (Connection connection = DriverManager.getConnection(DBCostants.DB_URL, DBCostants.LOGIN,
                DBCostants.PASSWORD); PreparedStatement statement = connection.prepareStatement(
                "CREATE TABLE IF NOT EXISTS PRODUCTS (ID BIGINT PRIMARY KEY AUTO_INCREMENT," +
                        "NAME VARCHAR(20), PRICE DECIMAL)")) {
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ProductDao getInstance() {
        return INSTANCE;
    }

    @Override
    public void save(Product product) {
        try (Connection connection = DriverManager.getConnection(DBCostants.DB_URL, DBCostants.LOGIN,
                DBCostants.PASSWORD); PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO PRODUCTS (NAME, PRICE) VALUES (?, ?)")) {
            statement.setString(1, product.getName());
            statement.setBigDecimal(2, product.getPrice());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(long id) {
        try (Connection connection = DriverManager.getConnection(DBCostants.DB_URL, DBCostants.LOGIN,
                DBCostants.PASSWORD); PreparedStatement statement = connection.prepareStatement(
                "DELETE FROM PRODUCTS WHERE ID = ?")) {
            statement.setLong(1, id);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Product> getAll() {
        try (Connection connection = DriverManager.getConnection(DBCostants.DB_URL, DBCostants.LOGIN,
                DBCostants.PASSWORD); PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM PRODUCTS"); ResultSet resultSet = statement.executeQuery()) {
            List<Product> products = new ArrayList<>();
            while (resultSet.next()) {
                products.add(getProduct(resultSet));
            }
            return products;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean isContainsKey(long id) {
        try (Connection connection = DriverManager.getConnection(DBCostants.DB_URL, DBCostants.LOGIN,
                DBCostants.PASSWORD); PreparedStatement statement = connection.prepareStatement(
                "SELECT ID FROM PRODUCTS WHERE ID = ?")) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Product findByID(long id) {
        try (Connection connection = DriverManager.getConnection(DBCostants.DB_URL, DBCostants.LOGIN,
                DBCostants.PASSWORD); PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM PRODUCTS WHERE ID = ?")) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return getProduct(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Product getProduct(ResultSet resultSet) throws SQLException {
        long productID = resultSet.getLong("ID");
        String name = resultSet.getString("NAME");
        BigDecimal price = resultSet.getBigDecimal("PRICE");
        return new Product(productID, name, price);
    }

    @Override
    public boolean modify(Product product) {
        try (Connection connection = DriverManager.getConnection(DBCostants.DB_URL, DBCostants.LOGIN,
                DBCostants.PASSWORD); PreparedStatement statement = connection.prepareStatement(
                "UPDATE PRODUCTS SET NAME = ?, PRICE = ? WHERE ID = ?")) {
            statement.setString(1, product.getName());
            statement.setBigDecimal(2, product.getPrice());
            statement.setLong(3, product.getId());
            return statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}