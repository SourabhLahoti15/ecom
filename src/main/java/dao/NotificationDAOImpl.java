package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import model.Notification;

public class NotificationDAOImpl implements NotificationDAO{

    private static final String URL = "jdbc:mysql://localhost:3306/project";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    @Override
    public boolean addNotification(Notification notification) {
        String query = "INSERT INTO notifications (user_id, order_id, type, message, status, created_at) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, notification.getUserId());
            ps.setObject(2, notification.getOrderId());
            ps.setString(3, notification.getType());
            ps.setString(4, notification.getMessage());
            ps.setString(5, notification.getStatus());
            ps.setTimestamp(6, Timestamp.valueOf(notification.getCreatedAt()));

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    @Override
    public boolean deleteNotification(int notificationId) {
        String query = "DELETE FROM notifications WHERE notification_id = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, notificationId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    @Override
    public List<Notification> getNotificationsByUserId(int userId) {
        String query = "SELECT * FROM notifications WHERE user_id = ? ORDER BY created_at DESC";
        List<Notification> notifications = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Notification notification = new Notification(
                    rs.getInt("notification_id"),
                    rs.getInt("user_id"),
                    rs.getInt("order_id"),
                    rs.getString("type"),
                    rs.getString("message"),
                    rs.getString("status"),
                    rs.getTimestamp("created_at").toLocalDateTime(),
                    rs.getTimestamp("read_at") != null ? rs.getTimestamp("read_at").toLocalDateTime() : null
                );
                notifications.add(notification);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return notifications;
    }
    @Override
    public boolean clearallNotifications(int userId) {
        String query = "DELETE FROM notifications WHERE user_id = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, userId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    @Override
    public boolean markAsRead(int notificationId) {
        String query = "UPDATE notifications SET status = 'Read', read_at = ? WHERE notification_id = ?";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
            ps.setInt(2, notificationId);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    @Override
    public boolean deleteOldNotifications(int days) {
        String query = "DELETE FROM notifications WHERE created_at < DATE_SUB(NOW(), INTERVAL ? DAY)";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, days);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
