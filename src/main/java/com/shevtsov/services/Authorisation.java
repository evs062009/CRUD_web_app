package com.shevtsov.services;

import com.shevtsov.domain.Client;

public interface Authorisation {

    Client authorizeClient();

    boolean authorizeAdmin();
}
