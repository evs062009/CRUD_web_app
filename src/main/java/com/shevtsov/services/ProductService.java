package com.shevtsov.services;

import java.math.BigDecimal;

public interface ProductService {
    boolean createProduct(String name, BigDecimal price);

    boolean modifyProduct(long id, String name, BigDecimal price);

    boolean removeProduct(long id);

    void listAllProducts();
}
