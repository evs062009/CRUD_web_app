package com.shevtsov.services.impl;

import com.shevtsov.dao.ProductDao;
import com.shevtsov.dao.impl.ProductDaoImpl;
import com.shevtsov.domain.Product;
import com.shevtsov.services.ProductService;

import java.math.BigDecimal;
import java.util.List;

public class ProductServiceImpl implements ProductService {
    private final ProductDao productDao = ProductDaoImpl.getInstance();

    @Override
    public boolean create(String name, BigDecimal price) {
        //add validation in future
        boolean validation = true;
        if (validation) {
            Product product = new Product(name, price);
            productDao.save(product);
            return true;
        }
        System.out.println("log: Creating has not been done");
        return false;
    }

    @Override
    public boolean modify(long id, String newName, BigDecimal newPrice) {
        if (productDao.isContainsKey(id)) {
            Product product = new Product(newName, newPrice);
            product.setId(id);
            productDao.save(id, product);
            return true;
        }
        System.out.println("log: Modifying has not been done (there is no such product)");
        return false;
    }

    @Override
    public boolean remove(long id) {
        if (productDao.isContainsKey(id)) {
            productDao.remove(id);
            return true;
        }
        System.out.println("log: Removing has not been done (there is no such product)");
        return false;
    }

    @Override
    public List<Product> gatAll() {
        return productDao.getAll();
    }

    @Override
    public Product getProduct(long id) {
        return productDao.findByID(id);
    }
}