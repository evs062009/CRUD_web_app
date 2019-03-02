package com.shevtsov.dao.impl;

import com.shevtsov.dao.ClientDao;
import com.shevtsov.dao.OrderDao;
import com.shevtsov.dao.ProductDao;
import com.shevtsov.domain.Client;
import com.shevtsov.domain.Order;
import com.shevtsov.domain.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDBDao implements OrderDao {
    private static final OrderDao INSTANCE = new OrderDBDao();
    private static final ClientDao CLIENT_DAO = ClientDBDao.getInstance();
    private static final ProductDao PRODUCT_DAO = ProductDBDao.getInstance();

    private OrderDBDao() {
        boolean ordersCreated = false;
        boolean specificationCreated = false;
        try (Connection connection = DriverManager.getConnection(DBCostants.DB_URL, DBCostants.LOGIN,
                DBCostants.PASSWORD);
             PreparedStatement statement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS ORDERS" +
                     "(ID BIGINT PRIMARY KEY AUTO_INCREMENT, CLIENT_ID BIGINT)");
             PreparedStatement statement1 = connection.prepareStatement("CREATE TABLE IF NOT EXISTS SPECIFICATIONS" +
                     "(ORDER_ID BIGINT, PRODUCT_ID BIGINT)")) {
            statement.execute();
            statement1.execute();
        } catch (SQLException e) {
            System.out.println("SOMETHING GOING WRONG!!!");
        }
    }

    public static OrderDao getInstance() {
        return INSTANCE;
    }

    @Override
    public List<Order> getAll() {
        List<Order> orders = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DBCostants.DB_URL, DBCostants.LOGIN,
                DBCostants.PASSWORD); Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery("SELECT * FROM ORDERS")) {
                while (resultSet.next()) {
                    orders.add(createOrderFromDB(statement, resultSet));
                }
            }
            return orders;
        } catch (SQLException e) {
            System.out.println("SOMETHING GOING WRONG!!!");
        }
        return null;
    }

    private Order createOrderFromDB(Statement statement, ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong(1);
        Client client = CLIENT_DAO.findByID(resultSet.getLong(2));
        List<Product> products = new ArrayList<>();
        try (ResultSet resultSet1 = statement.executeQuery(
                "SELECT PRODUCT_ID FROM SPECIFICATIONS WHERE ORDER_ID = '" + id + "'")) {
            while (resultSet1.next()) {
                Product product = PRODUCT_DAO.findByID(resultSet1.getLong(1));
                products.add(product);
            }
        }
        return new Order(id, client, products);
    }

    @Override
    public Order findByID(long id) {
        try (Connection connection = DriverManager.getConnection(DBCostants.DB_URL, DBCostants.LOGIN,
                DBCostants.PASSWORD); Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(
                    "SELECT * FROM ORDERS WHERE ORDER_ID = '" + id + "'")) {
                if (resultSet.next()) {
                    return createOrderFromDB(statement, resultSet);
                }
            }
        } catch (SQLException e) {
            System.out.println("SOMETHING GOING WRONG!!!");
        }
        return null;
    }

    @Override
    public void save(Order order) {
        try (Connection connection = DriverManager.getConnection(DBCostants.DB_URL, DBCostants.LOGIN,
                DBCostants.PASSWORD); PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO ORDERS (CLIENT_ID) VALUES ?");
                //как получить id только что добавленного ордера
//                111
        ) {
        } catch (SQLException e) {
            System.out.println("SOMETHING GOING WRONG!!!");
        }
    }

    @Override
    public void remove(long id) {
        try (Connection connection = DriverManager.getConnection(DBCostants.DB_URL, DBCostants.LOGIN,
                DBCostants.PASSWORD); PreparedStatement statement = connection.prepareStatement(
                "DELETE FROM ORDERS WHERE ID = ?")) {
            statement.setLong(1, id);
            statement.execute();
            deleteOrderSpecification(id, connection);
        } catch (SQLException e) {
            System.out.println("SOMETHING GOING WRONG!!!");
        }
    }

    private void deleteOrderSpecification(long orderID, Connection connection) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(
                "DELETE FROM SPECIFICATIONS WHERE ORDER_ID = ?")) {
            statement.setLong(1, orderID);
            statement.execute();
        }
    }

    @Override
    public List<Order> getUserOrders(long currentUserID) {
        List<Order> userOreders = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DBCostants.DB_URL, DBCostants.LOGIN,
                DBCostants.PASSWORD); Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(
                    "SELECT * FROM ORDERS WHERE ID = '" + currentUserID + "'")) {
                while (resultSet.next()) {
                    userOreders.add(createOrderFromDB(statement, resultSet));
                }
                return userOreders;
            }
        } catch (SQLException e) {
            System.out.println("SOMETHING GOING WRONG!!!");
        }
        return null;
    }

    @Override
    public void modify(Order draft) {
        try (Connection connection = DriverManager.getConnection(DBCostants.DB_URL, DBCostants.LOGIN,
                DBCostants.PASSWORD)) {
            deleteOrderSpecification(draft.getId(), connection);
            addOrderSpecification(draft.getId(), draft.getProducts(), connection);
        } catch (SQLException e) {
            System.out.println("SOMETHING GOING WRONG!!!");
        }
    }

    private void addOrderSpecification(long id, List<Product> products, Connection connection) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO SPECIFICATIONS (ORDER_ID, PRODUCT_ID) VALUES (?, ?)")) {
            statement.setLong(1, id);
            products.forEach(product -> {
                try {
                    statement.setLong(2, product.getId());
                    statement.execute();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}