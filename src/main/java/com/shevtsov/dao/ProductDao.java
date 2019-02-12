package com.shevtsov.dao;

import com.shevtsov.domain.Product;

import java.math.BigDecimal;

public interface ProductDao {
    
    boolean saveProduct(Product product);

    Product searchProduct(long id);

    boolean modifyProduct(Product product, String name, BigDecimal price);

    boolean removeProduct(Product product);

    void listAllProducts();
}
