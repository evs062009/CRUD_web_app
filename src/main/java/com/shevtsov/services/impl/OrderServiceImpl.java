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
    private Order orderDraft;

    @Override
    public List<Order> getAll() {
        return orderDao.getAll();
    }

    @Override
    public boolean save() {
        List<Product> products = orderDraft.getProducts();
        if (!products.isEmpty()) {
            orderDao.save(orderDraft);
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(long id) {
        Order order = orderDao.findByID(id);
        if (order != null) {
            if (authorisation.getCurrentUserID() == -1 ||
                    authorisation.getCurrentUserID() == order.getClient().getId()){
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
    public boolean addProductToOrderDraft(long productID) {
        Product product = productDao.findByID(productID);
        if (product != null) {
            orderDraft.getProducts().add(product);
            return true;
        }
        return false;
    }

    @Override
    public boolean removeProductFromOrderDraft(long productID) {
        List<Product> products = orderDraft.getProducts();
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
        if (order != null){
            orderDraft = new Order(order);
            return true;
        }
        return false;
    }

    @Override
    public void createOrderDraft() {
        Client currentClient = clientDao.findByID(authorisation.getCurrentUserID());
        orderDraft = new Order(currentClient);
        orderDraft.setId(-1);
    }

    @Override
    public List getOrderDraftProducts() {
        return orderDraft.getProducts();
    }
}