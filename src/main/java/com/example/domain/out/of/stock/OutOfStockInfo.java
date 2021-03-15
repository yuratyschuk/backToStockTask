package com.example.domain.out.of.stock;

import com.example.domain.Product;
import com.example.domain.User;

public class OutOfStockInfo {

    private Priority priority;

    private User user;

    private Product product;

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
