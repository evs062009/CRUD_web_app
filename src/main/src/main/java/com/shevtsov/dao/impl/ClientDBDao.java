package com.shevtsov.dao.impl;

import com.shevtsov.dao.ClientDao;
import com.shevtsov.domain.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDBDao implements ClientDao {
    private static final ClientDao INSTANCE = new ClientDBDao();
    private static final String DB_URL = "jdbc:h2:tcp://localhost/~/LuxoftShop";
    private static final String LOGIN = "test";
    private static final String PASSWORD = "test";

    private ClientDBDao() {
        try (Connection connection = DriverManager.getConnection(DB_URL, LOGIN, PASSWORD);
             Statement statement = connection.createStatement()) {
            statement.executeQuery("create table if not exists client (id BIGint  primary key AUTO_INCREMENT," +
                    "NAME VARCHAR(20),SURNAME VARCHAR(20), AGE INT, PHONE VARCHAR(20), EMAIL VARCHAR(50))");
        } catch (SQLException e) {
            System.out.println("SOMETHING GOING WRONG!!!");
        }
    }

    public static ClientDao getInstance() {
        return INSTANCE;
    }

    @Override
    public long save(Client client) {
        try (Connection connection = DriverManager.getConnection(DB_URL, LOGIN, PASSWORD);
             PreparedStatement statement = connection.prepareStatement("insert into client (NAME, SURNAME, age," +
                     "phone,email) values (?, ?, ?, ?, ?)")) {
            statement.setString(1, client.getName());
            statement.setString(2, client.getSurname());
            statement.setInt(3, client.getAge());
            statement.setString(4, client.getPhone());
            statement.setString(5, client.getEmail());
            if (statement.execute()) {
                return findByPhone(client.getPhone());
            }
        } catch (SQLException e) {
            System.out.println("SOMETHING GOING WRONG!!!");
        }
        return -1;
    }

    @Override
    public Client findByID(long id) {
        try (Connection connection = DriverManager.getConnection(DB_URL, LOGIN, PASSWORD);
             Statement statement = connection.createStatement();) {
            try (ResultSet resultSet = statement.executeQuery("SELECT * FROM CLIENT WHERE ID = '" + id + "'");) {
                resultSet.next();
                long clientID = resultSet.getLong(1);
                String name = resultSet.getString(2);
                String surname = resultSet.getString(3);
                int age = resultSet.getInt(4);
                String phone = resultSet.getString(5);
                String email = resultSet.getString(6);
                return new Client(clientID, name, surname, age, phone, email);
            }
        } catch (SQLException e) {
            System.out.println("SOMETHING GOING WRONG!!!");
            return null;
        }
    }

    @Override
    public void remove(long id) {
        try (Connection connection = DriverManager.getConnection(DB_URL, LOGIN, PASSWORD);
             PreparedStatement statement = connection.prepareStatement("delete from client where id = ?")) {
            statement.setLong(1, id);
            statement.execute();
        } catch (SQLException e) {
            System.out.println("SOMETHING GOING WRONG!!!");
        }
    }

    @Override
    public List<Client> gatAll() {
        try (Connection connection = DriverManager.getConnection(DB_URL, LOGIN, PASSWORD);
             Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery("select * from client")) {
                List<Client> clients = new ArrayList<>();
                while (resultSet.next()) {
                    long clientID = resultSet.getLong(1);
                    String name = resultSet.getString(2);
                    String surname = resultSet.getString(3);
                    int age = resultSet.getInt(4);
                    String phone = resultSet.getString(5);
                    String email = resultSet.getString(6);
                    clients.add(new Client(clientID, name, surname, age, phone, email));
                }
                return clients;
            }
        } catch (SQLException e) {
            System.out.println("SOMETHING GOING WRONG!!!");
        }
        return null;
    }

    @Override
    public long findByPhone(String phone) {
        try (Connection connection = DriverManager.getConnection(DB_URL, LOGIN, PASSWORD); Statement statement =
                connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery("select id from client where phone = '" +
                    phone + "'")) {
                if (resultSet.next()) {
                    return resultSet.getLong(1);
                }
            }
        } catch (SQLException e) {
            System.out.println("SOMETHING GOING WRONG!!!");
        }
        return -1;
    }

    @Override
    public boolean isContainsKey(long id) {
        try(Connection connection = DriverManager.getConnection(DB_URL, LOGIN, PASSWORD); Statement statement =
                connection.createStatement()){
            try(ResultSet resultSet = statement.executeQuery("select id from client where id = '" + id + "'")){
                return resultSet.next();
            }
        } catch (SQLException e){
            System.out.println("SOMETHING GOING WRONG!!!");
        }
        return false;
    }
}