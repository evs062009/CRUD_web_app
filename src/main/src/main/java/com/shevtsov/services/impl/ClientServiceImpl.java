package com.shevtsov.services.impl;

import com.shevtsov.dao.ClientDao;
import com.shevtsov.domain.Client;
import com.shevtsov.exceptions.BusinessException;
import com.shevtsov.exceptions.ObjectNotFoundExeption;
import com.shevtsov.services.ClientService;
import com.shevtsov.validators.ValidationService;

import java.util.Collections;
import java.util.List;

public class ClientServiceImpl implements ClientService {
    private AuthorisationImpl authorisation;
    private ClientDao clientDao;
    private final ValidationService validationService;

    public ClientServiceImpl(ValidationService validationService, ClientDao clientDao, AuthorisationImpl
            authorisation) {
        this.validationService = validationService;
        this.clientDao = clientDao;
        this.authorisation = authorisation;
    }

    @Override
    public boolean create(String name, String surname, int age, String phone, String email) {
        Client client = getClientForCreating(name, surname, age, phone, email);
        if (client != null) {
            authorisation.setCurrentUserID(clientDao.save(client));
            return true;
        }
        System.out.println("log: Creating has not been done.");
        return false;
    }

    @Override
    public boolean modify(long id, String newName, String newSurname, int newAge, String newPhone,
                          String newEmail) {
        if (clientDao.isContainsKey(id)) {
            Client client = getClientForModifying(newName, newSurname, newAge, newPhone, newEmail);
            if (client != null) {
                client.setId(id);
                return clientDao.modify(client);
            }
        }
        System.out.println("log: Modify has not been done!!!");
        return false;
    }

    @Override
    public Client getClient(long id) {
        return clientDao.findByID(id).orElseThrow(() -> new ObjectNotFoundExeption(id));
    }

    private Client getClientForModifying(String name, String surname, int age, String phone, String email) {
        try {
            validationService.validateAge(age);
            validationService.validatePhoneFormat(phone);
            validationService.validateEmail(email);
            return new Client(name, surname, age, phone, email);
        } catch (BusinessException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Client getClientForCreating(String name, String surname, int age, String phone, String email) {
        Client client = getClientForModifying(name, surname, age, phone, email);
        if (client != null) {
            try {
                validationService.validatePhoneUniq(phone);
                client.setId(-1L);
                return client;
            } catch (BusinessException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public boolean remove(long id) {
        if (clientDao.isContainsKey(id)) {
            clientDao.remove(id);
            return true;
        }
        System.out.println("log: Removing has not been done (there is no such client)");
        return false;
    }

    @Override
    public List<Client> getAll() {
        List<Client> clients = clientDao.getAll();
        if (clients != null) {
            Collections.sort(clients);
        }
        return clients;
    }
}