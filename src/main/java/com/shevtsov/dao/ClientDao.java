package com.shevtsov.dao;

import com.shevtsov.domain.Client;

public interface ClientDao {

    boolean saveClient(Client client);

    boolean modifyClient(long client, String name, String surname, String phone);

    boolean removeClient(long client);

    void listAllClients();

    Client findClientByPhone(String phone);
}
