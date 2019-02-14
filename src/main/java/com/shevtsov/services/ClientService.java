package com.shevtsov.services;

public interface ClientService {

    boolean createClient(String name, String surname, String phone);

    boolean modifyClient(long id, String name, String surname, String phone);

    boolean removeClient(long id);

    void listAllClients();

    boolean modifyUserInformation(String name, String surname, String phone);
}
