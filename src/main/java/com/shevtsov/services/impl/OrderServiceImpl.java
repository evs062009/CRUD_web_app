package com.shevtsov.services.impl;

import com.shevtsov.dao.OrderDao;
import com.shevtsov.dao.impl.OrderDaoImpl;
import com.shevtsov.domain.Order;
import com.shevtsov.services.OrderService;

public class OrderServiceImpl implements OrderService {
    OrderDao orderDao = new OrderDaoImpl();

    @Override
    public void listAllOrder() {
        orderDao.listAllOrder();
    }

    @Override
    public void showOrder(long id) {
        Order order = orderDao.searchOrder(id);
        if (order != null){
            orderDao.showOrder(order);
        } else {
            System.out.println("There is no such order.");
        }
    }
}
