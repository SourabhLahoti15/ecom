<%@ page contentType="text/html; charset=UTF-8" language="java" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ include file="sidebar.jsp" %>
            <%@ include file="nav.jsp" %>
                <!DOCTYPE html>
                <html lang="en">

                <head>
                    <meta charset="UTF-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                    <title>Document</title>
                    <link rel="stylesheet" href="css/seller.css">
                    <link rel="stylesheet" href="css/card.css">
                    <link rel="stylesheet"
                        href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css"
                        integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg=="
                        crossorigin="anonymous" referrerpolicy="no-referrer" />
                    <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
                </head>

                <body>
                    <main>
                        <form class="seller-form" method="POST" name="seller" action="${pageContext.request.contextPath}/addProduct"
                            enctype="multipart/form-data">
                            <div class="questions">
                                <div class="question">
                                    <label for="productImage">Image:</label>
                                    <input type="file" id="productImage" name="productImage" accept="image/*" required>
                                </div>
                                <div class="question">
                                    <label for="product_name">Name:</label>
                                    <input type="text" name="product_name" placeholder="Enter product name" required>
                                </div>
                                <div class="question">
                                    <label for="product_description">Description:</label>
                                    <input type="text" name="product_description"
                                        placeholder="Enter product description" required>
                                </div>
                                <div class="question">
                                    <label for="product_price">Price:</label>
                                    <input type="text" name="product_price" placeholder="Enter product price" required>
                                </div>
                                <div class="question">
                                    <label for="product_stock">Stock:</label>
                                    <input type="text" name="product_stock" placeholder="Enter product stock" required>
                                </div>
                            </div>
                            <div class="buttons">
                                <input type="submit" id="submit" name="submit" value="Add">
                                <input type="reset" id="reset" name="reset" value="Reset">
                            </div>
                        </form>
                        <% if (session != null && session.getAttribute("user_id") != null) { %>
                        <div class="your-products">
                            <h2>Your Products</h2>
                            <div class="cards">
                                <c:forEach var="product" items="${productsByUid}">
                                    <div class="card" onclick="window.location.href='/gu/product?productId=${product.productId}'">
                                        <button type="submit" class="addtowishlist"
                                            onclick='event.stopPropagation(); addToWishlist("${product.productId}")'>
                                            <i class="fa-regular fa-heart"></i>
                                        </button>
                                        <button type="submit" class="addtocart"
                                            onclick='event.stopPropagation(); addToCart("${product.productId}")'>
                                            <i class="fa-solid fa-cart-plus"></i>
                                        </button>
                                        <button type="submit" class="edit"
                                            onclick='event.stopPropagation(); editProduct("${product.productId}")'>
                                            <i class="fa-regular fa-pen-to-square"></i>
                                        </button>
                                        <button type="submit" class="delete"
                                            onclick='event.stopPropagation(); deleteProduct("${product.productId}")'>
                                            <i class="fa-regular fa-trash-can"></i>
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
                        </div>
                        <% } %>
                    </main>
                    <script src="js/notification.js"></script>
                    
                    <script>
                        document.forms["seller"].addEventListener('submit', function (event) {
                            var price = document.getElementsByName('product_price')[0];
                            var stock = document.getElementsByName('product_stock')[0];

                            price.setCustomValidity("");
                            stock.setCustomValidity("");

                            let isValid = true;

                            if (isNaN(price.value) || parseFloat(price.value) <= 0) {
                                price.setCustomValidity("Please enter a valid price.");
                                isValid = false;
                            }

                            if (isNaN(stock.value) || parseInt(stock.value) <= 0 || !Number.isInteger(parseFloat(stock.value))) {
                                stock.setCustomValidity("Please enter valid stock quantity.");
                                isValid = false;
                            }

                            if (!isValid) {
                                event.preventDefault();
                                [price, stock].forEach(el => {
                                    if (!el.validity.valid) el.reportValidity();
                                });
                                return;
                            }

                            const formData = new FormData(document.forms["seller"]);
                            const contextPath = window.location.pathname.split('/')[1];
                            const url = '/' + contextPath + '/addProduct';

                            fetch(url, {
                                method: 'POST',
                                body: formData,
                                credentials: 'same-origin'
                            })
                                .then(response => response.json())
                                .then(data => {
                                    if (data.success) {
                                        localStorage.setItem('notification', JSON.stringify({
                                            success: data.success,
                                            message: data.message,
                                            type: data.type
                                        }));
                                        window.location.reload();
                                    } else {
                                        showNotification(data.success, data.message, data.type);
                                    }
                                })

                                .catch(error => {
                                    showNotification(data.success, 'Error occured while adding product.', 'error');
                                });
                            event.preventDefault();
                        });
                        
                        document.getElementsByName('product_price')[0].addEventListener('input', function () {
                            this.setCustomValidity('');
                        });

                        document.getElementsByName('product_stock')[0].addEventListener('input', function () {
                            this.setCustomValidity('');
                        });
                    </script>
                    <script src="js/productAction.js"></script>
                </body>

                </html>