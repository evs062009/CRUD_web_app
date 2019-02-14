package com.shevtsov.services;

public interface OrderService {

    void listAllOrder();

    boolean findOrderByID(long id);

    void addProductToBasket(long productID);

    void removeProductFromBasket(long productID);

    boolean createOrder();
}
