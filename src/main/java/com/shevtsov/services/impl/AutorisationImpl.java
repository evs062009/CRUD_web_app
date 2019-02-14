package com.shevtsov.services.impl;

import com.shevtsov.dao.ClientDao;
import com.shevtsov.dao.impl.ClientDaoImpl;
import com.shevtsov.domain.Client;
import com.shevtsov.services.Authorisation;

public class AutorisationImpl implements Authorisation {
    private final ClientDao clientDao = new ClientDaoImpl();

    @Override
    public Client authorizeClient(String phone) {
        Client currentClient = clientDao.findClientByPhone(phone);
        if (currentClient != null){
            System.out.println("There is no client with such phone number!!!");
            return null;
        } else {
            System.out.println("Welcome " + ((currentClient.getName() != null)?(currentClient.getName()):("")));
            return currentClient;
        }
    }

    @Override
    public boolean authorizeAdmin(String password) {

        //only for test example
        return true;
    }
}