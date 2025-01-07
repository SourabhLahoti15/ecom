package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.User;

public class UserDAOImpl implements UserDAO {

    private static final String URL = "jdbc:mysql://localhost:3306/project";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    @Override
    public boolean addUser(User user) {
        String query = "INSERT INTO user(user_name, gender, email, password, phone, dob) VALUES(?, ?, ?, ?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);) {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, user.getName());
            ps.setString(2, user.getGender());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPassword());
            ps.setLong(5, user.getPhone());
            ps.setString(6, user.getDob());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public User getUserbyId(int user_id) {
        String query = "SELECT * FROM user WHERE user_id= ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, user_id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new User(
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getLong(6),
                        rs.getString(7));
            } else {
                System.out.println("No user found with user_id: " + user_id);
                return null;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<User> getAllUsers() {
        String query = "SELECT * FROM user";
        List<User> users = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User(
                        rs.getInt("user_id"),
                        rs.getString("user_name"),
                        rs.getString("gender"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getInt("phone"),
                        rs.getString("dob")
                        );
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public boolean updateUser(User user) {
        String query = "UPDATE user SET user_name=?, gender=?, email=?, password=?, phone=? WHERE user_id=?";
        boolean isUpdated = false;
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, user.getName());
            ps.setString(2, user.getGender());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPassword());
            ps.setLong(5, user.getPhone());
            ps.setInt(6, user.getUserId());
            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                isUpdated = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isUpdated;
    }
    
    @Override
    public boolean deleteUser(int user_id) {
        String query = "DELETE FROM user WHERE user_id = ?";
        boolean isDeleted = false;
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, user_id);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                isDeleted = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isDeleted;
    }

    public User loginCheck(String email, String password) {
        User user = null;
        String query = "SELECT * FROM user WHERE email = ? AND password = ?";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                user = new User(
                        rs.getInt("user_id"),
                        rs.getString("user_name"),
                        rs.getString("gender"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getLong("phone"),
                        rs.getString("dob"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
