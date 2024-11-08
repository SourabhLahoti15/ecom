package com.example.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class daoImpl implements dao{

    private static final String URL = "jdbc:mysql://localhost:3306/project";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    @Override
    public void addUser(User user) {
        String query = "INSERT INTO user(user_id, user_name,email,password,address) VALUES(?, ?, ?, ?, ?)";
        try(Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);)
        {
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
            return new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    // @Override
    public Object getAllUsers() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void updateUser(User user) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void deleteUser(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
