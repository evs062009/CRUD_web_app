package com.shevtsov.services.impl;

import com.shevtsov.dao.ClientDao;
import com.shevtsov.dao.impl.ClientDaoImpl;
import com.shevtsov.domain.Client;
import com.shevtsov.services.ClientService;

public class ClientServiceImpl implements ClientService {
    private ClientDao clientDao = new ClientDaoImpl();

    @Override
    public void createClient(String name, String surname, String phone) {
        Client client = new Client(name, surname, phone);
        boolean result = clientDao.saveClient(client);
        if (result) {
            System.out.println("Client saved: " + client);
        }
    }

    @Override
    public void modifyClient(long id, String name, String surname, String phone) {
        Client client = clientDao.searchClientByID(id);
        if (client != null) {
            if (clientDao.modifyClient(client, name, surname, phone)) {
                System.out.println("Client modified: " + client);
            }
        } else {
            System.out.println("There is no such client.");
        }
    }

    @Override
    public void removeClient(long id) {
        Client client = clientDao.searchClientByID(id);
        if (client != null) {
            if (clientDao.removeClient(client)){
                System.out.println("Client removed: " + client);
            }
        } else {
            System.out.println("There is no such client.");
        }
    }

    @Override
    public void listAllClients() {
        clientDao.listAllClients();
    }
}
