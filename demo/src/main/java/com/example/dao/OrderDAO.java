package com.example.dao;

import java.util.List;

public interface OrderDAO {

    boolean addOrder(Order order);
    
    boolean updateOrder(Order order);
    
    boolean deleteOrder(int orderId);
    
    List<Order> getAllOrders();
    
    Order getOrderById(int orderId);
}
