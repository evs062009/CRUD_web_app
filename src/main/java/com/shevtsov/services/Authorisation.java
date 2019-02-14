package com.shevtsov.services;

public interface Authorisation {

    /**
     * Authorizes the client by entered phone number.
     * @param phone The phone number of client, which is searched.
     * @return for now true if client was searched, or false otherwise.
     * Plan to change the return value to Client-object later.
     */
    boolean authorizeClient(String phone);

    /**
     * Checks the user for matching with the admin.
     * @return true if the user matches, or false otherwise.
     */
    boolean authorizeAdmin();
}
