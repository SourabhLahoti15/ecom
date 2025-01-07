package dao;

import java.util.List;

import model.Wishlist;

public interface WishlistDAO {

    boolean addItemToWishlist(int user_id, int product_id);
    
    // boolean updateItemQuantity(int cartId, int quantity);
    
    List<Wishlist> getWishlistItemsByUserId(int userId);
    
    boolean removeItemFromWishlist(int userId, int productId);
    
}
