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
                    <link rel="stylesheet" href="css/wishlist.css">
                    <link rel="stylesheet"
                        href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css"
                        integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg=="
                        crossorigin="anonymous" referrerpolicy="no-referrer" />
                    <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
                </head>

                <body>
                    <main>
                        <div class="products">
                            <c:forEach var="wishlistItem" items="${wishlistItems}">
                                <c:set var="product" value="${productMap[wishlistItem.productId]}" />
                                <div class="product" onclick="window.location.href='/gu/product?productId=${wishlistItem.productId}'">
                                    <div class="product-content">
                                        <div class="product-img">
                                            <img src="${product.imagePath}" alt="Product Image">
                                        </div>
                                        <div class="product-details">
                                            <h2 class="product-name">${product.productName}</h2>
                                            <p class="product-description">${product.productDescription}</p>
                                            <p class="product-price">Rs. ${product.productPrice}</p>
                                            <p class="ordered-at">Added to wishlist on ${wishlistItem.addedDate}</p>
                                        </div>
                                    </div>
                                    <div class="btns">
                                        <button class="buynow" onclick='event.stopPropagation(); placeOrder("${wishlistItem.productId}")'>Buy Now</button>                                        
                                        <button class="addtocart" onclick='event.stopPropagation(); addToCart("${wishlistItem.productId}")'><i class="fa-solid fa-cart-plus"></i> Cart</button>                
                                        <button class="remove" onclick='event.stopPropagation(); removeFromWishlist("${wishlistItem.productId}")'><i class="fa-regular fa-trash-can"></i> Remove</button>                                    
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </main>
                    <script src="js/notification.js"></script>
                    <script src="js/productAction.js"></script>
                </body>

                </html>