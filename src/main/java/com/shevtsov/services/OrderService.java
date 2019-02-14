package com.shevtsov.services;

import java.util.ArrayList;

public interface OrderService {

    void listAllOrder();

    boolean findOrderByID(long id);

    void addProductToBasket(long productID);

    void removeProductFromBasket(long productID);

    ArrayList getBasket();

    boolean createOrder();
}
