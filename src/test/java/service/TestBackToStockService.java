package service;

import com.example.domain.Product;
import com.example.domain.ProductCategory;
import com.example.domain.User;
import com.example.service.impl.BackToStockServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class TestBackToStockService {

    private static final BackToStockServiceImpl backToStockService = new BackToStockServiceImpl();
    private static final List<User> userList = new ArrayList<>();
    private static final Product product = new Product("1", ProductCategory.BOOKS);
    private static final Product product1 = new Product("2", ProductCategory.MEDICAL);

    @BeforeAll
    public static void setup() {
        User user = new User("Dmitrii", true, 10);
        User user1 = new User("Yurii", true, 80);
        User user2 = new User("Artur", false, 71);
        User user3 = new User("Viktor", false, 50);
        User user4 = new User("Roman", false, 40);

        backToStockService.subscribe(user3, product);
        backToStockService.subscribe(user, product);
        backToStockService.subscribe(user1, product);
        backToStockService.subscribe(user4, product);
        backToStockService.subscribe(user2, product);

        userList.add(user);
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        userList.add(user4);
    }

    @Test
    public void testSubscribeUser() {
        backToStockService.subscribe(new User(), new Product("3", ProductCategory.MEDICAL));
    }

    @Test
    public void testSubscribeUserWhenValueIsNullWillThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> backToStockService.subscribe(null, null));
    }

    @Test
    public void testSubscribedUsersWillReturnUsersWithCorrectProduct() {
        List<User> result = backToStockService.subscribedUsers(product);

        assertEquals(userList, result);
    }

    @Test
    public void testSubscribedUsersWillReturnEmptyListWhenNoUsersWaitingForProduct() {
        List<User> result = backToStockService.subscribedUsers(product1);

        assertTrue(result.isEmpty());
    }
}
