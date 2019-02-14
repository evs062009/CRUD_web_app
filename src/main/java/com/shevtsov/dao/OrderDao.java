package com.shevtsov.dao;

public interface OrderDao {

    void listAllOrder();

    boolean findOrderByID(long id);

    boolean createOrder();
}
