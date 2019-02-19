package com.shevtsov.dao;

import com.shevtsov.domain.Product;

import java.util.List;

public interface ProductDao {

    /**
     * Calculates id, assigns it to Product-object and calls save-method for saving the object to storage.
     *
     * @param product the Product-object, which is saved.
     */
    void save(Product product);

    /**
     * Saves the Product-object to storage.
     *
     * @param id      the id of the Product-object, which is saved.
     * @param product the Product-object, which is saved.
     */
    void save(long id, Product product);

    /**
     * Removes Product-object in storage.
     *
     * @param id the id of Product-object, which is removed.
     */
    void remove(long id);

    /**
     * Creates and returns collection of Product-objects from storage.
     *
     * @return collection of Product-objects.
     */
    List<Product> getAll();

    /**
     * Checks if there is a product with such id in the storage.
     *
     * @param id the id of the product, which is searched.
     * @return true if there is a product with such id in the storage or false otherwise.
     */
    boolean isContainsKey(long id);

    /**
     * Searches for product by id in the storage.
     *
     * @param id the id of the product, which is searched.
     * @return Product-object, if such was found, or null otherwise.
     */
    Product findByID(long id);
}