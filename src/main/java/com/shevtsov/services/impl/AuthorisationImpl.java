package com.shevtsov.services.impl;

import com.shevtsov.dao.ClientDao;
import com.shevtsov.dao.impl.ClientDaoImpl;
import com.shevtsov.services.Authorisation;

public class AuthorisationImpl implements Authorisation {
    private final ClientDao clientDao = ClientDaoImpl.getInstance();
    private long currentUserID;
    //object-singleton
    private static AuthorisationImpl authorisation = new AuthorisationImpl();

    //constructor-singleton
    private AuthorisationImpl() {
    }

    //factory method for singleton
    public static AuthorisationImpl getInstance() {
        return authorisation;
    }


    long getCurrentUserID() {
        return currentUserID;
    }

    @Override
    public boolean authorizeClient(String phone) {
        currentUserID = clientDao.findByPhone(phone);
        return currentUserID != -1;
    }

    @Override
    public boolean authorizeAdmin(String password) {
        final String adminPassword = "admin";
        return password.equals(adminPassword);
    }
}