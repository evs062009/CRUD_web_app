package com.shevtsov.services.impl;

import com.shevtsov.dao.ClientDao;
import com.shevtsov.dao.OrderDao;
import com.shevtsov.dao.ProductDao;
import com.shevtsov.dao.impl.ClientDaoImpl;
import com.shevtsov.dao.impl.OrderDaoImpl;
import com.shevtsov.dao.impl.ProductDaoImpl;
import com.shevtsov.domain.Client;
import com.shevtsov.domain.Order;
import com.shevtsov.domain.Product;
import com.shevtsov.services.OrderService;

import java.util.ArrayList;
import java.util.List;

public class OrderServiceImpl implements OrderService {
    private final ClientDao clientDao = ClientDaoImpl.getInstance();
    private final ProductDao productDao = ProductDaoImpl.getInstance();
    private final OrderDao orderDao = OrderDaoImpl.getInstance();
    private final AuthorisationImpl authorisation = AuthorisationImpl.getInstance();
    private List<Product> basket = new ArrayList<>();

    public List<Product> getBasket() {
        return basket;
    }

    @Override
    public List<Order> getAll() {
        return orderDao.getAll();
    }

    @Override
    public boolean findByID(long id) {
        return orderDao.findByID(id);
    }

    @Override
    public boolean create() {
        if (!basket.isEmpty()){
            Client currentClient = clientDao.findByID(authorisation.getCurrentUserID());
            if (currentClient != null){
                Order order = new Order(currentClient, basket);
                orderDao.save(order);
                return true;
            }
        }
        return false;
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

    @Override
    public boolean addProductToBasket(long productID) {
        Product product = productDao.findByID(productID);
        return (product != null);
    }

    @Override
    public boolean removeProductFromBasket(long productID) {
        return basket.remove(productID);
    }
}