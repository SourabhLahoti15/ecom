package dao;

import java.util.List;

import model.Order;

public interface OrderDAO {

    boolean placeOrder(Order order);
    
    boolean updateOrder(Order order);
    
    boolean deleteOrder(int orderId);

    List<Order> getOrderByUserId(int userId);
    
    List<Order> getAllOrders();
    
    Order getOrderById(int orderId);
}
