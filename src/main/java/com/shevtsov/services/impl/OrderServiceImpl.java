package com.shevtsov.services.impl;

import com.shevtsov.dao.OrderDao;
import com.shevtsov.dao.impl.OrderDaoImpl;
import com.shevtsov.domain.Order;
import com.shevtsov.services.OrderService;

import java.util.List;

public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao = OrderDaoImpl.getInstance();

    @Override
    public List<Order> getAll() {
        return orderDao.getAll();
    }

    @Override
    public boolean findByID(long id) {
        System.out.println("Processing...");
        return orderDao.findByID(id);
    }

    @Override
    public boolean create() {
    }

    @Override
    public boolean remove(long id) {
        if (orderDao.isContainsKey(id)) {
            orderDao.remove(id);
            return true;
        } else {
            return false;
        }
    }
}