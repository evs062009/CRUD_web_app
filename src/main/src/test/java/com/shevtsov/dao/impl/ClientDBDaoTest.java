package com.shevtsov.dao.impl;

import com.shevtsov.dao.ClientDao;
import com.shevtsov.dao.DBConnection;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.sql.*;

@RunWith(JUnit4.class)
public class ClientDBDaoTest {
    private DBConnection dbConnection = new DBConnectionInMemory();
    @Mock
    private ClientDao clientDao;
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();


    @Before
    public void setUp() {
        clientDao = new ClientDBDao(dbConnection);
        try (Connection connection = dbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "CREATE TABLE IF NOT EXISTS CLIENTS (ID BIGINT PRIMARY KEY AUTO_INCREMENT," +
                             "NAME VARCHAR(20),SURNAME VARCHAR(20), AGE INT, PHONE VARCHAR(20), EMAIL VARCHAR(50))")) {
            statement.executeUpdate();

            //
            Statement statement1 = connection.createStatement();
            ResultSet resultSet = statement1.executeQuery("SHOW TABLES");
            while (resultSet.next()){
                System.out.println(resultSet.getString(1));
            }
            //

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @After
    public void tearDown() {
        clientDao = null;
    }

    @Test
    public void save() {
        //GIVEN
//        Client client = new Client("name", "surname", 0, "phone", "email");
        //WHEN
//        clientDao.save(client);
        //THEN

        //
//        try (Connection connection = DBConnectionInMemory.getConnection();
////             PreparedStatement statement = connection.prepareStatement("SELECT * FROM CLIENTS"))
//                PreparedStatement statement = connection.prepareStatement("SHOW TABLES"))
//        {
//            try (ResultSet resultSet = statement.executeQuery()) {
//                while (resultSet.next()){
//                    System.out.println(resultSet.getString(1));
////                    System.out.println(resultSet.getString(2));
////                    System.out.println(resultSet.getString(3));
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        //

    }

    @Test
    public void getAll() {
    }

    @Test
    public void findByID() {
    }

    @Test
    public void findByPhone() {
    }

    @Test
    public void isContainsKey() {
    }

    @Test
    public void remove() {
    }

    @Test
    public void modify() {
    }
}