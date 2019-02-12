package com.shevtsov.services;

import java.math.BigDecimal;

public interface ProductService {
    void createProduct(String name, BigDecimal price);

    void modifyProduct(long id, String name, BigDecimal price);

    void removeProduct(long id);

    void listAllProducts();
}
