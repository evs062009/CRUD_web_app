package com.shevtsov.services;

public interface Authorisation {

    /**
     * Authorizes a client by entered phone number. If the client is found, saves his id.
     *
     * @param phone The phone number of client, which is searched.
     * @return true if client was found in storage, or false otherwise.
     */
    boolean authorizeClient(String phone);

    /**
     * Checks entered password for matching with admin password.
     *
     * @param password the password, which is checked
     * @return true if the password matches, or false otherwise.
     */
    boolean authorizeAdmin(String password);
}
