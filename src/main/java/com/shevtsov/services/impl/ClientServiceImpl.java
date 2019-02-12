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
        if (result){
            System.out.println("Client saved: " + client);
        }
    }

    @Override
    public void deleteClient() {

    }
}
