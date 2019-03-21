package com.shevtsov.domain;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class Order implements Comparable<Order> {
    private Long id;
    private Client client;
    private List<Product> products;

    public Order(){
        id = -1L;
        client = null;
        products = new ArrayList<>();
    }

    public Order(Client client) {
        this.client = client;
        products = new ArrayList<>();
    }

    public Order(long id, Client client, List<Product> products) {
        this(client);
        this.id = id;
        this.products = products;
    }

    public Order(Order baseOrder) {
        this.id = baseOrder.id;
        this.client = baseOrder.client;
        products = new ArrayList<>();
        products.addAll(baseOrder.products);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    @Override
    public int compareTo(Order o) {
        return (int) (id - o.id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return Objects.equals(getId(), order.getId()) &&
                Objects.equals(getClient(), order.getClient()) &&
                Objects.equals(getProducts(), order.getProducts());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getClient(), getProducts());
    }
}