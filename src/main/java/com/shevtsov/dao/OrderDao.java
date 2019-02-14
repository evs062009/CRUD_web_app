package com.shevtsov.dao;

public interface OrderDao {

    /**
     * Creates collection of Order-objects from DB and transmits it to Source for processing.
     */
    void createOrdersList();

    /**
     * Finds order in DB by id.
     * @param id id of order, which is searched.
     * @return for now true if order was searched, or false otherwise.
     * Plan to change the return value to Order-object later.
     */
    boolean findOrderByID(long id);

    /**
     * Saves Order-object in DB.
     * @return true if order was saved successfully, or false otherwise.
     */
    boolean saveOrder();
}