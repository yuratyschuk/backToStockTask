package com.example.domain;

import java.util.Objects;

public class Product {

    private final String id;
    private final ProductCategory category;

    public Product(String id, ProductCategory category) {
        if (category == null) {
            throw new IllegalArgumentException("Error while creating product. Product Category must not be null.");
        }

        this.id = id;
        this.category = category;
    }

    public String getId() {
        return id;
    }

    public ProductCategory getCategory() {
        return category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) && category == product.category;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, category);
    }
}
