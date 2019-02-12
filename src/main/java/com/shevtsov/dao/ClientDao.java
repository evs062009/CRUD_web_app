package com.shevtsov.dao;

import com.shevtsov.domain.Client;

public interface ClientDao {

    boolean saveClient(Client client);

    Client searchClientByID(long id);

    boolean modifyClient(Client client, String name, String surname, String phone);

    boolean removeClient(Client client);

    void listAllClients();
}
