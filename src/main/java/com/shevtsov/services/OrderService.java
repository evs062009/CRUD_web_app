package com.shevtsov.services;

import com.shevtsov.domain.Order;

import java.util.List;

public interface OrderService {

    /**
     * Plan to create Order-object and transmit it to DAO for saving later.
     * Plan to change params to Client current client and collection of Product-objects (basket).
     *
     * @return true if order was saved successfully, or false otherwise.
     */
    boolean remove(long orderID);

    List<Order> getUserOrders();

    boolean addProductToDraft(long productID);

    boolean removeProductFromDraft(long productID);

    boolean copyOrderToDraft(long orderID);

    void createDraft();

    List getDraftProducts();

    boolean save();

    /**
     * Receives collection of all Order-objects from DAO and transmits it to UI for listing.
     */
    List<Order> getAll();
}