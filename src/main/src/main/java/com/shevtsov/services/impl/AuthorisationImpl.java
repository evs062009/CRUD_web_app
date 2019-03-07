package com.shevtsov.services.impl;

import com.shevtsov.dao.ClientDao;
import com.shevtsov.services.Authorisation;

public class AuthorisationImpl implements Authorisation {
    private ClientDao clientDao;
    private long currentUserID = -1;

    public AuthorisationImpl(ClientDao clientDao) {
        this.clientDao = clientDao;
    }

    public long getCurrentUserID() {
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