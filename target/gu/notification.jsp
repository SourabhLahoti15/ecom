<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="sidebar.jsp" %>
<%@ include file="nav.jsp" %>
    <!DOCTYPE html>
    <html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Document</title>
        <link rel="stylesheet" href="css/sidebar.css">
        <link rel="stylesheet" href="css/notification.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css"
            integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg=="
            crossorigin="anonymous" referrerpolicy="no-referrer" />
        <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
    </head>

    <body>
        <main>
            <div class="categories">
                <div class="category">
                    <button>All</button>
                    <button>Order Update</button>
                    <button>New Order</button>
                    <button>Promotional</button>
                    <button>System Alert</button>
                </div>
                <!-- <div class="read-unread">
                    <button>Read</button>
                    <button>Unread</button>
                </div> -->
                <div class="clear-all">
                    <a href="#" onclick="clearallNotifications()" class="clear-all-a">clear all</a>
                </div>
            </div>
            <div class="msgs">
                <c:forEach var="notification" items="${notifications}">
                    <div class="msg ${notification.status == 'Unread' ? 'unread-msg' : 'read-msg'}">
                        <button class="remove-notification-btn" onclick="event.stopPropagation(); deleteNotification('${notification.notificationId}')">
                            <i class="fa-solid fa-xmark"></i>
                        </button>
                        <div class="msg-img">
                            <img src="images/husn.jpeg" alt="">
                        </div>
                        <div class="msg-details">
                            <h2 class="msg-name">${notification.type}</h2>
                            <p class="msg-description">${notification.message}</p>
                            <p class="notified-at">Notified on ${notification.createdAt}</p>
                        </div>
                        <c:if test="${notification.status == 'Unread'}">
                            <a href="#" onclick="markAsRead('${notification.notificationId}')" class="mark-as-read">Mark as Read</a>
                        </c:if>
                    </div>
                </c:forEach>
            </div>
        </main>
        <script src="js/notification.js"></script>
        <script src="js/notificationAction.js"></script>
    </body>
    </html>