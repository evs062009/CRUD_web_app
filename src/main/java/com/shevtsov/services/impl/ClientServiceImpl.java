package com.shevtsov.services.impl;

import com.shevtsov.dao.ClientDao;
import com.shevtsov.dao.impl.ClientDaoImpl;
import com.shevtsov.domain.Client;
import com.shevtsov.services.ClientService;

public class ClientServiceImpl implements ClientService {
    private ClientDao clientDao = new ClientDaoImpl();

    @Override
    public boolean createClient(String name, String surname, String phone) {
        Client client = new Client(name, surname, phone);
        return clientDao.saveClient(client);
    }

    @Override
    public boolean modifyClient(long id, String name, String surname, String phone) {
        return clientDao.modifyClient(id, name, surname, phone);
    }

    @Override
    public boolean removeClient(long id) {
        return clientDao.removeClient(id);
    }

    @Override
    public void listAllClients() {
        clientDao.listAllClients();
        System.out.println("Received collection from DAO");
        System.out.println("Processed");
        System.out.println("Transmitted to UI");
    }

    @Override
    public boolean modifyUserInformation(String name, String surname, String phone) {
        System.out.println("Defined current client id");
        long currentClientID = 0;
        return modifyClient(currentClientID, name, surname, phone);
    }
}
