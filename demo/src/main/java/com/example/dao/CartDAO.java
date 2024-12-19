package com.example.dao;

import java.util.List;

public interface CartDAO {

    boolean addItemToCart(Cart cart);
    
    boolean updateItemQuantity(int cartId, int quantity);
    
    boolean removeItemFromCart(int cartId);
    
    List<Cart> getCartItemsByUserId(int userId);
    
    Cart getCartItemById(int cartId);
}
