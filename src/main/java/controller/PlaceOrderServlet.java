package controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CartDAOImpl;
import dao.NotificationDAOImpl;
import dao.OrderDAOImpl;
import dao.ProductDAOImpl;
import model.Cart;
import model.Notification;
import model.Order;
import model.Product;

@WebServlet({"/placeOrder","/placeOrders"})
public class PlaceOrderServlet extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user_id") == null) {
            response.getWriter().write("{\"success\": false, \"message\": \"You are not logged in. Please login to buy product.\", \"type\": \"error\"}");
            return;
        }
        int uid = (int) session.getAttribute("user_id");
        String action = request.getServletPath();
        switch (action){
            case("/placeOrder"):{
                String productIdParam = request.getParameter("productId");
                if (productIdParam == null || productIdParam.isEmpty()) {
                    response.getWriter().write("{\"success\": false, \"message\": \"Product ID is missing.\", \"type\": \"error\"}");
                    return;
                }
                int product_id = Integer.parseInt(productIdParam);
                ProductDAOImpl productDAO = new ProductDAOImpl();
                Product product = productDAO.getProductById(product_id);
                double order_amount = product.getProductPrice();
                int sellerUID = product.getUserId();
                
                String addressIdParam = request.getParameter("addressId");
                if (addressIdParam == null || addressIdParam.isEmpty()) {
                    response.getWriter().write("{\"success\": false, \"message\": \"Address ID is missing.\", \"type\": \"error\"}");
                    return;
                }
                int address_id = Integer.parseInt(addressIdParam);
                
                Order order = new Order(uid, product_id, address_id, order_amount, "pending", 1);
                OrderDAOImpl orderDAO = new OrderDAOImpl();
                
                try {
                    // boolean isOrdered = orderDAO.placeOrder(order);
                    int orderId = orderDAO.placeOrder(order);
                    if (orderId>0) {
                        Notification userNotification = new Notification(uid, orderId, "Order Update", "Your order has been placed successfully (Order ID: " + orderId + ").", "Unread", LocalDateTime.now());
                        Notification sellerNotification = new Notification(sellerUID, orderId, "New Order", "You have a new order (Order ID: " + orderId + ").", "Unread", LocalDateTime.now());
                        NotificationDAOImpl notificationDAO = new NotificationDAOImpl();
                        notificationDAO.addNotification(userNotification);
                        notificationDAO.addNotification(sellerNotification);

                        int notificationCount = notificationDAO.getNotificationsByUserId(uid).size();
                        session.setAttribute("notification_count", notificationCount);
                        int orderCount = orderDAO.getOrderByUserId(uid).size();
                        session.setAttribute("order_count", orderCount);

                        response.getWriter().write("{\"success\": true, \"message\": \"Product ordered successfully!\", \"type\": \"success\", \"order_count\": " + orderCount + "}");
                    } else {
                        response.getWriter().write("{\"success\": false, \"message\": \"Failed to place order. Try again later.\", \"type\": \"error\"}");
                    }
                } catch (IOException e) {
                    response.getWriter().write("{\"success\": false, \"message\": \"An exception occurred: " + e.getMessage() + "\", \"type\": \"error\"}");
                }
                break;
            } 
            case("/placeOrders"):{
                CartDAOImpl cartDAO = new CartDAOImpl();
                List<Cart> checkedCartItems = cartDAO.getCheckedCartItemsByUserId(uid);
                String addressIdParam = request.getParameter("addressId");
                if (addressIdParam == null || addressIdParam.isEmpty()) {
                    response.getWriter().write("{\"success\": false, \"message\": \"Address ID is missing.\", \"type\": \"error\"}");
                    return;
                }
                int address_id = Integer.parseInt(addressIdParam);
                try {
                    for (Cart checkedCartItem : checkedCartItems) {
                        int product_id = checkedCartItem.getProductId();
                        ProductDAOImpl productDAO = new ProductDAOImpl();
                        Product product = productDAO.getProductById(product_id);
                        double order_amount = product.getProductPrice();
                        Order order = new Order(uid, product_id, address_id, order_amount, "pending", 1);
                        OrderDAOImpl orderDAO = new OrderDAOImpl();
                        // boolean isOrdered = orderDAO.placeOrder(order);
                        int orderId = orderDAO.placeOrder(order);
                        
                        // if (!isOrdered) {
                            // response.getWriter().write("{\"success\": false, \"message\": \"Failed to order "+product.getProductName()+"\", \"type\": \"error\"}");
                        //     return;
                        // }
                        if (orderId>0) {
                            Notification userNotification = new Notification(uid, orderId, "Order Update", "Your order has been placed successfully.", "Unread", LocalDateTime.now());
                            int sellerUID = product.getUserId();
                            Notification sellerNotification = new Notification(sellerUID, orderId, "New Order", "You have a new order (Order ID: " + orderId + ").", "Unread", LocalDateTime.now());
    
                            NotificationDAOImpl notificationDAO = new NotificationDAOImpl();
                            notificationDAO.addNotification(userNotification);
                            notificationDAO.addNotification(sellerNotification);
                        } else {
                            response.getWriter().write("{\"success\": false, \"message\": \"Failed to order "+product.getProductName()+"\", \"type\": \"error\"}");
                            return;
                        }
                    }
                    NotificationDAOImpl notificationDAO = new NotificationDAOImpl();
                    int notificationCount = notificationDAO.getNotificationsByUserId(uid).size();
                    session.setAttribute("notification_count", notificationCount);
                    OrderDAOImpl orderDAO = new OrderDAOImpl();
                    int orderCount = orderDAO.getOrderByUserId(uid).size();
                    session.setAttribute("order_count", orderCount);
                    response.getWriter().write("{\"success\": true, \"message\": \"Products ordered successfully!\", \"type\": \"success\", \"order_count\": " + orderCount + "}");
                } catch (IOException e) {
                    response.getWriter().write("{\"success\": false, \"message\": \"An exception occurred: " + e.getMessage() + "\", \"type\": \"error\"}");
                }
            }
            break;
        }
    }
}
