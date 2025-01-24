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
import dao.ProductDAOImpl;
import model.Address;
import model.Cart;
import model.Product;

@WebServlet("/buynowCart")
public class BuynowCartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("user_id") == null) {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("{\"success\": false, \"message\": \"You are not logged in. Please login to buy cart products.\", \"type\": \"error\"}");
            return;
        }
        try{
            int uid = (int) session.getAttribute("user_id");
            CartDAOImpl cartDAO = new CartDAOImpl();
            ProductDAOImpl productDAO = new ProductDAOImpl();
            float checkedCartTotalPrice = cartDAO.checkedCartTotalPrice(uid);
            List<Cart> checkedCartItems = cartDAO.getCheckedCartItemsByUserId(uid);
            // Map<Integer, Product> checkedCartProductMap = new HashMap<>();
            // for (Cart checkedCartItem : checkedCartItems) {
            //     Product product = productDAO.getProductById(checkedCartItem.getProductId());
            //     checkedCartProductMap.put(checkedCartItem.getProductId(), product);
            // }
            List<Cart> cartItems = cartDAO.getCartItemsByUserId(uid);
            Map<Integer, Product> cartProductMap = new HashMap<>();
            for (Cart cartItem : cartItems) {
                Product product = productDAO.getProductById(cartItem.getProductId());
                cartProductMap.put(cartItem.getProductId(), product);
            }
            AddressDAOImpl addressDAO = new AddressDAOImpl();
            List<Address> addresses = addressDAO.getAddressesByUserId(uid);
            
            request.setAttribute("addresses", addresses);
            request.setAttribute("cartItems", cartItems);
            request.setAttribute("cartProductMap", cartProductMap);
            request.setAttribute("checkedCartItems", checkedCartItems);
            // request.setAttribute("checkedCartProductMap", checkedCartProductMap);
            request.setAttribute("checkedCartTotalPrice", checkedCartTotalPrice);

            response.setContentType("text/html");
            RequestDispatcher rd = request.getRequestDispatcher("buynowCart.jsp");
            rd.forward(request, response);
        }catch (IOException | NumberFormatException | ServletException e) {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("{\"success\": false, \"message\": \"An exception occurred: " + e.getMessage() + "\", \"type\": \"error\"}");
        }
    }
}
