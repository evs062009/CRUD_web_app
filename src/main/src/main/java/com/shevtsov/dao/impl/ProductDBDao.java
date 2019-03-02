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
            System.out.println("SOMETHING GOING WRONG!!!");
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
            System.out.println("SOMETHING GOING WRONG!!!");
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
            System.out.println("SOMETHING GOING WRONG!!!");
        }
    }

    @Override
    public List<Product> getAll() {
        try (Connection connection = DriverManager.getConnection(DBCostants.DB_URL, DBCostants.LOGIN,
                DBCostants.PASSWORD); Statement statement = connection.createStatement()) {
            try(ResultSet resultSet = statement.executeQuery("SELECT * FROM PRODUCTS")){
                List<Product> products = new ArrayList<>();
                while (resultSet.next()){
                    long id = resultSet.getLong(1);
                    String name = resultSet.getString(2);
                    BigDecimal price = resultSet.getBigDecimal(3);
                    products.add(new Product(id, name, price));
                }
                return products;
            }
        } catch (SQLException e){
            System.out.println("SOMETHING GOING WRONG!!!");
        }
        return null;
    }

    @Override
    public boolean isContainsKey(long id) {
        try(Connection connection = DriverManager.getConnection(DBCostants.DB_URL, DBCostants.LOGIN,
                DBCostants.PASSWORD); Statement statement = connection.createStatement()){
            try(ResultSet resultSet = statement.executeQuery("SELECT ID FROM PRODUCTS WHERE ID = '" + id + "'")){
                return resultSet.next();
            }
        } catch (SQLException e){
            System.out.println("SOMETHING GOING WRONG!!!");
        }
        return false;
    }

    @Override
    public Product findByID(long id) {
        try(Connection connection = DriverManager.getConnection(DBCostants.DB_URL, DBCostants.LOGIN,
                DBCostants.PASSWORD); Statement statement = connection.createStatement()){
            try(ResultSet resultSet = statement.executeQuery("SELECT * FROM PRODUCTS WHERE ID = '" + id + "'")){
                if (resultSet.next()){
                    long productID = resultSet.getLong(1);
                    String name = resultSet.getString(2);
                    BigDecimal price = resultSet.getBigDecimal(3);
                    return new Product(productID, name, price);
                }
            }
        } catch (SQLException e){
            System.out.println("SOMETHING GOING WRONG!!!");
        }
        return null;
    }

    @Override
    public boolean modify(Product product) {
        try(Connection connection = DriverManager.getConnection(DBCostants.DB_URL, DBCostants.LOGIN,
                DBCostants.PASSWORD); PreparedStatement statement = connection.prepareStatement(
                        "UPDATE PRODUCTS SET NAME = ?, PRICE = ? WHERE ID = '" + product.getId() + "'")){
            statement.setString(1, product.getName());
            statement.setBigDecimal(2, product.getPrice());
            return statement.execute();
        } catch (SQLException e){
            System.out.println("SOMETHING GOING WRONG!!!");
        }
        return false;
    }
}