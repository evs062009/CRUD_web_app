package com.shevtsov.dao.impl;

import com.shevtsov.dao.ClientDao;
import com.shevtsov.domain.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClientDBDao implements ClientDao {
    private static final ClientDao INSTANCE = new ClientDBDao();

    private ClientDBDao() {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                "CREATE TABLE IF NOT EXISTS CLIENTS (ID BIGINT PRIMARY KEY AUTO_INCREMENT," +
                        "NAME VARCHAR(20),SURNAME VARCHAR(20), AGE INT, PHONE VARCHAR(20), EMAIL VARCHAR(50))")) {
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ClientDao getInstance() {
        return INSTANCE;
    }

    @Override
    public long save(Client client) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO CLIENTS (NAME, SURNAME, AGE," +
                     "PHONE,EMAIL) VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            setStatementParams(client, statement);
            statement.executeUpdate();
            try (ResultSet keys = statement.getGeneratedKeys()) {
                if (keys.next()) {
                    return keys.getLong("ID");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public List<Client> gatAll() {
        List<Client> clients = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM CLIENTS"); ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                clients.add(getClient(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clients;
    }

    @Override
    public Optional<Client> findByID(long id) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT * FROM CLIENTS WHERE ID = ?")) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(getClient(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public long findByPhone(String phone) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                "SELECT ID FROM CLIENTS WHERE PHONE = ?")) {
            statement.setString(1, phone);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getLong("ID");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public boolean isContainsKey(long id) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT ID FROM CLIENTS WHERE ID = ?")) {
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
    public void remove(long id) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "DELETE FROM CLIENTS WHERE ID = ?")) {
            statement.setLong(1, id);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean modify(Client client) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                "UPDATE CLIENTS SET NAME = ?, SURNAME = ?, AGE = ?, PHONE = ?, EMAIL = ? WHERE ID = ?")) {
            setStatementParams(client, statement);
            statement.setLong(6, client.getId());
            return statement.executeUpdate() != 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private Client getClient(ResultSet resultSet) throws SQLException {
        long clientID = resultSet.getLong("ID");
        String name = resultSet.getString("NAME");
        String surname = resultSet.getString("SURNAME");
        int age = resultSet.getInt("AGE");
        String phone = resultSet.getString("PHONE");
        String email = resultSet.getString("EMAIL");
        return new Client(clientID, name, surname, age, phone, email);
    }

    private void setStatementParams(Client client, PreparedStatement statement) throws SQLException {
        statement.setString(1, client.getName());
        statement.setString(2, client.getSurname());
        statement.setInt(3, client.getAge());
        statement.setString(4, client.getPhone());
        statement.setString(5, client.getEmail());
    }
}