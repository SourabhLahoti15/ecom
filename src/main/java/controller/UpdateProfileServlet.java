package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDAOImpl;
import model.User;

@WebServlet("/updateProfile")
public class UpdateProfileServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user_id") == null) {
            response.getWriter().write("{\"success\": false, \"message\": \"You are not logged in. Please login to add product.\", \"type\": \"error\"}");
            return;
        }

        int userId = (int) session.getAttribute("user_id");
        String name = request.getParameter("name");
        String gender = request.getParameter("gender");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String phoneStr = request.getParameter("phone");
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
        long phone;
        if (phoneStr == null || phoneStr.trim().isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"success\": false, \"message\": \"Phone number is required\", \"type\": \"error\"}");
            return;
        }
        try {
            phone = Long.parseLong(phoneStr);
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"success\": false, \"message\": \"Invalid phone number format\", \"type\": \"error\"}");
            return;
        }
        User user = new User(userId, name, gender, email, password, phone, dob);
        UserDAOImpl userDAO = new UserDAOImpl();
        try {
            boolean isUpdated = userDAO.updateUser(user);
            if (isUpdated) {
                response.setContentType("application/json");
                response.setStatus(HttpServletResponse.SC_OK);
                response.getWriter().write("{\"success\": true, \"message\": \"Profile updated successfully!\", \"type\": \"success\"}");
            } else {
                response.setContentType("application/json");
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("{\"success\": false, \"message\": \"Failed to update profile.\", \"type\": \"error\"}");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setContentType("application/json");
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"success\": false, \"message\": \"An error occurred while updating the profile.\", \"type\": \"error\"}");
        }
    }
}
