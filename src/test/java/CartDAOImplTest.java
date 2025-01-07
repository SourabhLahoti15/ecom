import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dao.CartDAOImpl;
import model.Cart;

public class CartDAOImplTest {

    private CartDAOImpl cartDAO;

    @BeforeEach
    void setup() {
        cartDAO = new CartDAOImpl();
    }

    @Test
    void testAddItemToCart() {
        int userId = 101;
        int productId = 201;

        boolean isAdded = cartDAO.addItemToCart(userId, productId);
        assertTrue(isAdded);

        boolean isAddedAgain = cartDAO.addItemToCart(userId, productId);
        assertFalse(isAddedAgain);
    }

    @Test
    void testRemoveItemFromCart() {
        int userId = 101;
        int productId = 202;

        cartDAO.addItemToCart(userId, productId);
        boolean isRemoved = cartDAO.removeItemFromCart(userId, productId);
        assertTrue(isRemoved);

        List<Cart> cartItems = cartDAO.getCartItemsByUserId(userId);
        assertTrue(cartItems.stream().noneMatch(cart -> cart.getProductId() == productId));
    }

    @Test
    void testGetCartItemsByUserId() {
        int userId = 102;

        cartDAO.addItemToCart(userId, 301);
        cartDAO.addItemToCart(userId, 302);

        List<Cart> cartItems = cartDAO.getCartItemsByUserId(userId);
        assertEquals(2, cartItems.size());
        assertTrue(cartItems.stream().anyMatch(cart -> cart.getProductId() == 301));
        assertTrue(cartItems.stream().anyMatch(cart -> cart.getProductId() == 302));
    }
}
