package com.shevtsov.dao.impl;

import com.shevtsov.dao.ClientDao;
import com.shevtsov.domain.Client;

public class ClientDaoImpl implements ClientDao {

    @Override
    public boolean saveClient(Client client) {
        System.out.println("Saving... Please wait.");
        return true;
    }

    @Override
    public boolean modifyClient(long id, String newName, String newSurname, String newPhone) {
        System.out.println("Modifying... Please wait");
        return true;
    }

    @Override
    public boolean removeClient(long client) {
        System.out.println("Deleting... Please wait.");
        return true;
    }

    @Override
    public void createClientsList() {
        System.out.println("Receiving data from DB...");
        System.out.println("Creating collection...");
        System.out.println("Transmitting to Service");
    }

    @Override
    public boolean findClientByPhone(String phone) {
        System.out.println("Searching... Please wait.");
        System.out.println("Client found");
        return true;
    }
}