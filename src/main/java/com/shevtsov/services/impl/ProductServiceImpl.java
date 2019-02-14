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
    public boolean modifyProduct(long id, String newName, BigDecimal newPrice) {
        System.out.println("Processing...");
        return productDao.modifyProduct(id, newName, newPrice);
    }

    @Override
    public boolean removeProduct(long id) {
        System.out.println("Processing...");
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