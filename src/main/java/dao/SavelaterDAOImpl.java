package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import model.Savelater;

public class SavelaterDAOImpl implements SavelaterDAO {

    private static final String URL = "jdbc:mysql://localhost:3306/project";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    // Add item to the savelater
    @Override
    public boolean addItemToSavelater(int user_id, int product_id) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String query = "INSERT INTO savelater (user_id, product_id) VALUES (?, ?)";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setInt(1, user_id);
                ps.setInt(2, product_id);
                return ps.executeUpdate() > 0;
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
    public boolean removeItemFromSavelater(int userId, int productId) {
        String query = "DELETE FROM savelater WHERE user_id = ? AND product_id = ?";
        
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

    // Get all savelater items for a user
    @Override
    public List<Savelater> getSavelaterItemsByUserId(int userId) {
        List<Savelater> savelaterItems = new ArrayList<>();
        String query = "SELECT * FROM savelater WHERE user_id = ?";
        
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int productId = rs.getInt("product_id");
                Timestamp addedDate = rs.getTimestamp("added_at");

                Savelater savelater = new Savelater(userId, productId, addedDate);
                savelaterItems.add(savelater);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return savelaterItems;
    }
}
