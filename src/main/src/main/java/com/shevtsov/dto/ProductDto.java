package com.shevtsov.dto;

import java.math.BigDecimal;

public class ProductDto {
    private String name;
    private BigDecimal price;

    public ProductDto(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }
}