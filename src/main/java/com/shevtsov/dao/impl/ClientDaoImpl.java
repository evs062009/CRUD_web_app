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
    public boolean modifyClient(long client, String name, String surname, String phone) {
        System.out.println("Modifying... Please wait");
        return true;
    }

    @Override
    public boolean removeClient(long client) {
        System.out.println("Deleting... Please wait.");
        return true;
    }

    @Override
    public void listAllClients() {
        System.out.println("List of clients:");
        System.out.println("...");
        System.out.println("...");
    }

    @Override
    public Client findClientByPhone(String phone) {

        //only for test example
        return new Client("John", "Doe", "111");
    }
}