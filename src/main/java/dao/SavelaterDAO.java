package dao;

import java.util.List;

import model.Savelater;

public interface SavelaterDAO {

    boolean addItemToSavelater(int user_id, int product_id);
    
    // boolean updateItemQuantity(int cartId, int quantity);
    
    boolean removeItemFromSavelater(int userId, int productId);
    
    List<Savelater> getSavelaterItemsByUserId(int userId);
}
