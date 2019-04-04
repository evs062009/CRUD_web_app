package com.shevtsov.dao.impl.EMDao;

import com.shevtsov.dao.OrderDao;
import com.shevtsov.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

/*
 * Order Dao for Hiber (Entity Manager - EM)
 */
@Repository
public class OrderEMDaoImpl extends AbstractEMDaoImpl<Order> implements OrderDao {

    @Autowired
    public OrderEMDaoImpl() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("persistence-unit");
        this.entityManager = factory.createEntityManager();
    }

    @Override
    public List<Order> getUserOrders(long currentUserID) {
        List<Order> orders = new ArrayList<>();
        try {
            entityManager.getTransaction().begin();
            orders = entityManager.createQuery(queryFrom() + " WHERE CLIENT_ID = :clientID", getEntityClass()).
                    setParameter("clientID", currentUserID).getResultList();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orders;
    }

    @Override
    Class<Order> getEntityClass() {
        return Order.class;
    }

    @Override
    String queryFrom() {
        return "FROM Order";
    }
}