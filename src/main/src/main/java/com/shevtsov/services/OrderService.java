package com.shevtsov.services;

import com.shevtsov.domain.Order;

import java.util.List;

public interface OrderService {

    /**
     * Transmits to DAO the command to remove exist Order-object from storage.
     * The presence of the object is checked.
     * In case when the client (not admin) deletes order,
     * user id is checked for matching the id of the order owner.
     *
     * @param id id of order, which is removed.
     * @return false if there is no such order in the storage, or user id (in case when the client (not admin)
     * deletes order) don`t match id of the order owner, true otherwise.
     */
    boolean remove(long id);

    /**
     * Returns sorted by natural comparison method list of current (sign in) user orders.
     * @return collection of user`s orders.
     */
    List<Order> getUserOrders();

    /**
     * Creates draft of order.
     */
    void createDraft();

    /**
     * Adds product to the draft of order.
     * The presence of the product is checked.
     *
     * @param productID the id of the product, which is added.
     * @return false if there is no such product in the storage or true otherwise.
     */
    boolean addProductToDraft(long productID);

    /**
     * Removes product from the draft of order.
     * The presence of the product in the draft of order is checked.
     *
     * @param productID the id of the product, which is removed.
     * @return false if there is no such product in the draft of order or true otherwise.
     */
    boolean removeProductFromDraft(long productID);

    /**
     * Copies the order, which id is entered, to draft of order for further modification.
     *
     * @param orderID the id of order, which is copied.
     * @return false if there is no such order in the storage or true otherwise.
     */
    boolean copyOrderToDraft(long orderID);

    /**
     * Returns the list of products from the draft of order.
     *
     * @return the list of products from the draft of order.
     */
    List getDraftProducts();

    /**
     * Saves the draft of order as an order in the storage.
     * If draft`s product list is empty, draft don`t save.
     *
     * @return true if draft`s product list is not empty (and draft is saved) or false otherwise.
     */
    boolean save();

    /**
     * Returns sorted by natural comparison method list of all orders.
     */
    List<Order> getAll();
}