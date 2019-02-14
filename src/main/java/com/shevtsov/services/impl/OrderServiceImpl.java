package com.shevtsov.services.impl;

import com.shevtsov.dao.OrderDao;
import com.shevtsov.dao.impl.OrderDaoImpl;
import com.shevtsov.services.OrderService;

public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao = new OrderDaoImpl();

    @Override
    public void listAllOrder() {
        orderDao.createOrdersList();
        System.out.println("Received collection from DAO");
        System.out.println("Processed");
        System.out.println("Transmitted to UI");
    }

    @Override
    public boolean findOrderByID(long id) {
        System.out.println("Processing...");
        return orderDao.findOrderByID(id);
    }

    @Override
    public void addProductToBasket(long productID) {
        System.out.println("Product added to the basket");
    }

    @Override
    public void removeProductFromBasket(long productID) {
        System.out.println("Product removed from the basket");
    }

    @Override
    public boolean createOrder() {
        System.out.println("Processing...");
        return orderDao.saveOrder();
    }
}