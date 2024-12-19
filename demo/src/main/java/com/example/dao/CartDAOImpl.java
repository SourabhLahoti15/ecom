package com.example.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class CartDAOImpl implements CartDAO {

    private static final String URL = "jdbc:mysql://localhost:3306/project";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    // Add item to the cart
    @Override
    public boolean addItemToCart(Cart cart) {
        String query = "INSERT INTO cart (user_id, product_id, quantity, added_date) VALUES (?, ?, ?, ?)";
        
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, cart.getUserId());
            ps.setInt(2, cart.getProductId());
            ps.setInt(3, cart.getQuantity());
            ps.setTimestamp(4, cart.getAddedDate());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Update the quantity of an item in the cart
    @Override
    public boolean updateItemQuantity(int cartId, int quantity) {
        String query = "UPDATE cart SET quantity = ? WHERE cart_id = ?";
        
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, quantity);
            ps.setInt(2, cartId);

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Remove item from the cart by cartId
    @Override
    public boolean removeItemFromCart(int cartId) {
        String query = "DELETE FROM cart WHERE cart_id = ?";
        
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, cartId);
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
                int cartId = rs.getInt("cart_id");
                int productId = rs.getInt("product_id");
                int quantity = rs.getInt("quantity");
                Timestamp addedDate = rs.getTimestamp("added_date");

                Cart cart = new Cart(cartId, userId, productId, quantity, addedDate);
                cartItems.add(cart);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cartItems;
    }

    // Get a specific cart item by cartId
    @Override
    public Cart getCartItemById(int cartId) {
        Cart cart = null;
        String query = "SELECT * FROM cart WHERE cart_id = ?";
        
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, cartId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int userId = rs.getInt("user_id");
                int productId = rs.getInt("product_id");
                int quantity = rs.getInt("quantity");
                Timestamp addedDate = rs.getTimestamp("added_date");

                cart = new Cart(cartId, userId, productId, quantity, addedDate);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cart;
    }
}
