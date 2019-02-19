package com.shevtsov.dao;

import com.shevtsov.domain.Product;

import java.util.List;

public interface ProductDao {

    /**
     * Saves Product-object to storage.
     *
     * @param product the Product-object, which is saved.
     * @return true if new product was saved successfully, or false otherwise.
     */
    void save(Product product);

    /**
     * Modifies product data in storage.
     *
     * @param id      id of product, which is modified.
     * @param product
     * @return true if product was modified successfully, or false otherwise.
     */
    void modify(long id, Product product);

    /**
     * Removes product in storage.
     *
     * @param product the Product-object, which is removed.
     * @return true if product was removed successfully, or false otherwise.
     */
    void remove(long product);

    /**
     * Creates collection of Product-objects from storage and transmits it to Source for processing.
     */
    List<Product> getAll();

    boolean isContainsKey(long id);

    Product findByID(long id);
}