package com.shevtsov.services.impl;

import com.shevtsov.dao.OrderDao;
import com.shevtsov.dao.impl.OrderDaoImpl;
import com.shevtsov.domain.Client;
import com.shevtsov.domain.Order;
import com.shevtsov.domain.Product;
import com.shevtsov.services.OrderService;

import java.util.ArrayList;

public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao = new OrderDaoImpl();
    private ArrayList<Product> basket = new ArrayList<>();

    @Override
    public void listAllOrder() {
        orderDao.listAllOrder();
    }

    @Override
    public void showOrder(long id) {
        Order order = orderDao.findOrderByID(id);
        if (order != null) {
            System.out.println(order);
        } else {
            System.out.println("There is no such order.");
        }
    }

    @Override
    public void addProductToBasket(long productID) {
        System.out.println("Product added to the basket");
    }

    @Override
    public void listBasket() {
        System.out.println("Your basket:");
        System.out.println("...");
        System.out.println("...");
    }

    @Override
    public void removeProductFromBasket(long productID) {
        System.out.println("Product removed from the basket");
    }

    @Override
    public void createOrder(Client currentClient) {
        if (orderDao.createOrder(currentClient, basket)) {
            System.out.println("Order created");
        }
    }
}
