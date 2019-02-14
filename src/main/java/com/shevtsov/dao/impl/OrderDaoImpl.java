package com.shevtsov.dao.impl;

import com.shevtsov.dao.OrderDao;
import com.shevtsov.domain.Order;

public class OrderDaoImpl implements OrderDao {

    @Override
    public void listAllOrder() {
        System.out.println("List of orders:");
        System.out.println("...");
        System.out.println("...");
    }

    @Override
    public Order searchOrder(long id) {
        System.out.println("Searching order by id...");
        return null;
    }

    @Override
    public void showOrder(long order) {
        System.out.println(order);
    }
}
