package com.shevtsov.dao.impl;

import com.shevtsov.dao.ClientDao;
import com.shevtsov.dao.ProductDao;
import com.shevtsov.domain.Client;
import com.shevtsov.domain.Product;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class ProductDaoImpl implements ProductDao {
    //storage emulation
    private Map<Long, Product> map = new HashMap<>();
    private static long generator = 0;
    //object-singleton
    private static ProductDao productDao = new ProductDaoImpl();

    //constructor-singleton
    private ProductDaoImpl(){
    }

    //factory method for singleton
    public static ProductDao getInstance(){
        return productDao;
    }

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
    public void createProductsList() {
        System.out.println("Receiving data from storage...");
        System.out.println("Creating collection...");
        System.out.println("Transmitting to Service");
    }
}