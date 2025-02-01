package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDAOImpl;

@WebServlet("/deleteUser")
public class DeleteUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("application/json");
        
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user_id") == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        int loggedInUserId = (int) session.getAttribute("user_id");

        UserDAOImpl userDAO = new UserDAOImpl();
        boolean isDeleted = userDAO.deleteUser(loggedInUserId);
        if(isDeleted){
            session.invalidate();
            response.getWriter().write("{\"success\": true, \"message\": \"Account deleted successfully.\", \"type\": \"success\"}");
        } else {
            response.getWriter().write("{\"success\": false, \"message\": \"Failed to delete account.\", \"type\": \"error\"}");
        }
    }
}
