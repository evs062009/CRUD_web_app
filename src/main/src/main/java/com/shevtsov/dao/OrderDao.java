package com.shevtsov.dao;

import com.shevtsov.domain.Order;

import java.util.List;
import java.util.Optional;

public interface OrderDao {

    /**
     * Creates and returns collection of Order-objects from storage.
     *
     * @return collection of Order-objects from storage.
     */
    List<Order> getAll();

    /**
     * Searches for order by id in the storage.
     *
     * @param id the id of the order, which is searched.
     * @return Order-object, if such was found, or null otherwise.
     */
    Optional<Order> findByID(long id);

    /**
     * Calculates id if needed (if current id = -1), assigns it to Order-object and saves the object to storage.
     *
     * @param order the Order-object, which is saved.
     * @return true if order is saved successfully, ot false otherwise.
     */
    boolean save(Order order);

    /**
     * Removes Order-object in storage.
     *
     * @param id the id of Order-object, which is removed.
     * @return true if order is removed successfully, ot false otherwise.
     */
    boolean remove(long id);

    /**
     * Creates and returns collection of orders, which owner id matches to currentUserID.
     *
     * @param currentUserID the id of current (authorised) user
     * @return collection of current (authorised) user`s orders
     */
    List<Order> getUserOrders(long currentUserID);

    /**
     * Modifies the products of Order in storage.
     *
     * @param draft the order, contains new list of products
     * @return true if order is modified successfully, ot false otherwise.
     */
    boolean modify(Order draft);
}