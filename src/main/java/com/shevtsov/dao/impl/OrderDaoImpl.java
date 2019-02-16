package com.shevtsov.dao.impl;

import com.shevtsov.dao.OrderDao;
import com.shevtsov.domain.Order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderDaoImpl implements OrderDao {
    //storage emulation
    private Map<Long, Order> map = new HashMap<>();
    private static long generator = 0;
    //object-singleton
    private static OrderDao orderDao = new OrderDaoImpl();

    //constructor-singleton
    private OrderDaoImpl() {
    }

    //factory method for singleton
    public static OrderDao getInstance() {
        return orderDao;
    }

    @Override
    public boolean save(Order order) {
        return false;
    }

    @Override
    public void remove(long id) {
        map.remove(id);
    }

    @Override
    public List<Order> getAll() {
        return new ArrayList<>(map.values());
    }

    @Override
    public boolean findByID(long id) {
        System.out.println("Searching...  Please wait");
        System.out.println("Creating object from storage data...");
        return true;
    }

    @Override
    public boolean isContainsKey(long id) {
        return map.containsKey(id);
    }
}