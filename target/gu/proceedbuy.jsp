<%@ page contentType="text/html; charset=UTF-8" language="java" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
            <%@ include file="sidebar.jsp" %>
                <%@ include file="nav.jsp" %>
                    <!DOCTYPE html>
                    <html lang="en">

                    <head>
                        <meta charset="UTF-8">
                        <meta name="viewport" content="width=device-width, initial-scale=1.0">
                        <title>Document</title>
                        <link rel="stylesheet" href="css/buynow.css">
                        <link rel="stylesheet"
                            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css"
                            integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg=="
                            crossorigin="anonymous" referrerpolicy="no-referrer" />
                        <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
                    </head>

                    <body>
                        <main>
                            <div class="products">
                                <c:forEach var="cartItem" items="${cartItems}">
                                    <c:set var="product" value="${cartProductMap[cartItem.productId]}" />
                                    <div class="product"
                                        onclick="window.location.href='/gu/product?productId=${cartItem.productId}'">
                                        <div class="product-content">
                                            <div class="product-img">
                                                <img src="${product.imagePath}" alt="Product Image">
                                            </div>
                                            <div class="product-details">
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
                                    </div>
                                </c:forEach>
                            </div>
                            <form class="buynow-form" name="buynow-form" method="POST">
                                <input type="hidden" name="productId" value="${product.productId}">
                                <div class="buy-addresses">
                                    <h2>Select address (${fn:length(addresses)})</h2>
                                    <c:if test="${empty addresses}">
                                        <div class="no-address-warning">
                                            <p>You don't have any addresses. Please
                                                <a href="addAddress.jsp" class="add-another-address">add an address</a>
                                                before placing an order.
                                            </p>
                                        </div>
                                    </c:if>
                                    <c:if test="${not empty addresses}">
                                        <c:forEach items="${addresses}" var="address">
                                            <div class="buy-address" data-address-id="${address.addressId}">
                                                <input type="radio" id="address-${address.addressId}" name="addressId"
                                                    value="${address.addressId}" required>
                                                <label for="address-${address.addressId}">
                                                    <h4 class="buy-address-title">${address.addressType}</h4>
                                                    <p class="buy-address-description">${address.flatDetails},
                                                        ${address.areaDetails}, ${address.townCity},
                                                        ${address.state},
                                                        ${address.pincode}</p>
                                                </label>
                                            </div>
                                        </c:forEach>
                                        <a href="addAddress.jsp" class="add-another-address">Add another address</a>
                                    </c:if>
                                </div>
                                <div class="payment-modes">
                                    <h2>Payment mode</h2>
                                    <div class="payment-mode">
                                        <input type="radio" id="paymentCard" name="paymentMode" value="Card" required>
                                        <label for="paymentCard">Credit/Debit Card</label>
                                    </div>
                                    <div class="payment-mode">
                                        <input type="radio" id="paymentUPI" name="paymentMode" value="UPI">
                                        <label for="paymentUPI">UPI (e.g., Google Pay, PhonePe)</label>
                                    </div>
                                    <div class="payment-mode">
                                        <input type="radio" id="paymentCOD" name="paymentMode" value="COD">
                                        <label for="paymentCOD">Cash on Delivery</label>
                                    </div>
                                </div>
                                <button type="submit" class="place-order-btn">Place Order</button>
                            </form>
                        </main>
                        <script src="js/notification.js"></script>
                        <script>document.forms["buynow-form"].onsubmit = function (event) {
                                const form = document.querySelector('.buynow-form');

                                form.querySelectorAll('input[name="addressId"], input[name="paymentMode"]').forEach(input => {
                                    input.setCustomValidity('');
                                });

                                const addressRadios = document.querySelectorAll('input[name="addressId"]');
                                const paymentRadios = document.querySelectorAll('input[name="paymentMode"]');

                                if (addressRadios.length === 0) {
                                    showNotification('false','Add an address to place order.','error');
                                    return false;
                                }

                                let addressSelected = false;
                                for (let radio of addressRadios) {
                                    if (radio.checked) {
                                        addressSelected = true;
                                        break;
                                    }
                                }

                                if (!addressSelected) {
                                    const firstAddressRadio = addressRadios[0];
                                    firstAddressRadio.setCustomValidity('Please select a delivery address.');
                                    firstAddressRadio.reportValidity();
                                    return false;
                                }

                                let paymentSelected = false;
                                for (let radio of paymentRadios) {
                                    if (radio.checked) {
                                        paymentSelected = true;
                                        break;
                                    }
                                }

                                if (!paymentSelected) {
                                    const firstPaymentRadio = paymentRadios[0];
                                    firstPaymentRadio.setCustomValidity('Please select a payment method.');
                                    firstPaymentRadio.reportValidity();
                                    return false;
                                }

                                const productId = form.querySelector('input[name="productId"]').value;
                                placeOrder(productId);
                                return false;
                            };

                        </script>
                        <script src="js/productAction.js"></script>
                    </body>

                    </html>