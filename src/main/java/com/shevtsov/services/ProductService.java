package com.shevtsov.services;

import com.shevtsov.domain.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {

    /**
     * Creates and transmits a Product-object to DAO for saving.
     *
     * @param name  the name of new product.
     * @param price the price of new product.
     * @return true if new product was saved successfully, or false otherwise.
     */
    boolean create(String name, BigDecimal price);

    /**
     * Gets data about a product, which is modified, from UI and transmits it to DAO for modifying.
     * Confirmation request and other logic will be added later.
     *
     * @param id       id of product, which is modified.
     * @param newName  new name of product, which is modified.
     * @param newPrice new price of product, which is modified.
     * @return true if product was modified successfully, or false otherwise.
     */
    boolean modify(long id, String newName, BigDecimal newPrice);

    /**
     * Gets data about a product, which is removed, from UI and transmits it to DAO for deleting.
     * Confirmation request and other logic will be added later.
     *
     * @param id id of product, which is removed.
     * @return true if product was removed successfully, or false otherwise.
     */
    boolean remove(long id);

    /**
     * Receives collection of Product-objects from DAO and transmits it to UI for listing.
     * Business-logic will be added later.
     */
    List<Product> gatAll();
}