package com.shevtsov.services;

public interface ClientService {
    /**
     * !!! написать к каждому методу описание
     **/
    void createClient(String name, String surname, String phone);


    void modifyClient(long clientID, String name, String surname, String phone);

    void removeClient(long clientID);

    void listAllClients();

    //подумать, что еще ()
}
