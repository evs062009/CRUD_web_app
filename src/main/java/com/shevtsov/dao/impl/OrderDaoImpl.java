package com.shevtsov.dao.impl;

import com.shevtsov.dao.OrderDao;

public class OrderDaoImpl implements OrderDao {

    @Override
    public void listAllOrder() {
        System.out.println("Receiving data from DB...");
        System.out.println("Creating collection...");
        System.out.println("Transmitting to Service");
    }

    @Override
    public boolean findOrderByID(long id) {
        System.out.println("Searching...  Please wait");
        System.out.println("Creating object from DB data...");
        return true;
    }

    @Override
    public boolean createOrder() {
        System.out.println("Saving... Please wait");
        return true;
    }
}
