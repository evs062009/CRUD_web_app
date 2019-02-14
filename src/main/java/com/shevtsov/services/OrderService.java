package com.shevtsov.services;

public interface OrderService {

    void listAllOrder();

    void showOrder(long id);

    void addProductToBasket(long productID);

    void listBasket();

    void removeProductFromBasket(long productID);

    void createOrder();
}
