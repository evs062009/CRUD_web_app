package com.shevtsov.dao.impl;

import com.shevtsov.dao.ProductDao;
import com.shevtsov.domain.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductDaoImpl implements ProductDao {
    private Map<Long, Product> map = new HashMap<>();       //storage emulation
    private static long generator = 0;
    private static ProductDao instance;

    private ProductDaoImpl() {
    }

    public static ProductDao getInstance() {
        if (instance == null){
            instance = new ProductDaoImpl();
        }
        return instance;
    }

    @Override
    public void save(Product product) {
        product.setId(generator++);
        map.put(product.getId(), product);
    }

    @Override
    public void modify(long id, Product product) {
        map.put(id, product);
    }

    @Override
    public void remove(long id) {
        map.remove(id);
    }

    @Override
    public List<Product> getAll() {
        return new ArrayList<>(map.values());
    }

    @Override
    public boolean isContainsKey(long id) {
        return map.containsKey(id);
    }

    @Override
    public Product findByID(long id) {
        return map.get(id);
    }
}