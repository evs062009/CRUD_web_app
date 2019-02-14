package com.shevtsov.services.impl;

import com.shevtsov.dao.ClientDao;
import com.shevtsov.dao.impl.ClientDaoImpl;
import com.shevtsov.domain.Client;
import com.shevtsov.services.ClientService;

public class ClientServiceImpl implements ClientService {
    private ClientDao clientDao = new ClientDaoImpl();

    @Override
    public Client createClient(String name, String surname, String phone) {
        Client client = new Client(name, surname, phone);
        if (clientDao.saveClient(client)) {
            System.out.println("Client saved: " + client);
        }
        return client;
    }

    @Override
    public void modifyClient(long id, String name, String surname, String phone) {
        if (clientDao.modifyClient(id, name, surname, phone)) {
            System.out.println("Client modified");
        } else {
            System.out.println("Client have NOT been modified!!!");
        }
    }

    @Override
    public void removeClient(long id) {
        if (clientDao.removeClient(id)) {
            System.out.println("Client removed");
        } else {
            System.out.println("Client have NOT been removed!!!");
        }
    }

    @Override
    public void listAllClients() {
        clientDao.listAllClients();
    }

    @Override
    public void modifyUserInformation(Client currentClient, String name, String surname, String phone) {
        long clientID = currentClient.getId();
        modifyClient(clientID, name, surname, phone);
    }
}
