package dao;

import java.util.List;

import model.Cart;

public interface CartDAO {

    boolean addItemToCart(int user_id, int product_id);
    
    // boolean updateItemQuantity(int cartId, int quantity);
    
    boolean removeItemFromCart(int userId, int productId);

    boolean updateProductCheckStatus(int userId, int productId, boolean isChecked);
    
    List<Cart> getCheckedCartItemsByUserId(int userId);

    List<Cart> getCartItemsByUserId(int userId);

    float checkedCartTotalPrice(int userId);
}
