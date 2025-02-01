package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AddressDAOImpl;
import dao.CartDAOImpl;
import dao.NotificationDAOImpl;
import dao.OrderDAOImpl;
import dao.UserDAOImpl;
import dao.WishlistDAOImpl;
import model.Address;
import model.User;

@WebServlet({ "/signin", "/login", "/loginCheck", "/logout" })
public class AuthServelt extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        RequestDispatcher rd;
        switch (path) {
            case "/signin":
                rd = request.getRequestDispatcher("signin.jsp");
                rd.forward(request, response);
                break;
            case "/login":
                rd = request.getRequestDispatcher("login.jsp");
                rd.forward(request, response);
                break;
            case "/loginCheck":
                String email = request.getParameter("email");
                String password = request.getParameter("password");
                NotificationDAOImpl notificationDAO = new NotificationDAOImpl();
                UserDAOImpl userDAO = new UserDAOImpl();
                CartDAOImpl cartDAO = new CartDAOImpl();
                WishlistDAOImpl wishlistDAO = new WishlistDAOImpl();
                OrderDAOImpl orderDAO = new OrderDAOImpl();
                AddressDAOImpl addressDAO = new AddressDAOImpl();
                User user = userDAO.loginCheck(email, password);
                if (user != null) {
                    HttpSession session = request.getSession();
                    session.setAttribute("user_id", user.getUserId());
                    session.setAttribute("user_name", user.getName());

                    int notificationCount = notificationDAO.getNotificationsByUserId(user.getUserId()).size();
                    session.setAttribute("notification_count", notificationCount);

                    int cartCount = cartDAO.getCartItemsByUserId(user.getUserId()).size();
                    session.setAttribute("cart_count", cartCount);

                    int checkedCartCount = cartDAO.getCheckedCartItemsByUserId(user.getUserId()).size();
                    session.setAttribute("checked_cart_count", checkedCartCount);

                    int wishlistCount = wishlistDAO.getWishlistItemsByUserId(user.getUserId()).size();
                    session.setAttribute("wishlist_count", wishlistCount);
                    
                    int orderCount = orderDAO.getOrderByUserId(user.getUserId()).size();
                    session.setAttribute("order_count", orderCount);

                    List<Address> addresses = addressDAO.getAddressesByUserId(user.getUserId());
                    session.setAttribute("addresses", addresses);

                    response.sendRedirect("home");
                } else {
                    response.getWriter().write("Invalid email or password.");
                }
                break;
            case "/logout":
                HttpSession session = request.getSession();
                session.invalidate();
                response.sendRedirect("/gu/home");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
