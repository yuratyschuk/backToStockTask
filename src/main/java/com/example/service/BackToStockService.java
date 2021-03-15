package com.example.service;

import com.example.domain.Product;
import com.example.domain.User;

import java.util.List;

public interface BackToStockService {

    void subscribe(User user, Product product);

    List<User> subscribedUsers(Product product);
}
