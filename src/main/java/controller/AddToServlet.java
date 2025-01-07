package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CartDAOImpl;
import dao.WishlistDAOImpl;

@WebServlet({"/addToCart", "/addToWishlist"})
public class AddToServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user_id") == null) {
            response.getWriter().write("{\"success\": false, \"message\": \"You are not logged in. Please login to add product.\", \"type\": \"error\"}");
            return;
        }

        int uid = (int) session.getAttribute("user_id");
        // System.out.println("user_id:"+uid);
        int product_id = Integer.parseInt(request.getParameter("productId"));
        // System.out.println("productId:"+product_id);
        String action = request.getServletPath();
        boolean isAdded;

        if ("/addToCart".equals(action)) {
            CartDAOImpl cartDAO = new CartDAOImpl();
            isAdded = cartDAO.addItemToCart(uid, product_id);
            if (isAdded) {
                int cartCount = cartDAO.getCartItemsByUserId(uid).size();
                session.setAttribute("cart_count", cartCount);
                response.getWriter().write("{\"success\": true, \"message\": \"Product added to cart successfully!\", \"type\": \"success\", \"cart_count\": " + cartCount + "}");
            } else {
                response.getWriter().write("{\"success\": false, \"message\": \"Product is already in cart.\", \"type\": \"warning\"}");
            }
        } else if ("/addToWishlist".equals(action)) {
            WishlistDAOImpl wishlistDAO = new WishlistDAOImpl();
            isAdded = wishlistDAO.addItemToWishlist(uid, product_id);
            if (isAdded) {
                int wishlistCount = wishlistDAO.getWishlistItemsByUserId(uid).size();
                session.setAttribute("wishlist_count", wishlistCount);
                response.getWriter().write("{\"success\": true, \"message\": \"Product added to wishlist successfully!\", \"type\": \"success\", \"wishlist_count\": " + wishlistCount + "}");
            } else {
                response.getWriter().write("{\"success\": false, \"message\": \"Product is already in wishlist.\", \"type\": \"warning\"}");
            }
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}

