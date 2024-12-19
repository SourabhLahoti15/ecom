package com.example.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/project";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    @Override
    public boolean addOrder(Order order) {
        String query = "INSERT INTO orders (order_id, user_id, order_amount, order_status, quantity, ordered_at) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, order.getOrder_id());
            ps.setInt(2, order.getUser_id());
            ps.setDouble(3, order.getorder_amount());
            ps.setString(4, order.getOrder_status());
            ps.setInt(5, order.getQuantity());
            ps.setTimestamp(6, order.getOrdered_at());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    @Override
    public boolean updateOrder(Order order) {
        String query = "UPDATE orders SET user_id = ?, order_amount = ?, order_status = ?, quantity = ?, ordered_at = ? WHERE order_id = ?";
        
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, order.getUser_id());
            ps.setDouble(2, order.getorder_amount());
            ps.setString(3, order.getOrder_status());
            ps.setInt(4, order.getQuantity());
            ps.setTimestamp(5, order.getOrdered_at());
            ps.setInt(6, order.getOrder_id());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    @Override
    public boolean deleteOrder(int orderId) {
        String query = "DELETE FROM orders WHERE order_id = ?";
        
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, orderId);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    @Override
    public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();
        String query = "SELECT * FROM orders";
        
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery(query)) {

            while (rs.next()) {
                int orderId = rs.getInt("order_id");
                int userId = rs.getInt("user_id");
                double orderAmount = rs.getDouble("order_amount");
                String orderStatus = rs.getString("order_status");
                int quantity = rs.getInt("quantity");
                Timestamp orderDate = rs.getTimestamp("ordered_at");

                Order order = new Order(orderId, userId, orderAmount, orderStatus, quantity, orderDate);
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }
    @Override
    public Order getOrderById(int orderId) {
        Order order = null;
        String query = "SELECT * FROM orders WHERE order_id = ?";
        
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, orderId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int userId = rs.getInt("user_id");
                double orderAmount = rs.getDouble("order_amount");
                String orderStatus = rs.getString("order_status");
                int quantity = rs.getInt("quantity");
                Timestamp orderDate = rs.getTimestamp("ordered_at");

                order = new Order(orderId, userId, orderAmount, orderStatus, quantity, orderDate);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;
    }
}
