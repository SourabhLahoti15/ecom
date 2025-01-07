package dao;

import java.util.List;

import model.Cart;

public interface CartDAO {

    boolean addItemToCart(int user_id, int product_id);
    
    // boolean updateItemQuantity(int cartId, int quantity);
    
    boolean removeItemFromCart(int userId, int productId);
    
    List<Cart> getCartItemsByUserId(int userId);
}
