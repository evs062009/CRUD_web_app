package com.shevtsov.services;

import com.shevtsov.domain.Client;

import java.util.List;

public interface ClientService {

    /**
     * Creates and transmits a Client-object to DAO for saving.
     *
     * @param name    of new client.
     * @param surname of new client.
     * @param age     of new client.
     * @param phone   the phone number of new client.
     * @param email   of new client.
     * @return true if new client was saved successfully, or false otherwise.
     */
    boolean create(String name, String surname, int age, String phone, String email);

    /**
     * Gets data about a client, which is modified, from UI and transmits it to DAO for modifying.
     * Confirmation request and other logic will be added later.
     *
     * @param id         id of client, which is modified.
     * @param newName    new name of client, which is modified.
     * @param age
     * @param newSurname new surname of client, which is modified.
     * @param newPhone   new phone number of client, which is modified.
     * @param email
     * @return true if client was modified successfully, or false otherwise.
     */
    boolean modify(long id, String newName, String age, int newSurname, String newPhone, String email);

    /**
     * Gets data about a client, which is removed, from UI and transmits it to DAO for deleting.
     * Confirmation request and other logic will be added later.
     *
     * @param id id of client, which is removed.
     * @return true if client was removed successfully, or false otherwise.
     */
    boolean remove(long id);

    /**
     * Receives collection of Client-objects from DAO and transmits it to UI for listing.
     * Business-logic will be added later.
     */
    List<Client> getAll();

    /**
     * Gets data about a current (sign in) client, from UI and transmits it to DAO for modifying.
     * Confirmation request and other logic will be added later.
     *
     * @param newName    new name of current client.
     * @param newSurname new surname of current client.
     * @param newPhone   new phone number of current client.
     * @return true if client was modified successfully, or false otherwise.
     */
    boolean modifyUserInformation(String newName, String newSurname, int newAge, String newPhone,
                                  String newEmail);
}