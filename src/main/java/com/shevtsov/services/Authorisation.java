package com.shevtsov.services;

public interface Authorisation {

    /**
     * Authorizes the client by entered phone number.
     * @param phone The phone number of client, which is searched.
     * @return true if client was searched in storage, or false otherwise.
     */
    boolean authorizeClient(String phone);

    /**
     * Checks the input for matching with admin password.
     * @param input users input, which is checked
     * @return true if the input matches, or false otherwise.
     */
    boolean authorizeAdmin(String input);
}
