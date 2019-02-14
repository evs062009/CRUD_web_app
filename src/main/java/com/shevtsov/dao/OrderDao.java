package com.shevtsov.dao;

import com.shevtsov.domain.Client;
import com.shevtsov.domain.Order;
import com.shevtsov.domain.Product;

import java.util.ArrayList;

public interface OrderDao {

    void listAllOrder();

    Order findOrderByID(long id);

    boolean createOrder(Client currentClient, ArrayList<Product> basket);
}
