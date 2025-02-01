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
                    <link rel="stylesheet" href="css/longCard.css">
                    <link rel="stylesheet" href="css/sidebar.css">
                    <link rel="stylesheet" href="css/cart.css">
                    <link rel="stylesheet"
                        href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css"
                        integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg=="
                        crossorigin="anonymous" referrerpolicy="no-referrer" />
                    <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
                </head>

                <body>
                    <main>
                        <div class="cart">
                            <div class="products-bill">
                                <div class="products">
                                    <h2 style="margin: 20px 0px 0px 30px;">
                                        <%= session.getAttribute("cart_count") !=null ? "Shopping Cart ("
                                            +session.getAttribute("cart_count")+")" : "" %>
                                    </h2>
                                    <c:forEach var="cartItem" items="${cartItems}">
                                        <c:set var="product" value="${cartProductMap[cartItem.productId]}" />
                                        <div class="cart-product product" id="product-${cartItem.productId}"
                                            onclick="window.location.href='/gu/product?productId=${cartItem.productId}'">
                                            <div class="cart-product-content product-content">
                                                <input type="checkbox" class="product-checkbox"
                                                    data-product-id="${cartItem.productId}" ${cartItem.isChecked
                                                    ? 'checked' : '' } onclick="event.stopPropagation();">
                                                <div class="cart-product-img product-img">
                                                    <img src="${product.imagePath}" alt="Product Image">
                                                </div>
                                                <div class="cart-product-details product-details">
                                                    <h2 class="product-name">${product.productName}</h2>
                                                    <p class="product-description">${product.productDescription}</p>
                                                    <p class="product-price">Rs. ${product.productPrice}</p>
                                                    <div class="added-move">
                                                        <p class="added-at">Added to cart on ${cartItem.addedDate}</p>
                                                        <a class="save-later-a" href="#"
                                                            onclick="event.stopPropagation(); saveForLater('${cartItem.productId}')">Save
                                                            for later</a>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="cart-btns btns">
                                                <button class="buynow"
                                                    onclick='event.stopPropagation(); buynow("${cartItem.productId}")'>Buy
                                                    Now</button>
                                                <button class="addtowishlist"
                                                    onclick='event.stopPropagation(); addToWishlist("${cartItem.productId}")'><i
                                                        class="fa-regular fa-heart"></i> Wishlist</button>
                                                <button class="remove"
                                                    onclick='event.stopPropagation(); removeFromCart("${cartItem.productId}")'><i
                                                        class="fa-regular fa-trash-can"></i> Remove</button>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </div>
                                <div class="bill-checkout">
                                    <div class="bill">
                                        <div class="price-details">
                                            <h4>PRICE DETAILS</h4>
                                        </div>
                                        <hr>
                                        <hr>
                                        <div class="price">
                                            <span>Price (${checked_cart_count})</span>
                                            <span>₹ ${cartCheckedTotalPrice}</span>
                                        </div>
                                        <hr>
                                        <div class="discount">
                                            <span>Discount</span>
                                        </div>
                                        <hr>
                                        <div class="coupons">
                                            <span>Coupons</span>
                                        </div>
                                        <hr>
                                        <div class="delivery">
                                            <span>Delivery Charges</span>
                                        </div>
                                        <hr>
                                        <hr>
                                        <div class="total-amount">
                                            <span>Total Amount</span>
                                        </div>
                                    </div>
                                    <button class="checkout" onclick="event.stopPropagation(); buynowCart()">Checkout</button>
                                </div>
                            </div>
                            <!-- <hr> -->
                            <!-- <div class="bill">
                                <h3>Total (${cart_count} items): Rs. ${cartCheckedTotalPrice}</h3>
                                <button class="proceedbuy"
                                    onclick="event.stopPropagation(); proceedToBuy()">Proceed to buy</button>
                            </div> -->
                        </div>
                        <c:if test="${savelater_count > 0}">
                            <div class="save-later">
                                <h2 style="margin: 20px 0px 0px 30px;">Saved for later (${savelater_count})</h2>
                                <div class="products">
                                    <c:forEach var="savelaterItem" items="${savelaterItems}">
                                        <c:set var="product" value="${savelaterProductMap[savelaterItem.productId]}" />
                                        <div class="product"
                                            onclick="window.location.href='/gu/product?productId=${savelaterItem.productId}'">
                                            <div class="product-content">
                                                <div class="product-img">
                                                    <img src="${product.imagePath}" alt="Product Image">
                                                </div>
                                                <div class="product-details">
                                                    <h2 class="product-name">${product.productName}</h2>
                                                    <p class="product-description">${product.productDescription}</p>
                                                    <p class="product-price">Rs. ${product.productPrice}</p>
                                                    <div class="added-move">
                                                        <p class="added-at">Saved for later on
                                                            ${savelaterItem.addedDate}</p>
                                                        <a href="#" class="save-later-a"
                                                            onclick="event.stopPropagation(); moveToCart('${savelaterItem.productId}')">Move
                                                            to cart</a>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="btns">
                                                <button class="buynow"
                                                    onclick='event.stopPropagation(); buynow("${savelaterItem.productId}")'>Buy
                                                    Now</button>
                                                <button class="addtowishlist"
                                                    onclick='event.stopPropagation(); addToWishlist("${savelaterItem.productId}")'><i
                                                        class="fa-regular fa-heart"></i> Wishlist</button>
                                                <button class="remove"
                                                    onclick='event.stopPropagation(); removeFromSavelater("${savelaterItem.productId}")'><i
                                                        class="fa-regular fa-trash-can"></i> Remove</button>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>
                        </c:if>
                    </main>
                    <script src="js/notification.js"></script>
                    <script src="js/productAction.js"></script>
                    <script>
                        document.querySelectorAll('.product-checkbox').forEach(checkbox => {
                            checkbox.addEventListener('change', function () {
                                event.preventDefault();
                                const productId = this.dataset.productId;
                                const isChecked = this.checked;

                                const formData = new URLSearchParams();
                                formData.append('productId', productId);
                                formData.append('isChecked', isChecked);

                                fetch('/gu/updateProductCheckStatus', {
                                    method: 'POST',
                                    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
                                    body: formData.toString()
                                })
                                    .then(response => response.json())
                                    .then(data => {
                                        if (data.success) {
                                            location.reload();
                                        } else {
                                            showNotification("false", "Failed to update cart status.", "error");
                                        }
                                    })
                                    .catch(error => console.error('Error updating product check status:', error));
                            });
                        });
                    </script>
                </body>

                </html>