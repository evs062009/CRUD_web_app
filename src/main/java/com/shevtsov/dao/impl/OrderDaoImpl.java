package com.shevtsov.dao.impl;

import com.shevtsov.dao.OrderDao;

public class OrderDaoImpl implements OrderDao {

    @Override
    public void createOrdersList() {
        System.out.println("Receiving data from storage...");
        System.out.println("Creating collection...");
        System.out.println("Transmitting to Service");
    }

    @Override
    public boolean findOrderByID(long id) {
        System.out.println("Searching...  Please wait");
        System.out.println("Creating object from storage data...");
        return true;
    }

    @Override
    public boolean saveOrder() {
        System.out.println("Saving... Please wait");
        return true;
    }
}
