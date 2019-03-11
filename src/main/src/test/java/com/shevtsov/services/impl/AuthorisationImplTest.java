package com.shevtsov.services.impl;

import com.shevtsov.dao.ClientDao;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.mockito.Mockito.times;

@RunWith(JUnit4.class)
public class AuthorisationImplTest {
    private AuthorisationImpl authorisation;
    @Mock
    private ClientDao clientDao;
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Before
    public void init() {
        authorisation = new AuthorisationImpl(clientDao);
    }

    @After
    public void tearDown() {
        authorisation = null;
    }

    @Test
    public void authorizeClient() {
        //GIVEN
        String phone = "0501111111";
        Mockito.when(clientDao.findByPhone(phone)).thenReturn(1L);
        //WHEN
        boolean actual = authorisation.authorizeClient(phone);
        //THEN
        Mockito.verify(clientDao, times(1)).findByPhone(phone);
        Assert.assertTrue(actual);
    }

    @Test
    public void authorizeClientWithInvalidPhone() {
        //GIVEN
        String phone = "111";
        Mockito.when(clientDao.findByPhone(phone)).thenReturn(-1L);
        //WHEN
        boolean actual = authorisation.authorizeClient(phone);
        //THEN
        Mockito.verify(clientDao, times(1)).findByPhone(phone);
        Assert.assertFalse(actual);
    }

    @Test
    public void authorizeAdminWithInvalidPassword() {
        //WHEN
        boolean actual = authorisation.authorizeAdmin("hjhcjnb");
        //THEN
        Assert.assertFalse(actual);
    }
}