package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import model.Wishlist;

public class WishlistDAOImpl implements WishlistDAO {

    private static final String URL = "jdbc:mysql://localhost:3306/project";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    // Add product to wishlist
    public boolean addItemToWishlist(int user_id, int product_id) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String checkQuery = "SELECT COUNT(*) FROM wishlist WHERE user_id = ? AND product_id = ?";
            try (PreparedStatement checkPs = connection.prepareStatement(checkQuery)) {
                checkPs.setInt(1, user_id);
                checkPs.setInt(2, product_id);
                ResultSet rs = checkPs.executeQuery();
                if (rs.next() && rs.getInt(1)>0) {
                    return false;
                }
            }
    
            String insertQuery = "INSERT INTO wishlist (user_id, product_id) VALUES (?, ?)";
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

    // Get wishlist by user
    public List<Wishlist> getWishlistItemsByUserId(int userId) {
        List<Wishlist> wishlistItems = new ArrayList<>();
        String query = "SELECT * FROM wishlist WHERE user_id = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int productId = rs.getInt("product_id");
                Timestamp addedDate = rs.getTimestamp("added_at");

                Wishlist wishlist = new Wishlist(userId, productId, addedDate);
                wishlistItems.add(wishlist);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return wishlistItems;
    }

    // Remove product from wishlist
    public boolean removeItemFromWishlist(int userId, int productId) {
        String query = "DELETE FROM wishlist WHERE user_id = ? AND product_id = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, userId);
            ps.setInt(2, productId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
