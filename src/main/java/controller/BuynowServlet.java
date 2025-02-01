package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ProductDAOImpl;
import model.Product;

@WebServlet("/buynow")
public class BuynowServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        
        if (session == null || session.getAttribute("user_id") == null) {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("{\"success\": false, \"message\": \"You are not logged in. Please login to buy product.\", \"type\": \"error\"}");
            return;
        }

        try {
            int userId = (int) session.getAttribute("user_id");
            String productId = request.getParameter("productId");
            
            if (productId == null) {
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write("{\"success\": false, \"message\": \"Product id required.\", \"type\": \"error\"}");
                return;
            }

            ProductDAOImpl productDAO = new ProductDAOImpl();
            Product product = productDAO.getProductById(Integer.parseInt(productId));
            
            if (product == null) {
                response.setContentType("application/json");
                response.getWriter().write("{\"success\": false, \"message\": \"Product not found.\", \"type\": \"error\"}");
                return;
            }

            request.setAttribute("product", product);

            response.setContentType("text/html");
            RequestDispatcher rd = request.getRequestDispatcher("buynow.jsp");
            rd.forward(request, response);
            // response.sendRedirect("buynow.jsp");
            // response.setContentType("application/json");
            // response.setCharacterEncoding("UTF-8");
            // response.getWriter().write("{\"success\": true, \"redirectUrl\": \"buynow.jsp\"}");
        } catch (IOException | NumberFormatException | ServletException e) {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("{\"success\": false, \"message\": \"An exception occurred: " + e.getMessage() + "\", \"type\": \"error\"}");
        }
    }
}