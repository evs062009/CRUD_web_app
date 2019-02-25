package com.shevtsov.dao;

import com.shevtsov.domain.Client;

import java.util.List;

public interface ClientDao {

    /**
     * Saves the Client-object to storage.
     *
     * @param client the Client-object, which is saved.
     * @return the id of client, which was saved.
     */
    long save(Client client);

    /**
     * Searches for client by id in the storage.
     *
     * @param id the id of the client, which is searched.
     * @return Client-object, if such was found, or null otherwise.
     */
    Client findByID(long id);

    /**
     * Removes client in storage.
     *
     * @param id the id of the client, which is removed.
     */
    void remove(long id);

    /**
     * Creates and returns collection of Client-objects from storage.
     *
     * @return collection of Client-objects from storage.
     */
    List<Client> gatAll();

    /**
     * Searches client in storage by phone number.
     *
     * @param phone The phone number of client, which is searched.
     * @return client`s id if such client was found or -1 otherwise.
     */
    long findByPhone(String phone);

    /**
     * Checks if there is a client with such id in the storage.
     *
     * @param id the id of the client, which is searched.
     * @return true if there is a client with such id in the storage or false otherwise.
     */
    boolean isContainsKey(long id);
}