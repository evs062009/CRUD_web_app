package com.shevtsov.domain;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
@Entity
@Table(name = "ORDERS")
public class Order implements Comparable<Order> {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "CLIENT_ID")
    private Client client;

    @ManyToMany
    @JoinTable(name = "SPECIFICATIONS", joinColumns = @JoinColumn(name = "ORDER_ID"),
            inverseJoinColumns = @JoinColumn(name = "PRODUCT_ID"))
    private List<Product> products;         //for Hiber

    public Order() {
        products = new ArrayList<>();
    }

    public Order(Client client) {
        this();
        this.client = client;
    }

    public Order(long id, Client client, List<Product> products) {
        this(client);
        this.id = id;
        this.products = products;
    }

    public Order(Order baseOrder) {
        this();
        this.id = baseOrder.id;
        this.client = baseOrder.client;
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