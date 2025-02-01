package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import model.Order;

public class OrderDAOImpl implements OrderDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/project";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    @Override
    public int placeOrder(Order order) {
        String query = "INSERT INTO orders (user_id, product_id, address_id, order_amount, order_status, quantity) VALUES (?, ?, ?, ?, ?, ?)";
        int orderId = -1;

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            // ps.setInt(1, order.getOrder_id());
            ps.setInt(1, order.getUserId());
            ps.setInt(2, order.getProductId());
            ps.setInt(3,order.getAddressId());
            ps.setDouble(4, order.getorderAmount());
            ps.setString(5, order.getOrder_status());
            ps.setInt(6, order.getQuantity());
            // ps.setTimestamp(5, order.getOrdered_at());

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                orderId = generatedKeys.getInt(1);
            }
        }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderId;
    }
    @Override
    public boolean updateOrder(Order order) {
        String query = "UPDATE orders SET user_id = ?,address_id = ?, order_amount = ?, order_status = ?, quantity = ?, ordered_at = ? WHERE order_id = ?";
        
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, order.getUserId());
            ps.setInt(2, order.getAddressId());
            ps.setDouble(3, order.getorderAmount());
            ps.setString(4, order.getOrder_status());
            ps.setInt(5, order.getQuantity());
            ps.setTimestamp(6, order.getOrderedAt());
            ps.setInt(7, order.getOrderId());

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
    public List<Order> getOrderByUserId(int userId) {
        List<Order> orders = new ArrayList<>();
        String query = "SELECT * FROM orders WHERE user_id = ?";
        
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int orderId = rs.getInt("order_id");
                int productId = rs.getInt("product_id");
                int addressId = rs.getInt("address_id");
                double orderAmount = rs.getDouble("order_amount");
                String orderStatus = rs.getString("order_status");
                int quantity = rs.getInt("quantity");
                Timestamp orderDate = rs.getTimestamp("ordered_at");

                Order order = new Order(orderId, userId, productId, addressId, orderAmount, orderStatus, quantity, orderDate);
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
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
                int productId = rs.getInt("product_id");
                int addressId = rs.getInt("address_id");
                double orderAmount = rs.getDouble("order_amount");
                String orderStatus = rs.getString("order_status");
                int quantity = rs.getInt("quantity");
                Timestamp orderDate = rs.getTimestamp("ordered_at");

                Order order = new Order(orderId, userId, productId, addressId, orderAmount, orderStatus, quantity, orderDate);
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
                int productId = rs.getInt("product_id");
                int addressId = rs.getInt("address_id");
                double orderAmount = rs.getDouble("order_amount");
                String orderStatus = rs.getString("order_status");
                int quantity = rs.getInt("quantity");
                Timestamp orderDate = rs.getTimestamp("ordered_at");

                order = new Order(orderId, userId, productId, addressId, orderAmount, orderStatus, quantity, orderDate);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;
    }
}
