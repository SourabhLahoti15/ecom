<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ include file="sidebar.jsp" %>
<%@ include file="nav.jsp" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="css/product.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css"
        integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg=="
        crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
</head>

<body>
    <main>
        <div class="product" var="product">
            <div class="product-img">
                <img src="${product.imagePath}" alt="Product Image">
                <div class="btns">
                    <button class="buynow" onclick='placeOrder("${product.productId}")'>BUY NOW</button>
                    <button class="addtocart" onclick='addToCart("${product.productId}")'>ADD TO CART</button>
                </div>
            </div>
            <div class="product-details">
                <h2 class="product-name">${product.productName}</h2>
                <p class="product-description">${product.productDescription}</p>
                <p class="product-price">${product.productPrice}</p>
            </div>
        </div>
    </main>
    <script src="js/notification.js"></script>
    <script src="js/productAction.js"></script>
</body>
</html>