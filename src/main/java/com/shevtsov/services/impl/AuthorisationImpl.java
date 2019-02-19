package com.shevtsov.services.impl;

import com.shevtsov.dao.ClientDao;
import com.shevtsov.dao.impl.ClientDaoImpl;
import com.shevtsov.services.Authorisation;

public class AuthorisationImpl implements Authorisation {
    private final ClientDao clientDao = ClientDaoImpl.getInstance();
    private long currentUserID = -1;
    private static AuthorisationImpl instance;

    private AuthorisationImpl() {
    }

    public static AuthorisationImpl getInstance() {
        if (instance == null) {
            instance = new AuthorisationImpl();
        }
        return instance;
    }

    long getCurrentUserID() {
        return currentUserID;
    }

    void setCurrentUserID(long currentUserID) {
        this.currentUserID = currentUserID;
    }

    @Override
    public boolean authorizeClient(String phone) {
        currentUserID = clientDao.findByPhone(phone);
        return (currentUserID != -1);
    }

    @Override
    public boolean authorizeAdmin(String password) {
        final String adminPassword = "admin";
        currentUserID = -1;
        return password.equals(adminPassword);
    }
}