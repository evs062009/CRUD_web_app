package com.shevtsov.dao;

import com.shevtsov.domain.Client;

public interface ClientDao {

    /**
     * Saves Client-object to DB.
     * @param client the Client-object, which is saved.
     * @return true if new client was saved successfully, or false otherwise.
     */
    boolean saveClient(Client client);

    /**
     * Modifies client data in DB.
     * @param id id of client, which is modified.
     * @param newName id of client, which is modified.
     * @param newSurname new name of client, which is modified.
     * @param newPhone new surname of client, which is modified.
     * @return true if client was modified successfully, or false otherwise.
     */
    boolean modifyClient(long id, String newName, String newSurname, String newPhone);

    /**
     * Removes client in DB.
     * @param client the Client-object, which is removed.
     * @return true if client was removed successfully, or false otherwise.
     */
    boolean removeClient(long client);

    /**
     * Creates collection of Client-objects from DB and transmits it to Source for processing.
     */
    void createClientsList();

    /**
     * Finds order in DB by phone number.
     * @param phone The phone number of client, which is searched.
     * @return for now true if client was searched, or false otherwise.
     * Plan to change the return value to Client-object later.
     */
    boolean findClientByPhone(String phone);
}
