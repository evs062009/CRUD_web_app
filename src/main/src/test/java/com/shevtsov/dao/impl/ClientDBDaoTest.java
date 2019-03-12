package com.shevtsov.dao.impl;

import com.shevtsov.dao.ClientDao;
import com.shevtsov.dao.DBConnection;
import com.shevtsov.domain.Client;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(JUnit4.class)
public class ClientDBDaoTest {
    private DBConnection dbConnection = new DBConnectionInMemory();
    private Connection connection;
    private ClientDao clientDao;
    private Client client;

    @Before
    public void setUp() {
        clientDao = new ClientDBDao(dbConnection);
        client = new Client(1, "name", "surname", 0, "phone", "email");

        try {
            connection = dbConnection.getConnection();
            try (PreparedStatement statement = connection.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS CLIENTS (ID BIGINT PRIMARY KEY AUTO_INCREMENT," +
                            "NAME VARCHAR(20),SURNAME VARCHAR(20), AGE INT, PHONE VARCHAR(20), EMAIL VARCHAR(50))")) {
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
        long actual = clientDao.save(client);
        //THEN
        Assert.assertEquals(1, actual);
    }

    @Test
    public void getAll() {
        //GIVEN
        List<Client> expected = new ArrayList<>();
        expected.add(client);
        addClientToTestDB();
        //WHEN
        List<Client> actual = clientDao.getAll();
        //THEN
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void findByID() {
        //GIVEN
        addClientToTestDB();
        //WHEN
        Optional<Client> actual = clientDao.findByID(client.getId());
        //THEN
        Assert.assertEquals(Optional.of(client), actual);
    }

    @Test
    public void findByPhone() {
        //GIVEN
        addClientToTestDB();
        //WHEN
        long actual = clientDao.findByPhone(client.getPhone());
        //THEN
        Assert.assertEquals(1, actual);
    }

    @Test
    public void isContainsKey() {
        //GIVEN
        addClientToTestDB();
        //WHEN
        boolean actual = clientDao.isContainsKey(client.getId());
        //THEN
        Assert.assertTrue(actual);
    }

    @Test
    public void remove() {
        //GIVEN
        addClientToTestDB();
        //WHEN
        clientDao.remove(client.getId());
        //THEN
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM CLIENTS WHERE ID = ?")) {
            statement.setLong(1, client.getId());
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()){
                    Assert.fail();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void modify() {
        //GIVEN
        addClientToTestDB();
        Client modifiedClient = new Client(client.getId(), "newname", "newsurname", 100,
                "newphone", "mewemail");
        //WHEN
        boolean actual = clientDao.modify(modifiedClient);
        //THEN
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM CLIENTS WHERE ID = ?")) {
            statement.setLong(1, client.getId());
            try (ResultSet resultSet = statement.executeQuery()) {
                if (!resultSet.next()) {
                    Assert.fail();
                }
                Assert.assertEquals((long)modifiedClient.getId(), resultSet.getLong("ID"));
                Assert.assertEquals(modifiedClient.getName(), resultSet.getString("NAME"));
                Assert.assertEquals(modifiedClient.getSurname(), resultSet.getString("SURNAME"));
                Assert.assertEquals((int)modifiedClient.getAge(), resultSet.getInt("AGE"));
                Assert.assertEquals(modifiedClient.getPhone(), resultSet.getString("PHONE"));
                Assert.assertEquals(modifiedClient.getEmail(), resultSet.getString("EMAIL"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Assert.assertTrue(actual);
    }

    private void addClientToTestDB() {
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO CLIENTS (NAME, SURNAME, AGE, PHONE, EMAIL) VALUES(?, ?, ?, ?, ?)")) {
            statement.setString(1, client.getName());
            statement.setString(2, client.getSurname());
            statement.setInt(3, client.getAge());
            statement.setString(4, client.getPhone());
            statement.setString(5, client.getEmail());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}