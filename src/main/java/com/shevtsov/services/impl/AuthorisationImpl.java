package com.shevtsov.services.impl;

import com.shevtsov.dao.ClientDao;
import com.shevtsov.dao.impl.ClientDaoImpl;
import com.shevtsov.services.Authorisation;

public class AuthorisationImpl implements Authorisation {
    private final ClientDao clientDao = ClientDaoImpl.getInstance();

    @Override
    public boolean authorizeClient(String phone) {
        System.out.println("Checking...");
        return clientDao.findClientByPhone(phone);
    }

    @Override
    public boolean authorizeAdmin() {
        System.out.println("Checking...");
        return true;
    }
}