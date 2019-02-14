package com.shevtsov.services;

import com.shevtsov.domain.Client;

public interface ClientService {

    Client createClient(String name, String surname, String phone);

    void modifyClient(long id, String name, String surname, String phone);

    void removeClient(long id);

    void listAllClients();

    void modifyUserInformation(Client currentClient, String name, String surname, String phone);
}
