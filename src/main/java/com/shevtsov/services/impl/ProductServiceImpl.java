package com.shevtsov.services.impl;

import com.shevtsov.dao.ProductDao;
import com.shevtsov.dao.impl.ProductDaoImpl;
import com.shevtsov.domain.Product;
import com.shevtsov.services.ProductService;

import java.math.BigDecimal;

public class ProductServiceImpl implements ProductService {
    private ProductDao productDao = new ProductDaoImpl();

    @Override
    public void createProduct(String name, BigDecimal price) {
        Product product = new Product(name, price);
        if (productDao.saveProduct(product)) {
            System.out.println("Product saved: " + product);
        }
    }

    @Override
    public void modifyProduct(long id, String name, BigDecimal price) {
        if (productDao.modifyProduct(id)) {
            System.out.println("Product modified");
        } else {
            System.out.println("Product have NOT been modified!!!");
        }
    }

    @Override
    public void removeProduct(long id) {
        if (productDao.removeProduct(id)) {
            System.out.println("Product removed");
        } else {
            System.out.println("Product have NOT been removed!!!");
        }
    }

    @Override
    public void listAllProducts() {
        productDao.listAllProducts();
    }
}
