package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.OrderDAOImpl;
import dao.ProductDAOImpl;
import model.Order;
import model.Product;

@WebServlet("/placeOrder")
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
        String productIdParam = request.getParameter("productId");
        if (productIdParam == null || productIdParam.isEmpty()) {
            response.getWriter().write("{\"success\": false, \"message\": \"Product ID is missing.\", \"type\": \"error\"}");
            return;
        }
        int product_id = Integer.parseInt(productIdParam);
        ProductDAOImpl productDAO = new ProductDAOImpl();
        Product product = productDAO.getProductById(product_id);
        double order_amount = product.getProductPrice();
        
        String addressIdParam = request.getParameter("addressId");
        if (addressIdParam == null || addressIdParam.isEmpty()) {
            response.getWriter().write("{\"success\": false, \"message\": \"Address ID is missing.\", \"type\": \"error\"}");
            return;
        }
        int address_id = Integer.parseInt(addressIdParam);
        
        Order order = new Order(uid, product_id, address_id, order_amount, "ordered", 1);
        OrderDAOImpl orderDAO = new OrderDAOImpl();
        
        try {
            boolean isOrdered = orderDAO.placeOrder(order);
            if (isOrdered) {
                int orderCount = orderDAO.getOrderByUserId(uid).size();
                session.setAttribute("order_count", orderCount);
                response.getWriter().write("{\"success\": true, \"message\": \"Product ordered successfully!\", \"type\": \"success\", \"order_count\": " + orderCount + "}");
            } else {
                response.getWriter().write("{\"success\": false, \"message\": \"Failed to place order. Try again later.\", \"type\": \"error\"}");
            }
        } catch (Exception e) {
            response.getWriter().write("{\"success\": false, \"message\": \"An exception occurred: " + e.getMessage() + "\", type\": \"error\"}");
        }
    }
}
