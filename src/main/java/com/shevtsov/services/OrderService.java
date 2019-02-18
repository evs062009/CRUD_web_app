package com.shevtsov.services;

import com.shevtsov.domain.Order;
import com.shevtsov.domain.Product;

import java.util.List;

public interface OrderService {

    List<Product> getBasket();

    /**
     * Receives collection of Order-objects from DAO and transmits it to UI for listing.
     * Business-logic will be added later.
     */
    List<Order> getAll();

    /**
     * Transmits an order id to DAO for searching.
     * Business-logic will be added later.
     *
     * @param id id of order, which is searched.
     * @return for now true if order was searched, or false otherwise.
     * Plan to change the return value to Order-object later.
     */
    boolean findByID(long id);

    /**
     * Plan to create Order-object and transmit it to DAO for saving later.
     * Plan to change params to Client current client and collection of Product-objects (basket).
     *
     * @return true if order was saved successfully, or false otherwise.
     */
    boolean create();

    boolean remove(long orderID);

    List<Order> getUserOrders();

    boolean addProductToBasket(long productID);

    boolean removeProductFromBasket(long productID);
}