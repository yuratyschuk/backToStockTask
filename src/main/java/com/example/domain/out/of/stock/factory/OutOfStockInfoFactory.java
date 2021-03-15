package com.example.domain.out.of.stock.factory;

import com.example.domain.Product;
import com.example.domain.ProductCategory;
import com.example.domain.User;
import com.example.domain.out.of.stock.OutOfStockInfo;
import com.example.domain.out.of.stock.Priority;

import static com.example.domain.out.of.stock.Priority.*;

public class OutOfStockInfoFactory {

    public OutOfStockInfo create(User user, Product product) {
        OutOfStockInfo outOfStockInfo = new OutOfStockInfo();
        outOfStockInfo.setUser(user);
        outOfStockInfo.setProduct(product);

        outOfStockInfo.setPriority(calculatePriority(user, product.getCategory()));

        return outOfStockInfo;
    }

    private Priority calculatePriority(User user, ProductCategory productCategory) {
        if (user.isPremium()) {
            return PREMIUM;
        }

        if (user.getAge() > 70) {
            if (productCategory == ProductCategory.MEDICAL) {
                return HIGH;
            }

            return MEDIUM;
        }

        return NORMAL;
    }
}
