package com.shevtsov.services;

public interface Authorisation {

    boolean authorizeClient(String phone);

    boolean authorizeAdmin(String password);
}
