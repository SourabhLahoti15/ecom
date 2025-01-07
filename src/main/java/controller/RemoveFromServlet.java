package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CartDAOImpl;
import dao.OrderDAOImpl;
import dao.WishlistDAOImpl;

@WebServlet({"/removeFromCart", "/removeFromWishlist", "/removeFromOrders"})
public class RemoveFromServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        
        HttpSession session = request.getSession();

        int userId = (int) session.getAttribute("user_id");
        String action = request.getServletPath();
        
        if("/removeFromCart".equals(action)){
            int productId = Integer.parseInt(request.getParameter("productId"));
            CartDAOImpl cartDAO = new CartDAOImpl();
            boolean isRemoved = cartDAO.removeItemFromCart(userId, productId);
            if(isRemoved){
                int cartCount = cartDAO.getCartItemsByUserId(userId).size();
                session.setAttribute("cart_count", cartCount);
                response.getWriter().write("{\"success\": true, \"message\": \"Product removed from cart.\", \"type\": \"success\", \"cart_count\": " + cartCount + "}");
            } else{
                response.getWriter().write("{\"success\": false, \"message\": \"Failed to remove product from cart.\", \"type\": \"error\"}");
            }
        } else if("/removeFromWishlist".equals(action)){
            int productId = Integer.parseInt(request.getParameter("productId"));
            WishlistDAOImpl wishlistDAO = new WishlistDAOImpl();
            boolean isRemoved = wishlistDAO.removeItemFromWishlist(userId, productId);
            if (isRemoved) {
                int wishlistCount = wishlistDAO.getWishlistItemsByUserId(userId).size();
                session.setAttribute("wishlist_count", wishlistCount);
                response.getWriter().write("{\"success\": true, \"message\": \"Product removed from wishlist.\", \"type\": \"success\", \"wishlist_count\": "+ wishlistCount + "}");
            } else {
                response.getWriter().write("{\"success\": false, \"message\": \"Failed to remove product from wishlist.\", \"type\": \"error\"}");
            }
        } else if("/removeFromOrders".equals(action)){
            OrderDAOImpl orderDAO = new OrderDAOImpl();
            int orderId = Integer.parseInt(request.getParameter("orderId"));
            boolean isCancelled = orderDAO.deleteOrder(orderId);
            if (isCancelled) {
                int orderCount = orderDAO.getOrderByUserId(userId).size();
                session.setAttribute("order_count", orderCount);
                response.getWriter().write("{\"success\": true, \"message\": \"Order cancelled successfully!\", \"type\": \"success\", \"order_count\": " + orderCount + "}");
            } else {
                response.getWriter().write("{\"success\": false, \"message\": \"Failed to remove product from wishlist.\", \"type\": \"error\"}");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}