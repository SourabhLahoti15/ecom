package model;

import java.time.LocalDateTime;

public class Notification {

    private int notificationId; 
    private int userId;         
    private int orderId;    
    private String type;        
    private String message;     
    private String status;      
    private LocalDateTime createdAt; 
    private LocalDateTime readAt; 
    
    public Notification(int userId, int orderId, String type, String message, 
    String status, LocalDateTime createdAt) {
        this.userId = userId;
        this.orderId = orderId;
        this.type = type;
        this.message = message;
        this.status = status;
        this.createdAt = createdAt;
    }

    public Notification(int notificationId, int userId, int orderId, String type, String message, 
    String status, LocalDateTime createdAt, LocalDateTime readAt) {
        this.notificationId = notificationId;
        this.userId = userId;
        this.orderId = orderId;
        this.type = type;
        this.message = message;
        this.status = status;
        this.createdAt = createdAt;
        this.readAt = readAt;
    }
    
    public int getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(int notificationId) {
        this.notificationId = notificationId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getReadAt() {
        return readAt;
    }

    public void setReadAt(LocalDateTime readAt) {
        this.readAt = readAt;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "notificationId=" + notificationId +
                ", userId=" + userId +
                ", orderId=" + orderId +
                ", type='" + type + '\'' +
                ", message='" + message + '\'' +
                ", status='" + status + '\'' +
                ", createdAt=" + createdAt +
                ", readAt=" + readAt +
                '}';
    }
}
