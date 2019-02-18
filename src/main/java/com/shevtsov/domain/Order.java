package com.shevtsov.domain;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private long id;
    private Client client;
    private List<Product> products;

    public Order(Client client) {
        this.client = client;
        products = new ArrayList<>();
    }

    public Order(Order baseOrder){
        this.id = baseOrder.id;
        this.client = baseOrder.client;
        products = new ArrayList<>();
        products.addAll(baseOrder.products);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", client=" + client +
                ", products=" + products +
                '}';
    }
}