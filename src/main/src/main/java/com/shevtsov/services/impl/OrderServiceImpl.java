package com.shevtsov.services.impl;

import com.shevtsov.dao.ClientDao;
import com.shevtsov.dao.OrderDao;
import com.shevtsov.dao.ProductDao;
import com.shevtsov.dao.impl.ClientDBDao;
import com.shevtsov.dao.impl.ClientDaoImpl;
import com.shevtsov.dao.impl.OrderDaoImpl;
import com.shevtsov.dao.impl.ProductDaoImpl;
import com.shevtsov.domain.Client;
import com.shevtsov.domain.Order;
import com.shevtsov.domain.Product;
import com.shevtsov.services.OrderService;

import java.util.Collections;
import java.util.List;

public class OrderServiceImpl implements OrderService {
    private final ClientDao clientDao = ClientDBDao.getInstance();
    private final ProductDao productDao = ProductDaoImpl.getInstance();
    private final OrderDao orderDao = OrderDaoImpl.getInstance();
    private final AuthorisationImpl authorisation = AuthorisationImpl.getInstance();
    private Order draft;

    @Override
    public List<Order> getAll() {
        List<Order> orders = orderDao.getAll();
        Collections.sort(orders);
        return orders;
    }

    @Override
    public boolean save() {
        List<Product> products = draft.getProducts();
        if (!products.isEmpty()) {
            orderDao.save(draft);
            return true;
        }
        System.out.println("log: Saving has not been done!!! (there is no product in the order)");
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
        System.out.println("log: Removing has not been done!!!");
        return false;
    }

    @Override
    public List<Order> getUserOrders() {
        List<Order> orders = orderDao.getUserOrders(authorisation.getCurrentUserID());
        Collections.sort(orders);
        return orders;

    }

    @Override
    public boolean addProductToDraft(long productID) {
        Product product = productDao.findByID(productID);
        if (product != null) {
            draft.getProducts().add(product);
            return true;
        }
        System.out.println("log: Product has not been added (there is no such product)");
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
        System.out.println("log: Product has not been  removed (there is no such product)");
        return false;
    }

    @Override
    public boolean copyOrderToDraft(long id) {
        Order order = orderDao.findByID(id);
        if (order != null) {
            draft = new Order(order);
            return true;
        }
        System.out.println("log: Copying has not been done (there is no such order)");
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