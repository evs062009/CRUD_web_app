package com.shevtsov.services;

public interface ClientService {

    void createClient(String name, String surname, String phone);

    void modifyClient(long id, String name, String surname, String phone);

    void removeClient(long id);

    void listAllClients();
}
