package com.shevtsov.services.impl;

import com.shevtsov.dao.ClientDao;
import com.shevtsov.domain.Client;
import com.shevtsov.services.ClientService;
import com.shevtsov.validators.ValidationService;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mockito;

import static org.mockito.Mockito.mock;

@RunWith(JUnit4.class)
public class ClientServiceImplTest {

    private ValidationService validationService = mock(ValidationService.class);
    private ClientDao clientDao = mock(ClientDao.class);
    private AuthorisationImpl authorisation = mock(AuthorisationImpl.class);

    private ClientService clientService;

    @Before
    public void init() {
        clientService = new ClientServiceImpl(validationService, clientDao, authorisation);
    }

    @Test
    @Ignore
    public void create() {
        //GIVEN
        String name = "test";
        String surname = "test";
        int age = 10;
        String phone = "0501111111";
        String email = "111@test.com";

        //WHEN
        boolean result = clientService.create(name, surname, age, phone, email);

        //THEN
    }

    @Test
    public void getClient(){
        //GIVEN
        long id = 1;
        String name = "test";
        String surname = "test";
        int age = 10;
        String phone = "0501111111";
        String email = "111@test.com";
        Client expectedClient = new Client(id, name, surname, age, phone, email);

        //ClientDao behaviour model
        Mockito.when(clientDao.findByID(id)).thenReturn(java.util.Optional.of(expectedClient));
//        Mockito.when(clientDao.findByID(0)).thenReturn(java.util.Optional.of(expectedClient));

        //WHEN
        Client actualClient = clientService.getClient(id);

        //THEN
        //check if such method with such params is invoked
        //Mokito.times(2) - check how many times we have to invoke this method
//        Mockito.verify(clientDao, Mokito.times(2)).getClient(0);
//        Mockito.verifyNoMoreInteractions(clientDao);    //что делает ???
        Assert.assertEquals(expectedClient, actualClient);
    }

    @After
    public void tearDown() {
        clientService = null;
    }
}
