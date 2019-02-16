package com.shevtsov.services;

import com.shevtsov.domain.Client;

import java.util.List;

public interface ClientService {

    /**
     * Creates and transmits a Client-object to DAO for saving.
     * @param name the name of new client.
     * @param surname the surname of new client.
     * @param phone the phone number of new client.
     * @return true if new client was saved successfully, or false otherwise.
     */
    void createClient(String name, String surname, String phone);

    void createClient(String name, String surname, int age, String phone, String email);

    /**
     * Gets data about a client, which is modified, from UI and transmits it to DAO for modifying.
     * Confirmation request and other logic will be added later.
     * @param id id of client, which is modified.
     * @param newName new name of client, which is modified.
     * @param newSurname new surname of client, which is modified.
     * @param newPhone new phone number of client, which is modified.
     * @return true if client was modified successfully, or false otherwise.
     */
    boolean modifyClient(long id, String newName, String newSurname, String newPhone);

    /**
     * Gets data about a client, which is removed, from UI and transmits it to DAO for deleting.
     * Confirmation request and other logic will be added later.
     * @param id id of client, which is removed.
     * @return true if client was removed successfully, or false otherwise.
     */
    boolean removeClient(long id);

    /**
     * Receives collection of Client-objects from DAO and transmits it to UI for listing.
     * Business-logic will be added later.
     */
    List<Client> getAllClients();

    /**
     * Gets data about a current (sign in) client, from UI and transmits it to DAO for modifying.
     * Confirmation request and other logic will be added later.
     * @param newName new name of current client.
     * @param newSurname new surname of current client.
     * @param newPhone new phone number of current client.
     * @return true if client was modified successfully, or false otherwise.
     */
    boolean modifyUserInformation(String newName, String newSurname, String newPhone);
}