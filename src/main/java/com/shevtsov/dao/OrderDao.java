package com.shevtsov.dao;

import com.shevtsov.domain.Order;

public interface OrderDao {
    void listAllOrder();

    Order searchOrder(long id);

    void showOrder(Order order);
}
