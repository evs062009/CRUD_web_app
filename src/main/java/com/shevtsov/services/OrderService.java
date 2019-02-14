package com.shevtsov.services;

import com.shevtsov.domain.Client;

public interface OrderService {

    void listAllOrder();

    void showOrder(long id);

    void addProductToBasket(long productID);

    void removeProductFromBasket(long productID);

    void listBasket();

    void createOrder(Client currentClient);
}
