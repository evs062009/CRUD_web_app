package com.shevtsov.dao.impl;

import com.shevtsov.dao.DBConnection;
import com.shevtsov.dao.ProductDao;
import com.shevtsov.domain.Product;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductDBDaoTest {
    private ProductDao productDao;
    private DBConnection dbConnection = new DBConnectionInMemory();
    private Connection connection;
    private Product product;

    @Before
    public void setUp() {
        productDao = new ProductDBDao(dbConnection);
        product = new Product(1, "name", BigDecimal.valueOf(10));
        try {
            connection = dbConnection.getConnection();
            try (PreparedStatement statement = connection.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS PRODUCTS (ID BIGINT PRIMARY KEY AUTO_INCREMENT,NAME VARCHAR(20)," +
                            "PRICE DECIMAL)")) {
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @After
    public void tearDown() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void save() {
        //WHEN
        boolean actual = productDao.save(product);
        //THEN
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM PRODUCTS WHERE ID = ?")) {
            statement.setLong(1, product.getId());
            try (ResultSet resultSet = statement.executeQuery()) {
                if (!resultSet.next()) {
                    Assert.fail();
                    return;
                }
                Assert.assertEquals((long) product.getId(), resultSet.getLong("ID"));
                Assert.assertEquals(product.getName(), resultSet.getString("NAME"));
                Assert.assertEquals(product.getPrice(), resultSet.getBigDecimal("PRICE"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Assert.assertTrue(actual);
    }

    @Test
    public void getAll() {
        //GIVEN
        List<Product> expected = new ArrayList<>();
        expected.add(product);
        addProductToTestDB();
        //WHEN
        List<Product> actual = productDao.getAll();
        //THEN
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void findByID() {
        //GIVEN
        addProductToTestDB();
        //WHEN
        Optional<Product> actual = productDao.findByID(product.getId());
        //THEN
        Assert.assertEquals(Optional.of(product), actual);
    }

    @Test
    public void isContainsKey() {
        //GIVEN
        addProductToTestDB();
        //WHEN
        boolean actual = productDao.isContainsKey(product.getId());
        //THEN
        Assert.assertTrue(actual);
    }

    @Test
    public void modify() {
        //GIVEN
        addProductToTestDB();
        Product modifiedProduct = new Product(product.getId(), "newname", BigDecimal.valueOf(100));
        //WHEN
        boolean actual = productDao.modify(modifiedProduct);
        //THEN
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM PRODUCTS WHERE ID = ?")) {
            statement.setLong(1, modifiedProduct.getId());
            try (ResultSet resultSet = statement.executeQuery()) {
                if (!resultSet.next()) {
                    Assert.fail();
                    return;
                }
                Assert.assertEquals((long) modifiedProduct.getId(), resultSet.getLong("ID"));
                Assert.assertEquals(modifiedProduct.getName(), resultSet.getString("NAME"));
                Assert.assertEquals(modifiedProduct.getPrice(), resultSet.getBigDecimal("PRICE"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Assert.assertTrue(actual);
    }

    @Test
    public void remove() {
        //GIVEN
        addProductToTestDB();
        //WHEN
        boolean actual = productDao.remove(product.getId());
        //THEN
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM PRODUCTS WHERE ID = ?")) {
            statement.setLong(1, product.getId());
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Assert.fail();
                    return;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Assert.assertTrue(actual);
    }

    private void addProductToTestDB() {
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO PRODUCTS (NAME, PRICE) VALUES(?, ?)")) {
            statement.setString(1, product.getName());
            statement.setBigDecimal(2, product.getPrice());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}