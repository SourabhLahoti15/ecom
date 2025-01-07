package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDAOImpl;
import model.User;
@WebServlet("/getUserDetails")
public class ProfileServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("application/json");
        
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user_id") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        int loggedInUserId = (int) session.getAttribute("user_id");

        UserDAOImpl userDAO = new UserDAOImpl();
        User user = userDAO.getUserbyId(loggedInUserId);
        request.setAttribute("user", user);
        
        RequestDispatcher rd = request.getRequestDispatcher("profile.jsp");
        rd.forward(request, response);        
    }
}
