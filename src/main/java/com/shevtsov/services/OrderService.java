package com.shevtsov.services;

import com.shevtsov.domain.Order;

import java.util.List;

public interface OrderService {

    /**
     * Receives collection of Order-objects from DAO and transmits it to UI for listing.
     * Business-logic will be added later.
     */
    List<Order> getAll();

    /**
     * Plan to create Order-object and transmit it to DAO for saving later.
     * Plan to change params to Client current client and collection of Product-objects (basket).
     *
     * @return true if order was saved successfully, or false otherwise.
     */
    boolean remove(long orderID);

    List<Order> getUserOrders();

    boolean addProductToOrderDraft(long productID);

    boolean removeProductFromOrderDraft(long productID);

    boolean copyOrderToDraft(long orderID);

    void createOrderDraft();

    List getOrderDraftProducts();

    boolean save();
}