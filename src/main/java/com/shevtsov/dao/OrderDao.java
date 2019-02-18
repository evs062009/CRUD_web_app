package com.shevtsov.dao;

import com.shevtsov.domain.Order;

import java.util.List;

public interface OrderDao {

    /**
     * Creates collection of Order-objects from storage and transmits it to Source for processing.
     */
    List<Order> getAll();

    /**
     * Finds order in storage by id.
     * @param id id of order, which is searched.
     * @return for now true if order was searched, or false otherwise.
     * Plan to change the return value to Order-object later.
     */
    boolean findByID(long id);

    /**
     * Saves Order-object in storage.
     * @param order which is saved.
     * @return true if order was saved successfully, or false otherwise.
     */
    void save(Order order);

    boolean isContainsKey(long id);

    void remove(long id);

    List<Order> getUserOrders(long currentUserID);
}