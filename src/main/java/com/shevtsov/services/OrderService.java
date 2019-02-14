package com.shevtsov.services;

public interface OrderService {

    /**
     * Receives collection of Order-objects from DAO and transmits it to UI for listing.
     * Business-logic will be added later.
     */
    void listAllOrder();

    /**
     * Transmits an order id to DAO for searching.
     * Business-logic will be added later.
     * @param id id of order, which is searched.
     * @return for now true if order was searched, or false otherwise.
     * Plan to change the return value to Order-object later.
     */
    boolean findOrderByID(long id);

    /**
     * Adds the product, whose id was entered, to the collection.
     * @param productID id of product, which is added.
     */
    void addProductToBasket(long productID);

    /**
     * Removes the product, whose id was entered, from the collection.
     * @param productID id of product, which is removed.
     */
    void removeProductFromBasket(long productID);

    /**
     * Plan to create Order-object and transmit it to DAO for saving later.
     * Plan to change params to Client current client and collection of Product-objects (basket).
     * @return true if order was saved successfully, or false otherwise.
     */
    boolean createOrder();
}