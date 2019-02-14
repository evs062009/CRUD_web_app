package com.shevtsov.dao.impl;

import com.shevtsov.dao.ProductDao;
import com.shevtsov.domain.Product;

import java.math.BigDecimal;

public class ProductDaoImpl implements ProductDao {
    @Override
    public boolean saveProduct(Product product) {
        System.out.println("Saving... Please wait.");
        return true;
    }

    @Override
    public boolean modifyProduct(long id, String name, BigDecimal price) {
        System.out.println("Modifying... Please wait");
        return true;
    }

    @Override
    public boolean removeProduct(long product) {
        System.out.println("Deleting... Please wait.");
        return true;
    }

    @Override
    public void listAllProducts() {
        System.out.println("List of products:");
        System.out.println("...");
        System.out.println("...");
    }
}