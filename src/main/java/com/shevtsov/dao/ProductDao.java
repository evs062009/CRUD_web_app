package com.shevtsov.dao;

import com.shevtsov.domain.Product;

import java.math.BigDecimal;

public interface ProductDao {

    /**
     * Saves Product-object to storage.
     * @param product the Product-object, which is saved.
     * @return true if new product was saved successfully, or false otherwise.
     */
    boolean saveProduct(Product product);

    /**
     * Modifies product data in storage.
     * @param id id of product, which is modified.
     * @param name new name of product, which is modified.
     * @param price new price of product, which is modified.
     * @return true if product was modified successfully, or false otherwise.
     */
    boolean modifyProduct(long id, String name, BigDecimal price);

    /**
     * Removes product in storage.
     * @param product the Product-object, which is removed.
     * @return true if product was removed successfully, or false otherwise.
     */
    boolean removeProduct(long product);

    /**
     * Creates collection of Product-objects from storage and transmits it to Source for processing.
     */
    void createProductsList();
}