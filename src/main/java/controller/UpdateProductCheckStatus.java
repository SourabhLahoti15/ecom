package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CartDAOImpl;

@WebServlet("/updateProductCheckStatus")
public class UpdateProductCheckStatus extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        HttpSession session = request.getSession();

        int userId = (int) session.getAttribute("user_id");
        int productId = Integer.parseInt(request.getParameter("productId"));
        boolean isChecked = Boolean.parseBoolean(request.getParameter("isChecked"));

        CartDAOImpl cartDAO = new CartDAOImpl();
        boolean isUpdated = cartDAO.updateProductCheckStatus(userId, productId, isChecked);
        int checkedCartCount = cartDAO.getCheckedCartItemsByUserId(userId).size();
        session.setAttribute("checked_cart_count", checkedCartCount);
        
        response.setContentType("application/json");
        response.getWriter().write("{\"success\": " + isUpdated + "}");
    }
}