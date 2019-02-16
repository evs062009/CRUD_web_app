package com.shevtsov.dao;

import com.shevtsov.domain.Client;

import java.util.List;

public interface ClientDao {

    /**
     * Saves Client-object to storage.
     * @param client the Client-object, which is saved.
     * @return true if new client was saved successfully, or false otherwise.
     */
    void save(Client client);

    /**
     * Removes client in storage.
     * @param id of the client, which is removed.
     * @return true if client was removed successfully, or false otherwise.
     */
    void remove(long id);

    /**
     * Creates collection of Client-objects from storage and transmits it to Source for processing.
     */
    List<Client> gatAll();

    /**
     * Finds client in storage by phone number.
     * @param phone The phone number of client, which is searched.
     * @return Client-object if it`s found or null otherwise.
     */
    Client findByPhone(String phone);

    boolean isContainsKey(long id);

    /**
     * Modifies client data in storage.
     * @param id of client, which is modified.
     * @param client new data of, which will be assigned to the client.
     * @return true if client was modified successfully, or false otherwise.
     */
    void modify(long id, Client client);
}
