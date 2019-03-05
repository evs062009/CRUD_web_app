package com.shevtsov.services.impl;

import com.shevtsov.dao.ClientDao;
import com.shevtsov.dao.OrderDao;
import com.shevtsov.dao.ProductDao;
import com.shevtsov.dao.impl.*;
import com.shevtsov.domain.Client;
import com.shevtsov.domain.Order;
import com.shevtsov.domain.Product;
import com.shevtsov.exceptions.ObjectNotFoundExeption;
import com.shevtsov.services.OrderService;

import java.util.Collections;
import java.util.List;

public class OrderServiceImpl implements OrderService {
    private final ClientDao clientDao = ClientDBDao.getInstance();
    private final ProductDao productDao = ProductDBDao.getInstance();
    private final OrderDao orderDao = OrderDBDao.getInstance();
    private final AuthorisationImpl authorisation = AuthorisationImpl.getInstance();
    private Order draft;

    @Override
    public List<Order> getAll() {
        List<Order> orders = orderDao.getAll();
        if (orders != null) {
            Collections.sort(orders);
        }
        return orders;
    }

    @Override
    public boolean save() {
        List<Product> products = draft.getProducts();
        if (!products.isEmpty()) {
            if (draft.getId() == -1){
                orderDao.save(draft);
            } else {
                orderDao.modify(draft);
            }
            return true;
        }
        System.out.println("log: Saving has not been done!!! (there is no product in the order)");
        return false;
    }

    @Override
    public boolean remove(long id) {
        if (orderDao.findByID(id).isPresent()) {
            Order order = orderDao.findByID(id).get();
            if (authorisation.getCurrentUserID() == -1 ||
                    authorisation.getCurrentUserID() == order.getClient().getId()) {
                orderDao.remove(id);
                return true;
            }
        }
        System.out.println("log: Removing has not been done!!!");
        return false;
    }

    @Override
    public List<Order> getUserOrders() {
        List<Order> orders = orderDao.getUserOrders(authorisation.getCurrentUserID());
        if (orders != null) {
            Collections.sort(orders);
        }
        return orders;

    }

    @Override
    public boolean addProductToDraft(long productID) throws ObjectNotFoundExeption {
        boolean isPresent = productDao.findByID(productID).isPresent();
        if (isPresent){
            Product product = productDao.findByID(productID).get();
            draft.getProducts().add(product);
        }
        return isPresent;
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
        System.out.println("log: Product has not been  removed (there is no such product)");
        return false;
    }

    @Override
    public boolean copyOrderToDraft(long id) {
        if (orderDao.findByID(id).isPresent()) {
            Order order = orderDao.findByID(id).get();
            draft = new Order(order);
            return true;
        }
        System.out.println("log: Copying has not been done (there is no such order)");
        return false;
    }

    @Override
    public void createDraft() {
        long id = authorisation.getCurrentUserID();
        Client currentClient = clientDao.findByID(id)
                .orElseThrow(() -> new ObjectNotFoundExeption(id));
        draft = new Order(currentClient);
        draft.setId(-1L);
    }

    @Override
    public List getDraftProducts() {
        return draft.getProducts();
    }
}