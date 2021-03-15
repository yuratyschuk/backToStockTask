package domain.out.of.stock.factory;

import com.example.domain.Product;
import com.example.domain.ProductCategory;
import com.example.domain.User;
import com.example.domain.out.of.stock.OutOfStockInfo;
import com.example.domain.out.of.stock.factory.OutOfStockInfoFactory;
import org.junit.jupiter.api.Test;

import static com.example.domain.out.of.stock.Priority.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestOutOfStockInfoFactory {

    private OutOfStockInfoFactory outOfStockInfoFactory = new OutOfStockInfoFactory();
    private Product product = new Product("1", ProductCategory.MEDICAL);

    @Test
    public void testCreateOutOfStockInfoWhenUserIsPremiumWillReturnPremiumPriority() {
        User user = new User("Yurii", true, 50);

        OutOfStockInfo result = outOfStockInfoFactory.create(user, product);

        assertEquals(PREMIUM, result.getPriority());
    }

    @Test
    public void testCreateOutOfStockInfoWhenUserElderThan70AndProductIsMedicalWillReturnHighPriority() {
        User user = new User("Yurii", false, 71);

        OutOfStockInfo result = outOfStockInfoFactory.create(user, product);

        assertEquals(HIGH, result.getPriority());
    }

    @Test
    public void testCreateOutOfStockInfoWhenUserElderThan70WillReturnMediumPriority() {
        User user = new User("Yurii", false, 71);

        OutOfStockInfo result = outOfStockInfoFactory.create(user, new Product("1", ProductCategory.BOOKS));

        assertEquals(MEDIUM, result.getPriority());
    }

    @Test
    public void testCreateOutOfStockInfoWillReturnNormalPriority() {
        User user = new User("Yurii", false, 50);

        OutOfStockInfo result = outOfStockInfoFactory.create(user, product);

        assertEquals(NORMAL, result.getPriority());
    }

    @Test
    public void testCreateOutOfStockInfoWithProductCategoryNullWillThrowException() {
        User user = new User("Yurii", false, 50);

        assertThrows(IllegalArgumentException.class, () -> outOfStockInfoFactory.create(user, new Product("1", null)));
    }
}
