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
            String checkCartQuery = "SELECT COUNT(*) FROM cart WHERE user_id = ? AND product_id = ?";
            try (PreparedStatement checkCartPs = connection.prepareStatement(checkCartQuery)) {
                checkCartPs.setInt(1, user_id);
                checkCartPs.setInt(2, product_id);
                ResultSet rs = checkCartPs.executeQuery();
                if (rs.next() && rs.getInt(1) > 0) {
                    return false;
                }
            }
            String checkSavelaterQuery = "SELECT COUNT(*) FROM savelater WHERE user_id = ? AND product_id = ?";
            try (PreparedStatement checkPs = connection.prepareStatement(checkSavelaterQuery)) {
                checkPs.setInt(1, user_id);
                checkPs.setInt(2, product_id);
                ResultSet rs = checkPs.executeQuery();
                if (rs.next() && rs.getInt(1) > 0) {
                    String deleteSavelaterQuery = "DELETE FROM savelater WHERE user_id = ? AND product_id = ?";
                    try (PreparedStatement deleteSavelaterPs = connection.prepareStatement(deleteSavelaterQuery)) {
                        deleteSavelaterPs.setInt(1, user_id);
                        deleteSavelaterPs.setInt(2, product_id);
                        deleteSavelaterPs.executeUpdate();
                    }
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
    // String query = "UPDATE cart SET quantity = ? WHERE cart_id = ?";

    // try (Connection connection = DriverManager.getConnection(URL, USER,
    // PASSWORD);
    // PreparedStatement ps = connection.prepareStatement(query)) {

    // ps.setInt(1, quantity);
    // ps.setInt(2, cartId);

    // int rowsAffected = ps.executeUpdate();
    // return rowsAffected > 0;
    // } catch (SQLException e) {
    // e.printStackTrace();
    // }
    // return false;
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
                boolean isChecked = rs.getBoolean("is_checked");

                Cart cart = new Cart(userId, productId, addedDate, isChecked);
                cartItems.add(cart);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cartItems;
    }

    @Override
    public List<Cart> getCheckedCartItemsByUserId(int userId) {
        List<Cart> cartItems = new ArrayList<>();
        String query = "SELECT * FROM cart WHERE user_id = ? AND is_checked = 1";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int productId = rs.getInt("product_id");
                Timestamp addedDate = rs.getTimestamp("added_at");
                boolean isChecked = rs.getBoolean("is_checked");

                Cart cart = new Cart(userId, productId, addedDate, isChecked);
                cartItems.add(cart);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cartItems;
    }

    @Override
    public boolean updateProductCheckStatus(int userId, int productId, boolean isChecked) {
        String query = "UPDATE cart SET is_checked = ? WHERE user_id = ? AND product_id = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setBoolean(1, isChecked);
            ps.setInt(2, userId);
            ps.setInt(3, productId);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }    

    @Override
    public float checkedCartTotalPrice(int userId){
        String query = "SELECT SUM(p.product_price) AS total_price\n" + 
                        "FROM cart c\n" +
                        "JOIN product p ON c.product_id = p.product_id\n" + 
                        "WHERE c.user_id = ? AND c.is_checked = 1";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return rs.getFloat("total_price");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    
}