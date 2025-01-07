import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dao.WishlistDAOImpl;
import model.Wishlist;

public class WishlistDAOImplTest {

    private WishlistDAOImpl wishlistDAO;

    @BeforeEach
    void setUp() {
        wishlistDAO = new WishlistDAOImpl();
    }

    @Test
    void testAddItemToWishlist() throws SQLException {
        assertTrue(wishlistDAO.addItemToWishlist(1, 101));
        assertFalse(wishlistDAO.addItemToWishlist(1, 101));
    }

    @Test
    void testGetWishlistItemsByUserId() throws SQLException {
        wishlistDAO.addItemToWishlist(1, 101);
        List<Wishlist> wishlistItems = wishlistDAO.getWishlistItemsByUserId(1);
        assertNotNull(wishlistItems);
        assertEquals(1, wishlistItems.size());
    }

    @Test
    void testRemoveItemFromWishlist() throws SQLException {
        wishlistDAO.addItemToWishlist(1, 101);
        assertTrue(wishlistDAO.removeItemFromWishlist(1, 101));
        assertFalse(wishlistDAO.removeItemFromWishlist(1, 101));
    }
}
