package dao;

import java.util.List;

import model.Notification;

public interface NotificationDAO {
    boolean addNotification(Notification notification);
    boolean deleteNotification(int notificationId);
    List<Notification> getNotificationsByUserId(int userId);
    boolean clearallNotifications(int userId);
    boolean markAsRead(int notificationId);
    boolean deleteOldNotifications(int days);
}
