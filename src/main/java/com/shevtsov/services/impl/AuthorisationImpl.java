package com.shevtsov.services.impl;

import com.shevtsov.dao.ClientDao;
import com.shevtsov.dao.impl.ClientDaoImpl;
import com.shevtsov.services.Authorisation;

public class AuthorisationImpl implements Authorisation {
    private final ClientDao clientDao = new ClientDaoImpl();

    @Override
    public boolean authorizeClient(String phone) {
        return clientDao.findClientByPhone(phone);
    }

    @Override
    public boolean authorizeAdmin(String password) {
        System.out.println("Checking...");
        return true;
    }
}