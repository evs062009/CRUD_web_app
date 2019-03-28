package com.shevtsov.dao.impl.DBDao;

import com.shevtsov.dao.DBConnection;
import com.shevtsov.dao.OrderDao;
import com.shevtsov.domain.Client;
import com.shevtsov.domain.Order;
import com.shevtsov.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class OrderDBDao implements OrderDao {
    private DBConnection dbConnection;

    @Autowired
    public OrderDBDao(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
        try (Connection connection = dbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "CREATE TABLE IF NOT EXISTS ORDERS (ID BIGINT PRIMARY KEY AUTO_INCREMENT," +
                             "CLIENT_ID BIGINT);" +
                             "CREATE TABLE IF NOT EXISTS SPECIFICATIONS (ORDER_ID BIGINT, PRODUCT_ID" +
                             " BIGINT)")) {
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean save(Order order) {
        try (Connection connection = dbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO ORDERS (CLIENT_ID) VALUES (?)", Statement.RETURN_GENERATED_KEYS)) {
            statement.setLong(1, order.getClient().getId());
            statement.executeUpdate();
            try (ResultSet keys = statement.getGeneratedKeys()) {
                if (keys.next()) {
                    long orderID = keys.getLong("ID");
                    try (PreparedStatement statement1 = connection.prepareStatement(
                            "INSERT INTO SPECIFICATIONS (ORDER_ID, PRODUCT_ID) VALUES (?, ?)")) {
                        for (Product product : order.getProducts()) {
                            statement1.setLong(1, orderID);
                            statement1.setLong(2, product.getId());
                            statement1.executeUpdate();
                        }
                        return true;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Order> getAll() {
        List<Order> orders = new ArrayList<>();
        try (Connection connection = dbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT ORDERS.ID, CLIENT_ID, NAME, SURNAME, AGE, PHONE, EMAIL FROM ORDERS LEFT JOIN CLIENTS " +
                             "ON CLIENT_ID = CLIENTS.ID"); ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                orders.add(getOrder(connection, resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    @Override
    public List<Order> getUserOrders(long currentUserID) {
        List<Order> userOreders = new ArrayList<>();
        try (Connection connection = dbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT ORDERS.ID, CLIENT_ID, NAME, SURNAME, AGE, PHONE, EMAIL FROM ORDERS LEFT JOIN CLIENTS " +
                             "ON CLIENT_ID = CLIENTS.ID WHERE CLIENTS.ID = ?")) {
            statement.setLong(1, currentUserID);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    userOreders.add(getOrder(connection, resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userOreders;
    }

    @Override
    public Optional<Order> findByID(long id) {
        try (Connection connection = dbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT ORDERS.ID, CLIENT_ID, NAME, SURNAME, AGE, PHONE, EMAIL FROM ORDERS LEFT JOIN CLIENTS " +
                             "ON CLIENT_ID = CLIENTS.ID WHERE ORDERS.ID = ?")) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(getOrder(connection, resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public boolean modify(Order draft) {
        try (Connection connection = dbConnection.getConnection()) {
            deleteOrderSpecification(draft.getId(), connection);
            addOrderSpecification(draft.getId(), draft.getProducts(), connection);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean remove(long id) {
        try (Connection connection = dbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "DELETE FROM ORDERS WHERE ID = ?")) {
            statement.setLong(1, id);
            boolean isRemoveSuccess = statement.executeUpdate() != 0;
            if (isRemoveSuccess) {
                deleteOrderSpecification(id, connection);
            }
            return isRemoveSuccess;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private Order getOrder(Connection connection, ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong("ID");
        long clientID = resultSet.getLong("CLIENT_ID");
        String name = resultSet.getString("NAME");
        String surname = resultSet.getString("SURNAME");
        int age = resultSet.getInt("AGE");
        String phone = resultSet.getString("PHONE");
        String email = resultSet.getString("EMAIL");
        Client client = new Client(clientID, name, surname, age, phone, email);
        List<Product> products = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT PRODUCT_ID, NAME, PRICE FROM SPECIFICATIONS LEFT JOIN PRODUCTS" +
                        " ON SPECIFICATIONS.PRODUCT_ID = PRODUCTS.ID WHERE ORDER_ID = ?")) {
            statement.setLong(1, id);
            try (ResultSet resultSet1 = statement.executeQuery()) {
                while (resultSet1.next()) {
                    long productID = resultSet1.getLong("PRODUCT_ID");
                    String productName = resultSet1.getString("NAME");
                    BigDecimal price = resultSet1.getBigDecimal("PRICE");
                    products.add(new Product(productID, productName, price));
                }
            }
        }
        return new Order(id, client, products);
    }

    private void deleteOrderSpecification(long orderID, Connection connection) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(
                "DELETE FROM SPECIFICATIONS WHERE ORDER_ID = ?")) {
            statement.setLong(1, orderID);
            statement.execute();
        }
    }

    private void addOrderSpecification(long id, List<Product> products, Connection connection) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO SPECIFICATIONS (ORDER_ID, PRODUCT_ID) VALUES (?, ?)")) {
            statement.setLong(1, id);
            for (Product product : products) {
                statement.setLong(2, product.getId());
                statement.execute();
            }
        }
    }
}