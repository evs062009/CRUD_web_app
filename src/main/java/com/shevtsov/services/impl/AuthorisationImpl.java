package com.shevtsov.services.impl;

import com.shevtsov.dao.ClientDao;
import com.shevtsov.dao.impl.ClientDaoImpl;
import com.shevtsov.domain.Client;
import com.shevtsov.services.Authorisation;

public class AuthorisationImpl implements Authorisation {
    private final ClientDao clientDao = ClientDaoImpl.getInstance();
    private Client currentClient;

    @Override
    public boolean authorizeClient(String phone) {
        currentClient = clientDao.findByPhone(phone);
        return currentClient != null;
    }

    @Override
    public boolean authorizeAdmin(String password) {
        final String adminPassword = "admin";
        return password.equals(adminPassword);
    }
}