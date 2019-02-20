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

import java.util.List;

public class OrderServiceImpl implements OrderService {
    private final ClientDao clientDao = ClientDaoImpl.getInstance();
    private final ProductDao productDao = ProductDaoImpl.getInstance();
    private final OrderDao orderDao = OrderDaoImpl.getInstance();
    private final AuthorisationImpl authorisation = AuthorisationImpl.getInstance();
    private Order draft;

    @Override
    public List<Order> getAll() {
        return orderDao.getAll();
    }

    @Override
    public boolean save() {
        List<Product> products = draft.getProducts();
        if (!products.isEmpty()) {
            orderDao.save(draft);
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(long id) {
        Order order = orderDao.findByID(id);
        if (order != null) {
            if (authorisation.getCurrentUserID() == -1 ||
                    authorisation.getCurrentUserID() == order.getClient().getId()) {
                orderDao.remove(id);
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Order> getUserOrders() {
        return orderDao.getUserOrders(authorisation.getCurrentUserID());
    }

    @Override
    public boolean addProductToDraft(long productID) {
        Product product = productDao.findByID(productID);
        if (product != null) {
            draft.getProducts().add(product);
            return true;
        }
        return false;
    }

    @Override
    public boolean removeProductFromDraft(long productID) {
        List<Product> products = draft.getProducts();
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == productID) {
                products.remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean copyOrderToDraft(long id) {
        Order order = orderDao.findByID(id);
        if (order != null) {
            draft = new Order(order);
            return true;
        }
        return false;
    }

    @Override
    public void createDraft() {
        Client currentClient = clientDao.findByID(authorisation.getCurrentUserID());
        draft = new Order(currentClient);
        draft.setId(-1);
    }

    @Override
    public List getDraftProducts() {
        return draft.getProducts();
    }
}