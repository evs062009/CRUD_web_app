package com.shevtsov.services;

import com.shevtsov.domain.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {

    /**
     * Creates and transmits a Product-object to DAO for saving.
     * Parameters validation will be added in future.
     *
     * @param name  the name of new product.
     * @param price the price of new product.
     * @return true if parameters are valid and new product was saved successfully, or false otherwise.
     */
    boolean create(String name, BigDecimal price);

    /**
     * Creates modifying copy of exist Product-object and transmits to DAO for replace in storage.
     * The presence of the object is checked.
     * Name and prise can be changed, id remains the same.
     *
     * @param id       id of product, which is modified.
     * @param newName  new name of product, which is modified.
     * @param newPrice new price of product, which is modified.
     * @return false if there is no such product in the storage, or true otherwise.
     */
    boolean modify(long id, String newName, BigDecimal newPrice);

    /**
     * Transmits to DAO the command to remove exist Product-object from storage.
     * The presence of the object is checked.
     *
     * @param id id of product, which is removed.
     * @return false if there is no such product in the storage, or true otherwise.
     */
    boolean remove(long id);

    /**
     * Receives collection of all Product-objects from DAO and transmits it to UI for listing.
     */
    List<Product> gatAll();

    /**
     * Gets from dao and returns the Product-object, what has specified id.
     * @param id the id of the product, which is searched.
     * @return Product-object, if such was found, or null otherwise.
     */
    Product getProduct(long id);
}