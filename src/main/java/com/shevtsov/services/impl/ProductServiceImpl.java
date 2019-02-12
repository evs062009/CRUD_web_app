package com.shevtsov.services.impl;

import com.shevtsov.dao.ProductDao;
import com.shevtsov.dao.impl.ProductDaoImpl;
import com.shevtsov.domain.Product;
import com.shevtsov.services.ProductService;

import java.math.BigDecimal;

public class ProductServiceImpl implements ProductService {
    private ProductDao productDao = new ProductDaoImpl();

    @Override
    public void createProduct(String name, BigDecimal price) {
        Product product = new Product(name, price);
        if (productDao.saveProduct(product)){
            System.out.println("Product saved: " + product);
        }
    }

    @Override
    public void modifyProduct(long id, String name, BigDecimal price) {
        Product product = productDao.searchProduct(id);
        if (product != null){
            if (productDao.modifyProduct(product, name, price)){
                System.out.println("Product modified: " + product);
            } else {
                System.out.println("There is no such client.");
            }
        }
    }

    @Override
    public void removeProduct(long id) {
        Product product = productDao.searchProduct(id);
        if (product != null){
            if (productDao.removeProduct(product)){
                System.out.println("Product removed: " + product);
            } else {
                System.out.println("There is no such client.");
            }
        }
    }

    @Override
    public void listAllProducts() {
        productDao.listAllProducts();
    }
}
