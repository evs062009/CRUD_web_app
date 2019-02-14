package com.shevtsov.services.impl;

import com.shevtsov.dao.ProductDao;
import com.shevtsov.dao.impl.ProductDaoImpl;
import com.shevtsov.domain.Product;
import com.shevtsov.services.ProductService;

import java.math.BigDecimal;

public class ProductServiceImpl implements ProductService {
    private ProductDao productDao = new ProductDaoImpl();

    @Override
    public boolean createProduct(String name, BigDecimal price) {
        Product product = new Product(name, price);
        return productDao.saveProduct(product);
    }

    @Override
    public boolean modifyProduct(long id, String name, BigDecimal price) {
        return productDao.modifyProduct(id, name, price);
    }

    @Override
    public boolean removeProduct(long id) {
        return productDao.removeProduct(id);
    }

    @Override
    public void listAllProducts() {
        productDao.listAllProducts();
        System.out.println("Received collection from DAO");
        System.out.println("Processed");
        System.out.println("Transmitted to UI");
    }
}
