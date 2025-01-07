package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import model.Cart;

public class CartDAOImpl implements CartDAO {

    private static final String URL = "jdbc:mysql://localhost:3306/project";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    // Add item to the cart
    @Override
    public boolean addItemToCart(int user_id, int product_id) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String checkQuery = "SELECT COUNT(*) FROM cart WHERE user_id = ? AND product_id = ?";
            try (PreparedStatement checkPs = connection.prepareStatement(checkQuery)) {
                checkPs.setInt(1, user_id);
                checkPs.setInt(2, product_id);
                ResultSet rs = checkPs.executeQuery();
                if (rs.next() && rs.getInt(1)>0) {
                    return false;
                }
            }
            String insertQuery = "INSERT INTO cart (user_id, product_id) VALUES (?, ?)";
            try (PreparedStatement insertPs = connection.prepareStatement(insertQuery)) {
                insertPs.setInt(1, user_id);
                insertPs.setInt(2, product_id);
                return insertPs.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Update the quantity of an item in the cart
    // @Override
    // public boolean updateItemQuantity(int cartId, int quantity) {
    //     String query = "UPDATE cart SET quantity = ? WHERE cart_id = ?";
        
    //     try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
    //          PreparedStatement ps = connection.prepareStatement(query)) {

    //         ps.setInt(1, quantity);
    //         ps.setInt(2, cartId);

    //         int rowsAffected = ps.executeUpdate();
    //         return rowsAffected > 0;
    //     } catch (SQLException e) {
    //         e.printStackTrace();
    //     }
    //     return false;
    // }

    // Remove item from the cart by cartId
    @Override
    public boolean removeItemFromCart(int userId, int productId) {
        String query = "DELETE FROM cart WHERE user_id = ? AND product_id = ?";
        
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, userId);
            ps.setInt(2, productId);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Get all cart items for a user
    @Override
    public List<Cart> getCartItemsByUserId(int userId) {
        List<Cart> cartItems = new ArrayList<>();
        String query = "SELECT * FROM cart WHERE user_id = ?";
        
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int productId = rs.getInt("product_id");
                Timestamp addedDate = rs.getTimestamp("added_at");

                Cart cart = new Cart(userId, productId, addedDate);
                cartItems.add(cart);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cartItems;
    }
}
