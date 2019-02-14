package com.shevtsov.dao;

import com.shevtsov.domain.Client;
import com.shevtsov.domain.Order;
import com.shevtsov.domain.Product;

import java.util.List;

public interface OrderDao {
    void listAllOrder();

    Order findOrderByID(long id);

    void createOrder(Client currentClient, List<Product> basket);
}
