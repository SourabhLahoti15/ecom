package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDAOImpl;
import model.User;

@WebServlet("/getUsers")
public class AdminServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getServletPath();
        RequestDispatcher rd;
        try {
            switch (path) {
                case "/getUsers": {
                    UserDAOImpl userDAO = new UserDAOImpl();
                    List<User> users = userDAO.getAllUsers();
                    request.setAttribute("users", users);
                    rd = request.getRequestDispatcher("users.jsp");
                    rd.forward(request, response);
                break;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("{\"success\": false, \"message\": \"Exception occured: " + e.getMessage()
                    + "\", \"type\": \"error\"}");
        }
    }
}
