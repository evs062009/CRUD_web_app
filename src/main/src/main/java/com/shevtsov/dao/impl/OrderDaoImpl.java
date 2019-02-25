package com.shevtsov.dao.impl;

import com.shevtsov.dao.OrderDao;
import com.shevtsov.domain.Order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderDaoImpl implements OrderDao {
    private Map<Long, Order> map = new HashMap<>();     //storage emulation
    private static long generator = 0;
    private static final OrderDao INSTANCE = new OrderDaoImpl();

    private OrderDaoImpl() {
    }

    public static OrderDao getInstance() {
        return INSTANCE;
    }

    @Override
    public void save(Order order) {
        if (order.getId() == -1) {
            order.setId(generator++);
        }
        map.put(order.getId(), order);
    }

    @Override
    public void remove(long id) {
        map.remove(id);
    }

    @Override
    public List<Order> getUserOrders(long currentUserID) {
        ArrayList<Order> userOrders = new ArrayList<>();
        for (Map.Entry<Long, Order> entry : map.entrySet()) {
            Order order = entry.getValue();
            if (order.getClient().getId() == currentUserID) {
                userOrders.add(order);
            }
        }
        return userOrders;
    }

    @Override
    public List<Order> getAll() {
        return new ArrayList<>(map.values());
    }

    @Override
    public Order findByID(long id) {
        return map.get(id);
    }
}