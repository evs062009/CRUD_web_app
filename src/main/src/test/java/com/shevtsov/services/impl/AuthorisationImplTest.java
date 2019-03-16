package com.shevtsov.services.impl;

import com.shevtsov.dao.ClientDao;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.times;

@RunWith(MockitoJUnitRunner.class)
public class AuthorisationImplTest {
    private AuthorisationImpl authorisation;
    @Mock
    private ClientDao clientDao;

    @Before
    public void init() {
        authorisation = new AuthorisationImpl(clientDao);
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