package com.shevtsov.services.impl;

import com.shevtsov.dao.ClientDao;
import com.shevtsov.domain.Client;
import com.shevtsov.exceptions.BusinessException;
import com.shevtsov.exceptions.ObjectNotFoundExeption;
import com.shevtsov.services.ClientService;
import com.shevtsov.validators.ValidationService;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(JUnit4.class)
public class ClientServiceImplTest {

    @Mock
    private ValidationService validationService;
    @Mock
    private ClientDao clientDao;
    @Mock
    private AuthorisationImpl authorisation;
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    private ClientService clientService;
    private String name;
    private String surname;
    private int age;
    private String phone;
    private String email;
    private long id;

    @Before
    public void init() {
        clientService = new ClientServiceImpl(validationService, clientDao, authorisation);
        id = 1;
        name = "name";
        surname = "surname";
        age = 10;
        phone = "0501111111";
        email = "111@test.com";
    }

    @Test
    public void createWithValidParameters() {
        //WHEN
        boolean actual = clientService.create(name, surname, age, phone, email);
        //THEN
        Mockito.verify(clientDao, times(1)).save(any(Client.class));
        Assert.assertTrue(actual);
    }

    @Test
    public void createWithInvalidAge() throws BusinessException {
        //GIVEN
        age = 201;
        Mockito.doThrow(BusinessException.class).when(validationService).validateAge(age);
        //
        whenThenForCreateWithInvalidParameters();
    }

    @Test
    public void createWithInvalidPhone() throws BusinessException {
        //GIVEN
        phone = "111";
        Mockito.doThrow(BusinessException.class).when(validationService).validatePhoneFormat(phone);
        //
        whenThenForCreateWithInvalidParameters();
    }

    @Test
    public void createWithNotUniquePhone() throws BusinessException {
        //GIVEN
        phone = "111";
        Mockito.doThrow(BusinessException.class).when(validationService).validatePhoneUniq(phone);
        //
        whenThenForCreateWithInvalidParameters();
    }

    @Test
    public void createWithInvalidEmail() throws BusinessException {
        //GIVEN
        email = "111";
        Mockito.doThrow(BusinessException.class).when(validationService).validateEmail(email);
        //
        whenThenForCreateWithInvalidParameters();
    }

    @Test
    public void modifyWithValidParameters() {
        //GIVEN
        Mockito.when(clientDao.isContainsKey(id)).thenReturn(true);
        Mockito.when(clientDao.modify(any(Client.class))).thenReturn(true);
        //WHEN
        boolean actual = clientService.modify(id, name, surname, age, phone, email);
        //THEN
        Mockito.verify(clientDao, times(1)).isContainsKey(id);
        Mockito.verify(clientDao, times(1)).modify(any(Client.class));
        Assert.assertTrue(actual);
    }

    @Test
    public void modifyWithInvalidID() {
        //GIVEN
        id = -1;
        Mockito.when(clientDao.isContainsKey(id)).thenReturn(false);
        //
        whenThenForModifyWithInvalidParameters();
    }

    @Test
    public void modifyWithInvalidAge() throws BusinessException {
        //GIVEN
        age = 201;
        Mockito.doThrow(BusinessException.class).when(validationService).validateAge(age);
        //
        whenThenForModifyWithInvalidParameters();
    }


    @Test
    public void modifyWithInvalidPhone() throws BusinessException {
        //GIVEN
        phone = "111";
        Mockito.doThrow(BusinessException.class).when(validationService).validatePhoneFormat(phone);
        //
        whenThenForModifyWithInvalidParameters();
    }

    @Test
    public void modifyWithInvalidEmail() throws BusinessException {
        //GIVEN
        email = "111";
        Mockito.doThrow(BusinessException.class).when(validationService).validateEmail(email);
        //
        whenThenForModifyWithInvalidParameters();
    }

    @Test
    public void getClientWithValidParameters() {
        //GIVEN
        Client expected = new Client(id, name, surname, age, phone, email);
        Mockito.when(clientDao.findByID(id)).thenReturn(Optional.of(expected));
        //WHEN
        Client actual = clientService.getClient(id);
        //THEN
        Mockito.verify(clientDao, times(1)).findByID(id);
        Assert.assertEquals(expected, actual);
    }

    @Test(expected = ObjectNotFoundExeption.class)
    public void getClientWithInvalidID() {
        //GIVEN
        id = -1;
        Mockito.when(clientDao.findByID(id)).thenThrow(ObjectNotFoundExeption.class);
        //WHEN
        clientService.getClient(id);
        //THEN
        Mockito.verify(clientDao, times(1)).findByID(id);
    }

    @Test
    public void removeWithValidParameters() {
        //GIVEN
        Mockito.when(clientDao.isContainsKey(id)).thenReturn(true);
        //WHEN
        boolean actual = clientService.remove(id);
        //THEN
        Mockito.verify(clientDao, times(1)).isContainsKey(id);
        Mockito.verify(clientDao, times(1)).remove(id);
        Assert.assertTrue(actual);
    }

    @Test
    public void removeWithInvalidID() {
        //GIVEN
        id = -1;
        Mockito.when(clientDao.isContainsKey(id)).thenReturn(false);
        //WHEN
        boolean actual = clientService.remove(id);
        //THEN
        Mockito.verify(clientDao, times(1)).isContainsKey(id);
        Mockito.verifyNoMoreInteractions(clientDao);
        Assert.assertFalse(actual);
    }

    @Test
    public void getAll() {
        //GIVEN
        Mockito.when(clientDao.getAll()).thenReturn(new ArrayList<>());
        //WHEN
        List<Client> actual = clientService.getAll();
        //THEN
        Mockito.verify(clientDao, times(1)).getAll();
        Assert.assertNotNull(actual);
    }


    private void whenThenForCreateWithInvalidParameters() {
        //WHEN
        boolean actual = clientService.create(name, surname, age, phone, email);
        //THEN
        Mockito.verifyZeroInteractions(clientDao);
        Assert.assertFalse(actual);
    }

    private void whenThenForModifyWithInvalidParameters() {
        //WHEN
        boolean actual = clientService.modify(id, name, surname, age, phone, email);
        //THEN
        Mockito.verify(clientDao, times(1)).isContainsKey(id);
        Mockito.verifyNoMoreInteractions(clientDao);
        Assert.assertFalse(actual);
    }

    @After
    public void tearDown() {
        clientService = null;
    }
}