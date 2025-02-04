package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import dao.ProductDAOImpl;
import dao.SavelaterDAOImpl;
import dao.WishlistDAOImpl;
import model.Address;
import model.Cart;
import model.Notification;
import model.Order;
import model.Product;
import model.Savelater;
import model.Wishlist;

@WebServlet({ "/home", "/profile", "/notification", "/cart", "/wishlist", "/orders", "/become-seller", "/product", "/sidebar" })
public class PageServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;  

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        int uid;
        RequestDispatcher rd;
        String path = request.getServletPath();
        switch (path) {
            case "/home":{
                ProductDAOImpl productDAO = new ProductDAOImpl();
                List<Product> allProducts = productDAO.getAllProducts();
                request.setAttribute("allProducts", allProducts);
                rd = request.getRequestDispatcher("home.jsp");
                rd.forward(request, response);
                break;
            }
            case "/profile":{
                request.getRequestDispatcher("profile.jsp").forward(request, response);
                break;
            }
            case "/notification":{
                session = request.getSession(false);
                if (session == null || session.getAttribute("user_id") == null) {
                    request.getRequestDispatcher("nologinnotification.jsp").forward(request, response);
                    return;
                }
                uid = (int) session.getAttribute("user_id");
                NotificationDAOImpl notificationDAO = new NotificationDAOImpl();
                List<Notification> notifications = notificationDAO.getNotificationsByUserId(uid);
                request.setAttribute("notifications", notifications);
                request.getRequestDispatcher("notification.jsp").forward(request, response);
                break;
            }
            case "/cart":{
                session = request.getSession(false);
                if (session == null || session.getAttribute("user_id") == null) {
                    request.getRequestDispatcher("nologincart.jsp").forward(request, response);
                    // response.sendRedirect("login");
                    // response.getWriter().write("{\"success\": false, \"message\": \"You are not logged in. Please login to view cart products.\", \"type\": \"error\"}");
                    return;
                }
                uid = (int) session.getAttribute("user_id");
                CartDAOImpl cartDAO = new CartDAOImpl();
                ProductDAOImpl productDAO = new ProductDAOImpl();
                float cartCheckedTotalPrice = cartDAO.checkedCartTotalPrice(uid);
                List<Cart> cartItems = cartDAO.getCartItemsByUserId(uid);
                List<Cart> cartCheckedItems = cartDAO.getCheckedCartItemsByUserId(uid);
                Map<Integer, Product> cartProductMap = new HashMap<>();
                for (Cart cartItem : cartItems) {
                    Product product = productDAO.getProductById(cartItem.getProductId());
                    cartProductMap.put(cartItem.getProductId(), product);
                }
                request.setAttribute("cartItems", cartItems);
                request.setAttribute("cartCheckedItems", cartCheckedItems);
                request.setAttribute("cartProductMap", cartProductMap);
                request.setAttribute("cartCheckedTotalPrice", cartCheckedTotalPrice);

                SavelaterDAOImpl savelaterDAO = new SavelaterDAOImpl();
                List<Savelater> savelaterItems = savelaterDAO.getSavelaterItemsByUserId(uid);
                Map<Integer, Product> savelaterProductMap = new HashMap<>();
                int savelaterCount = savelaterItems.size();
                for (Savelater savelaterItem : savelaterItems) {
                    Product product = productDAO.getProductById(savelaterItem.getProductId());
                    savelaterProductMap.put(savelaterItem.getProductId(), product);
                }
                request.setAttribute("savelaterItems", savelaterItems);
                request.setAttribute("savelaterProductMap", savelaterProductMap);
                session.setAttribute("savelater_count", savelaterCount);

                request.getRequestDispatcher("cart.jsp").forward(request, response);
                break;
            }
            case "/wishlist":{
                session = request.getSession(false);
                if (session == null || session.getAttribute("user_id") == null) {
                    request.getRequestDispatcher("nologinwishlist.jsp").forward(request, response);
                    // response.sendRedirect("login");
                    // response.getWriter().write("{\"success\": false, \"message\": \"You are not logged in. Please login to wishlist products.\", \"type\": \"error\"}");
                    return;
                }
                uid = (int) session.getAttribute("user_id");
                WishlistDAOImpl wishlistDAO = new WishlistDAOImpl();
                ProductDAOImpl productDAO = new ProductDAOImpl();
                List<Wishlist> wishlistItems = wishlistDAO.getWishlistItemsByUserId(uid);
                Map<Integer, Product> wishlistProductMap = new HashMap<>();
                for (Wishlist wishlistItem : wishlistItems) {
                    Product product = productDAO.getProductById(wishlistItem.getProductId());
                    wishlistProductMap.put(wishlistItem.getProductId(), product);
                }
                request.setAttribute("wishlistItems", wishlistItems);
                request.setAttribute("productMap", wishlistProductMap);
                request.getRequestDispatcher("wishlist.jsp").forward(request, response);
                break;
            }
            case "/orders":{
                session = request.getSession(false);
                if (session == null || session.getAttribute("user_id") == null) {
                    request.getRequestDispatcher("nologinorders.jsp").forward(request, response);
                    // response.sendRedirect("login");
                    return;
                }
                uid = (int) session.getAttribute("user_id");
                OrderDAOImpl orderDAO = new OrderDAOImpl();
                ProductDAOImpl productDAO = new ProductDAOImpl();
                List<Order> orders = orderDAO.getOrderByUserId(uid);
                Map<Integer, Product> orderProductMap = new HashMap<>();
                for (Order order : orders) {
                    Product product = productDAO.getProductById(order.getProductId());
                    orderProductMap.put(order.getProductId(), product);
                }
                request.setAttribute("orders", orders);
                request.setAttribute("productMap", orderProductMap);
                rd = request.getRequestDispatcher("orders.jsp");
                rd.forward(request, response);
                break;
            }
            case "/become-seller":{
                // System.out.println("Session: " + session); // Debug
                // System.out.println("User ID: " + session.getAttribute("user_id")); // Debug
                if (session != null && session.getAttribute("user_id") != null) {
                    uid = (int) session.getAttribute("user_id");
                    ProductDAOImpl productDAO = new ProductDAOImpl();
                    List<Product> productsByUid = productDAO.getProductByUserId(uid);
                    // System.out.println("Products found: " + productsByUid.size()); // Debug
                    request.setAttribute("productsByUid", productsByUid);
                }
                request.getRequestDispatcher("seller.jsp").forward(request, response);
                break;
            }
            case "/product":{
                ProductDAOImpl productDAO = new ProductDAOImpl();
                int productId = Integer.parseInt(request.getParameter("productId"));
                Product product = productDAO.getProductById(productId);
                request.setAttribute("product", product);
                request.getRequestDispatcher("product.jsp").forward(request, response);
                break;
            }
            case "/sidebar":{
                AddressDAOImpl addressDAO = new AddressDAOImpl();
                List<Address> addresses = addressDAO.getAddressesByUserId((Integer) session.getAttribute("user_id"));
                session.setAttribute("addresses", addresses);
                request.getRequestDispatcher("sidebar.jsp").forward(request, response);
                break;
            }
            default:
                request.getRequestDispatcher("home.jsp").forward(request, response);
        }
    }
}