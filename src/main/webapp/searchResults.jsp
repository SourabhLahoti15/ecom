<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="sidebar.jsp" %>
<%@ include file="nav.jsp" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>eCom</title>
    <link rel="icon" href="images/cart.jpg">
    <link rel="stylesheet" href="css/sidebar.css">
    <link rel="stylesheet" href="css/card.css">
    <link rel="stylesheet" href="css/home.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css"
        integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg=="
        crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
</head>

<body>
    <main>
        <div class="heading">
            <h2>Search Results for: <%= request.getParameter("searchedProduct") %></h2>
            <p class="show-all">Show all</p>
        </div>
        <div class="cards">
            <c:forEach var="product" items="${searchResults}">
                <div class="card" onclick="window.location.href='/gu/product?productId=${product.productId}'">
                    <button class="addtowishlist" onclick='event.stopPropagation(); addToWishlist("${product.productId}")'>
                        <i class="fa-regular fa-heart"></i>
                    </button>
                    <button class="addtocart" onclick='event.stopPropagation(); addToCart("${product.productId}")'>
                        <i class="fa-solid fa-cart-plus"></i>
                    </button>
                    <div class="product-contents">
                        <div class="product-img">
                            <img src="${product.imagePath}" alt="Product Image">
                        </div>
                        <div class="product-details">
                            <div class="product-name"><b>${product.productName}</b></div>
                            <div class="product-description">${product.productDescription}</div>
                            <div class="product-price">Rs. ${product.productPrice}</div>
                        </div>
                    </div>
                    <button class="buynow-btn" onclick="buynow('${product.productId}'); event.stopPropagation();">Buy Now</button>
                </div>
            </c:forEach>
        </div>
    </main>

    <script src="js/notification.js"></script>
    <script src="js/productAction.js"></script>
</body>

</html>
