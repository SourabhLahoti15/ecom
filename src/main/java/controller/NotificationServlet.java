package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.NotificationDAOImpl;

@WebServlet({"/markAsRead", "/deleteNotification", "/clearallNotifications"})
public class NotificationServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("application/json");
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user_id") == null) {
            response.getWriter().write("{\"success\": false, \"message\": \"You are not logged in. Please login first.\", \"type\": \"error\"}");
            return;
        }
        int userId = (int) session.getAttribute("user_id");
        NotificationDAOImpl notificationDAO = new NotificationDAOImpl();
        String path = request.getServletPath();
        switch (path){
            case "/markAsRead":{
                int notificationId = Integer.parseInt(request.getParameter("notificationId"));
                boolean isDone = notificationDAO.markAsRead(notificationId);
                if (isDone) {
                    response.getWriter().write("{\"success\": true, \"message\": \"Marked as read.\", \"type\": \"success\"}");
                } else {
                    response.getWriter().write("{\"success\": false, \"message\": \"Failed to mark as read.\", \"type\": \"error\"}");                    
                }
                break;
            }
            case "/deleteNotification":{
                int notificationId = Integer.parseInt(request.getParameter("notificationId"));
                boolean isDeleted = notificationDAO.deleteNotification(notificationId);
                if(isDeleted) {
                    int notificationCount = notificationDAO.getNotificationsByUserId(userId).size();
                    session.setAttribute("notification_count", notificationCount);
                    response.getWriter().write("{\"success\": true, \"message\": \"Notification deleted.\", \"type\": \"success\"}");
                } else {                    
                    response.getWriter().write("{\"success\": false, \"message\": \"Failed to delete notification.\", \"type\": \"error\"}");
                }
                break;   
            }
            case "/clearallNotifications":{
                boolean isDeleted = notificationDAO.clearallNotifications(userId);
                if(isDeleted) {
                    int notificationCount = notificationDAO.getNotificationsByUserId(userId).size();
                    session.setAttribute("notification_count", notificationCount);
                    response.getWriter().write("{\"success\": true, \"message\": \"Notifications deleted.\", \"type\": \"success\"}");
                } else {                    
                    response.getWriter().write("{\"success\": false, \"message\": \"Failed to delete notifications.\", \"type\": \"error\"}");
                }
                break;   
            }
        }

    }
}
