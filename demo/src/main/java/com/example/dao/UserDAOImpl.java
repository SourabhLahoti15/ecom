package com.example.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO{

    private static final String URL = "jdbc:mysql://localhost:3306/project";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    @Override
    public void addUser(User user) {
        String query = "INSERT INTO user(user_id, user_name, gender,email,password,address) VALUES(?, ?, ?, ?, ?, ?)";
        try(Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);) {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, user.getUser_id()); 
            ps.setString(2, user.getUser_name()); 
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPassword());
            ps.setString(5, user.getAddress());
            ps.executeUpdate();
            System.out.println("user added.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public User getUserbyId(int user_id) {
        String query = "SELECT * FROM user WHERE user_id= '?'";
        try(Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)){
            PreparedStatement ps = connection.prepareStatement(query); 
            ps.setInt(1, user_id);
            ResultSet rs = ps.executeQuery();
            return new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<User> getAllUsers() {
        String query = "SELECT * FROM user";
        List<User> users = new ArrayList<>();
        try(Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)){
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                User user = new User(rs.getInt("user_id"),
                rs.getString("user_name"),
                rs.getString("gender"),
                rs.getString("email"),
                rs.getString("password"),
                rs.getString("address"),
                rs.getInt("phone")
                );
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public boolean updateUser(User user) {
        String query = "UPDATE user SET user_name=?, gender=?, email=?, password=?, address=?, phone=? WHERE user_id=?";
        boolean isUpdated = false;
        try(Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)){
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1,user.getUser_name());
            ps.setString(2,user.getGender());
            ps.setString(3,user.getEmail());
            ps.setString(4,user.getPassword());
            ps.setString(5,user.getAddress());
            ps.setInt(6,user.getPhone());
            ps.setInt(7,user.getUser_id());
            int rowsAffected = ps.executeUpdate();  

            if (rowsAffected > 0) {
                isUpdated = true;  
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        return isUpdated;
    }
    
    @Override
    public boolean deleteUser(int user_id) {
        String query = "DELETE FROM user WHERE id = ?";
        boolean isDeleted = false;
        try(Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)){
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1,user_id);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected>0){
                isDeleted=true;
            }            
        } catch (SQLException e){
            e.printStackTrace();
        }
        return isDeleted;
    }    
}
