package com.shevtsov.dao;

import com.shevtsov.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductDao {

    /**
     * Saves the Product-object to storage.
     *
     * @param product the Product-object, which is saved.
     * @return true if product is saved successfully, ot false otherwise.
     */
    boolean save(Product product);

    /**
     * Removes Product-object in storage.
     *
     * @param id the id of Product-object, which is removed.
     * @return true if client is removed successfully, ot false otherwise.
     */
    boolean remove(long id);

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
    Optional<Product> findByID(long id);

    /**
     * Modifies the Product-object data in storage.
     * @param product the Product-object, which is modified.
     * @return true if client is modified successfully, ot false otherwise.
     */
    boolean modify(Product product);
}