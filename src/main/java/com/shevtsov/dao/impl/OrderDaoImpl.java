package com.shevtsov.dao.impl;

import com.shevtsov.dao.OrderDao;
import com.shevtsov.domain.Client;
import com.shevtsov.domain.Order;
import com.shevtsov.domain.Product;

import java.util.ArrayList;

public class OrderDaoImpl implements OrderDao {

    @Override
    public void listAllOrder() {
        System.out.println("List of orders:");
        System.out.println("...");
        System.out.println("...");
    }

    @Override
    public Order findOrderByID(long id) {

        //only for test example
        return new Order(new Client("John", "Doe", "111"), new ArrayList<>());
    }

    @Override
    public boolean createOrder(Client currentClient, ArrayList<Product> basket) {
        System.out.println("Saving... Please wait");
        return true;
    }
}
