package com.shevtsov.dao.impl.EMDao;

import com.shevtsov.dao.ProductDao;
import com.shevtsov.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/*
 * Product Dao for Hiber (Entity Manager - EM)
 */
@Repository
public class ProductEMDaoImpl extends AbstractEMDaoImpl<Product> implements ProductDao {

    @Autowired
    public ProductEMDaoImpl() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("persistence-unit");
        this.entityManager = factory.createEntityManager();
    }

    @Override
    Class<Product> getEntityClass() {
        return Product.class;
    }

    @Override
    String queryFrom() {
        return "FROM Product";
    }
}