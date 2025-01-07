<%@ page contentType="text/html; charset=UTF-8" language="java" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
            <!DOCTYPE html>
            <html lang="en">

            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>Document</title>
                <link rel="stylesheet" href="css/sidebar.css">
                <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css"
                    integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg=="
                    crossorigin="anonymous" referrerpolicy="no-referrer" />
                <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
            </head>

            <body>
                <div class="sidebar">
                    <div class="sidebar-nav">
                        <div class="Home" onclick="window.location.href='/gu/home'">
                            <i class="fa-solid fa-house"></i>
                            <span>Home</span>
                        </div>
                        <hr>
                        <div class="my-profile" onclick="window.location.href='/gu/getUserDetails'">
                            <i class='bx bx-user'></i>
                            <span>My Profile</span>
                        </div>
                        <hr>
                        <div class="notification" onclick="window.location.href='/gu/notification'">
                            <i class="fa-regular fa-bell"></i>
                            <span>Notifications</span>
                        </div>
                        <hr>
                        <div class="cart" onclick="window.location.href='/gu/cart'">
                            <i class="fa-solid fa-cart-shopping"></i>
                            <span class="cart-count">Cart <%= session.getAttribute("cart_count") !=null ? "("
                                    +session.getAttribute("cart_count")+")" : "" %></span>
                        </div>
                        <hr>
                        <div class="wishlist" onclick="window.location.href='/gu/wishlist'">
                            <i class="fa-regular fa-heart"></i>
                            <span class="wishlist-count">Wishlist <%= session.getAttribute("wishlist_count") !=null
                                    ? "(" +session.getAttribute("wishlist_count")+")" : "" %></span>
                        </div>
                        <hr>
                        <div class="orders" onclick="window.location.href='/gu/orders'">
                            <i class='bx bx-box'></i>
                            <span class="order-count">Orders <%= session.getAttribute("order_count") !=null ? "("
                                    +session.getAttribute("order_count")+")" : "" %></span>
                        </div>
                        <hr>
                        <div class="become-seller" onclick="window.location.href='/gu/become-seller'">
                            <i class="fa-solid fa-shop"></i>
                            <span>Become Seller</span>
                        </div>
                        <% if (session !=null && session.getAttribute("user_id") !=null) { %>
                            <hr>
                            <div class="logout" onclick="window.location.href='/gu/logout'">
                                <i class="fa-solid fa-right-from-bracket"></i>
                                <span>Logout</span>
                            </div>
                            <% } %>
                    </div>
                    <% if (session !=null && session.getAttribute("user_id") !=null) { %>
                        <div class="sidebar-address">
                            <div class="address-heading">
                                <div class="your-address">
                                    <i class="fa-regular fa-map"></i>
                                    <span>Your Address (${fn:length(addresses)})</span>
                                </div>
                                <div class="address-plus">
                                    <!-- <button class="fa-solid fa-plus" onclick=""></button> -->
                                    <a href="addAddress.jsp" class="address-plus"><i class="fa-solid fa-plus"></i></a>
                                </div>
                            </div>
                            <c:forEach items="${addresses}" var="address">
                                <div class="addresses">
                                    <hr>
                                    <div class="address" data-address-id="${address.addressId}"
                                        onclick="addressClicked(this)">
                                        <h4 class="address-title">${address.addressType}</h4>
                                        <p class="address-description">${address.flatDetails}, ${address.areaDetails},
                                            ${address.townCity},
                                            ${address.state}, ${address.pincode}</p>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                        <% } %>
                            <div class="language">
                                <i class="fa-solid fa-globe"></i>
                                <p>English</p>
                            </div>
                </div>
                <script>
                    function addressClicked(addressElement) {
                        const addressId = addressElement.getAttribute('data-address-id');
                        window.location.href = "/gu/getAddress?addressId=" + addressId;
                    }
                </script>
            </body>

            </html>