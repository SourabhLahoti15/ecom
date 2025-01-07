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
import dao.UserDAOImpl;
import dao.WishlistDAOImpl;
import model.User;

@WebServlet("/addUser")
public class AddUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        String name = request.getParameter("name");
        String gender = request.getParameter("gender");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        long phone = Long.parseLong(request.getParameter("phone"));
        String dob = request.getParameter("dob");
        // Date dob = null;
        // try {
        // String dobStr = request.getParameter("dob");
        // SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
        // dob = sdf.parse(dobStr);
        // } catch (java.text.ParseException e) {
        // e.printStackTrace();
        // throw new ServletException("Invalid date format for DOB", e);
        // }

        User user = new User(name, gender, email, password, phone, dob);
        UserDAOImpl userDAO = new UserDAOImpl();
        CartDAOImpl cartDAO = new CartDAOImpl();
        WishlistDAOImpl wishlistDAO = new WishlistDAOImpl();
        OrderDAOImpl orderDAO = new OrderDAOImpl();
        try {
            boolean isAdded = userDAO.addUser(user);
            if (isAdded) {
                response.sendRedirect("/gu/home");
                response.getWriter().write("{\"success\": true, \"message\": \"Signed in successfully!\", \"type\": \"success\"}");
                HttpSession session = request.getSession();
                session.setAttribute("user_id", user.getUserId());
                session.setAttribute("user_name", user.getName());

                int cartCount = cartDAO.getCartItemsByUserId(user.getUserId()).size();
                session.setAttribute("cart_count", cartCount);

                int wishlistCount = wishlistDAO.getWishlistItemsByUserId(user.getUserId()).size();
                session.setAttribute("wishlist_count", wishlistCount);

                int orderCount = orderDAO.getOrderByUserId(user.getUserId()).size();
                session.setAttribute("order_count", orderCount);
            } else {
                response.getWriter().write("{\"success\": false, \"message\": \"Failed to sign in.\", \"type\": \"error\"}");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("{\"success\": false, \"message\": \"Exception occured: " + e.getMessage() + "\", \"type\": \"error\"}");
        }
    }
}
