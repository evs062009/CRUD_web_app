package com.shevtsov.services.impl;

import com.shevtsov.dao.ClientDao;
import com.shevtsov.dao.OrderDao;
import com.shevtsov.dao.impl.ClientDaoImpl;
import com.shevtsov.dao.impl.OrderDaoImpl;
import com.shevtsov.domain.Client;
import com.shevtsov.domain.Order;
import com.shevtsov.domain.Product;
import com.shevtsov.services.OrderService;

import java.util.ArrayList;
import java.util.List;

public class OrderServiceImpl implements OrderService {
    private final OrderDao orderDao = OrderDaoImpl.getInstance();
    private final AuthorisationImpl authorisation = AuthorisationImpl.getInstance();
    private final ClientDao clientDao = ClientDaoImpl.getInstance();
    private List<Product> basket;
    private Order orderDraft;

    public List<Product> getBasket() {
        return basket;
    }

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
        Client currentClient = clientDao.findByID(authorisation.getCurrentUserID());
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

    @Override
    public List<Order> getUserOrders() {
        return orderDao.getUserOrders(authorisation.getCurrentUserID());
    }
}