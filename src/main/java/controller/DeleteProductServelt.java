package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CartDAOImpl;
import dao.ProductDAOImpl;
import dao.WishlistDAOImpl;

@WebServlet("/deleteProduct")
public class DeleteProductServelt extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("user_id") == null) {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("{\"success\": false, \"message\": \"You are not logged in. Please login to delete products.\", \"type\": \"error\"}");
            return;
        }
        String strpid = request.getParameter("productId");

        if (strpid != null && !strpid.isEmpty()) {
            int uid = (int) session.getAttribute("user_id");
            int pid = Integer.parseInt(strpid);
            ProductDAOImpl productDAO = new ProductDAOImpl();
            boolean isDeleted = productDAO.deleteProduct(pid);

            if (isDeleted) {
                CartDAOImpl cartDAO = new CartDAOImpl();
                WishlistDAOImpl wishlistDAO = new WishlistDAOImpl();

                int cartCount = cartDAO.getCartItemsByUserId(uid).size();
                session.setAttribute("cart_count", cartCount);

                int checkedCartCount = cartDAO.getCheckedCartItemsByUserId(uid).size();
                session.setAttribute("checked_cart_count", checkedCartCount);

                int wishlistCount = wishlistDAO.getWishlistItemsByUserId(uid).size();
                session.setAttribute("wishlist_count", wishlistCount);
                response.setContentType("application/json");
                response.getWriter().write("{\"success\": true, \"message\": \"Product deleted successfully.\", \"type\": \"success\"}");
            
            } else {
                response.setContentType("application/json");
                response.getWriter().write("{\"success\": false, \"message\": \"Failed to delete product.\", \"type\": \"error\"}");
            }
        } else {
            response.setContentType("application/json");
            response.getWriter().write("{\"success\": false, \"message\": \"Invalid product.\", \"type\": \"error\"}");
        }
    }
}
