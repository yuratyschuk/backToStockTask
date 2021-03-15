package com.example.service.impl;

import com.example.domain.Product;
import com.example.domain.User;
import com.example.domain.out.of.stock.OutOfStockInfo;
import com.example.domain.out.of.stock.factory.OutOfStockInfoFactory;
import com.example.service.BackToStockService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class BackToStockServiceImpl implements BackToStockService {

    private OutOfStockInfoFactory outOfStockInfoFactory = new OutOfStockInfoFactory();
    private List<OutOfStockInfo> outOfStockInfoList = new ArrayList<>();

    @Override
    public void subscribe(User user, Product product) {
        if (user == null || product == null) {
            throw new IllegalArgumentException("Error while subscribing for product. User or product is null.");
        }

        OutOfStockInfo outOfStockInfo = outOfStockInfoFactory.create(user, product);

        outOfStockInfoList.add(outOfStockInfo);
    }

    @Override
    public List<User> subscribedUsers(Product product) {
        return outOfStockInfoList.stream()
                .filter(outOfStockInfo -> outOfStockInfo.getProduct().equals(product))
                .sorted(Comparator.comparing(OutOfStockInfo::getPriority))
                .map(OutOfStockInfo::getUser)
                .collect(Collectors.toList());
    }

}
