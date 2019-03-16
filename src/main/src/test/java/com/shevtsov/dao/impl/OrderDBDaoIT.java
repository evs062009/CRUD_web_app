package com.shevtsov.dao.impl;

import com.shevtsov.dao.DBConnection;
import com.shevtsov.dao.OrderDao;
import com.shevtsov.domain.Client;
import com.shevtsov.domain.Order;
import com.shevtsov.domain.Product;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderDBDaoIT {
    private OrderDao orderDao;
    private DBConnection dbConnection = new DBConnectionInMemory();
    private Connection connection;
    private Client client;
    private Product product;
    private Order order;

    @Before
    public void setUp() {
        orderDao = new OrderDBDao(dbConnection);
        client = new Client(1, "name", "surname", 0, "phone", "email");
        product = new Product(1, "product", BigDecimal.valueOf(10));
        List<Product> products = new ArrayList<>();
        products.add(product);
        order = new Order(1, client, products);
        try {
            connection = dbConnection.getConnection();
            try (PreparedStatement statement = connection.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS ORDERS (ID BIGINT PRIMARY KEY AUTO_INCREMENT, CLIENT_ID BIGINT);" +
                            "CREATE TABLE IF NOT EXISTS SPECIFICATIONS (ORDER_ID BIGINT, PRODUCT_ID BIGINT)")) {
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
        //WHERE
        orderDao.save(order);
        //THERE
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM ORDERS WHERE ID = ?");
             PreparedStatement statement1 = connection.prepareStatement(
                     "SELECT * FROM SPECIFICATIONS WHERE ORDER_ID = ?")) {
            statement.setLong(1, order.getId());
            statement1.setLong(1, order.getId());
            try (ResultSet resultSet = statement.executeQuery()) {
                if (!resultSet.next()) {
                    Assert.fail();
                    return;
                }
                Assert.assertEquals((long) order.getId(), resultSet.getLong("ID"));
                Assert.assertEquals((long) order.getClient().getId(), resultSet.getLong("CLIENT_ID"));
            }
            try (ResultSet resultSet = statement1.executeQuery()) {
                if (!resultSet.next()) {
                    Assert.fail();
                    return;
                }
                Assert.assertEquals((long) order.getId(), resultSet.getLong("ORDER_ID"));
                Assert.assertEquals((long) order.getProducts().get(0).getId(),resultSet.getLong("PRODUCT_ID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getAll() {
        //GIVEN
        List<Order> expected = new ArrayList<>();
        expected.add(order);
        addOrderToTestDB();
        //WHEN
        List<Order> actual = orderDao.getAll();
        //THEN
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getUserOrders() {
        //GIVEN
        List<Order> expected = new ArrayList<>();
        expected.add(order);
        addOrderToTestDB();
        //WHEN
        List<Order> actual = orderDao.getUserOrders(client.getId());
        //THEN
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void findByID() {
        //GIVEN
        addOrderToTestDB();
        //WHEN
        Optional<Order> actual = orderDao.findByID(order.getId());
        //THEN
        Assert.assertEquals(Optional.of(order), actual);
    }

    @Test
    public void modify() {
        //GIVEN
        addOrderToTestDB();
        Product newProduct = new Product(product.getId() + 1, "newname", BigDecimal.valueOf(100));
        Order modifiedOrder = new Order(order);
        modifiedOrder.getProducts().clear();
        modifiedOrder.getProducts().add(newProduct);
        //WHEN
        orderDao.modify(modifiedOrder);
        //THEN
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM SPECIFICATIONS WHERE ORDER_ID = ?")) {
            statement.setLong(1, modifiedOrder.getId());
            try (ResultSet resultSet = statement.executeQuery()) {
                if (!resultSet.next()) {
                    Assert.fail();
                    return;
                }
                Assert.assertEquals((long)newProduct.getId(), resultSet.getLong("PRODUCT_ID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void remove() {
        //GIVEN
        addOrderToTestDB();
        //WHEN
        orderDao.remove(order.getId());
        //THEN
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM ORDERS WHERE ID = ?");
             PreparedStatement statement1 = connection.prepareStatement(
                     "SELECT * FROM SPECIFICATIONS WHERE ORDER_ID = ?")) {
            statement.setLong(1, order.getId());
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Assert.fail();
                    return;
                }
            }
            statement1.setLong(1, order.getId());
            try (ResultSet resultSet = statement1.executeQuery()) {
                if (resultSet.next()) {
                    Assert.fail();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addOrderToTestDB() {
        try (PreparedStatement statement = connection.prepareStatement(
                "CREATE TABLE IF NOT EXISTS CLIENTS (ID BIGINT PRIMARY KEY AUTO_INCREMENT, NAME VARCHAR(20)," +
                        "SURNAME VARCHAR(20), AGE INT, PHONE VARCHAR(20), EMAIL VARCHAR(50));" +
                        "CREATE TABLE IF NOT EXISTS PRODUCTS (ID BIGINT PRIMARY KEY AUTO_INCREMENT,NAME VARCHAR(20)," +
                        "PRICE DECIMAL)")) {
            statement.executeUpdate();
            addClient();
            addProduct();
            addOrder();
            addSpecification();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addClient() throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO CLIENTS (NAME, SURNAME, AGE, PHONE, EMAIL) VALUES(?, ?, ?, ?, ?)")) {
            statement.setString(1, client.getName());
            statement.setString(2, client.getSurname());
            statement.setInt(3, client.getAge());
            statement.setString(4, client.getPhone());
            statement.setString(5, client.getEmail());
            statement.executeUpdate();
        }
    }

    private void addProduct() throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO PRODUCTS (NAME, PRICE) VALUES(?, ?)")) {
            statement.setString(1, product.getName());
            statement.setBigDecimal(2, product.getPrice());
            statement.executeUpdate();
        }
    }

    private void addOrder() throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO ORDERS (CLIENT_ID) VALUES (?)")) {
            statement.setLong(1, client.getId());
            statement.executeUpdate();
        }
    }

    private void addSpecification() throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO SPECIFICATIONS (ORDER_ID, PRODUCT_ID) VALUES (?, ?)")) {
            statement.setLong(1, order.getId());
            statement.setLong(2, product.getId());
            statement.executeUpdate();
        }
    }
}