package com.shevtsov.services.impl;

import com.shevtsov.dao.ClientDao;
import com.shevtsov.dao.impl.ClientDaoImpl;
import com.shevtsov.domain.Client;
import com.shevtsov.exceptions.BusinessException;
import com.shevtsov.services.ClientService;
import com.shevtsov.validators.ValidationService;

import java.util.List;

public class ClientServiceImpl implements ClientService {
    private final AuthorisationImpl authorisation = AuthorisationImpl.getInstance();
    private final ClientDao clientDao = ClientDaoImpl.getInstance();
    private final ValidationService validationService;

    public ClientServiceImpl(ValidationService validationService) {
        this.validationService = validationService;
    }

    @Override
    public boolean create(String name, String surname, int age, String phone, String email) {
        Client client = getClientForCreating(name, surname, age, phone, email);
        if (client != null) {
            authorisation.setCurrentUserID(clientDao.save(client));
            return true;
        }
        return false;
    }

    @Override
    public boolean modify(long id, String newName, String newSurname, int newAge, String newPhone,
                          String newEmail) {
        if (clientDao.isContainsKey(id)) {
            Client client = getClientForModifying(newName, newSurname, newAge, newPhone, newEmail);
            if (client != null) {
                client.setId(id);
                clientDao.save(id, client);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean modifyUserInformation(String newName, String newSurname, int newAge, String newPhone,
                                         String newEmail) {
        return modify(authorisation.getCurrentUserID(), newName, newSurname, newAge, newPhone, newEmail);
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
        } else {
            return false;
        }
    }

    @Override
    public List<Client> getAll() {
        return clientDao.gatAll();
    }
}