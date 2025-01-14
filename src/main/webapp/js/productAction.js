function addToCart(productId) {
    const url = "/gu/addToCart?productId=" + productId;
    fetch(url)
        .then(response => response.json())
        .then(data => {
            showNotification(data.success, data.message, data.type);
            if (data.cart_count !== undefined) {
                document.querySelector('.cart-count').textContent = "Cart (" + data.cart_count + ")";
            }
        })
        .catch(error => {
            showNotification(false, 'Error occured while adding product to cart.', 'error');
        });
}
function removeFromCart(productId) {
    const url = "/gu/removeFromCart?productId=" + productId;
    fetch(url)
        .then(response => response.json())
        .then(data => {
            if (data.success) {
                localStorage.setItem('notification', JSON.stringify({
                    success: data.success,
                    message: data.message,
                    type: data.type
                }));
                window.location.reload();
                document.querySelector('.cart-count').textContent = "Cart (" + data.cart_count + ")";
            } else {
                showNotification(data.success, data.message, data.type);
            }
        })
        .catch(error => {
            showNotification(data.success, 'Error occured while removing product from cart.', 'error');
        });
}
function addToWishlist(productId) {
    const url = "/gu/addToWishlist?productId=" + productId;
    fetch(url)
        .then(response => response.json())
        .then(data => {
            showNotification(data.success, data.message, data.type);
            if (data.wishlist_count !== undefined) {
                document.querySelector('.wishlist-count').textContent = "Wishlist (" + data.wishlist_count + ")";
            }
        })
        .catch(error => {
            showNotification(false, 'Error occured while adding product to wishlist.', 'error');
        });
}
function removeFromWishlist(productId) {
    const url = "/gu/removeFromWishlist?productId=" + productId;
    fetch(url)
        .then(response => response.json())
        .then(data => {
            if (data.success) {
                localStorage.setItem('notification', JSON.stringify({
                    success: data.success,
                    message: data.message,
                    type: data.type
                }));
                window.location.reload();
                document.querySelector('.wishlist-count').textContent = "Wishlist (" + data.wishlist_count + ")";
            } else {
                showNotification(data.success, data.message, data.type);
            }
        })
        .catch(error => {
            showNotification(data.success, 'Error occured while removing product from wishlist.', 'error');
        });
}
function deleteProduct(productId) {
    const url = "/gu/deleteProduct?productId=" + productId;
    fetch(url)
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
            showNotification(false, 'Error occurred while deleting the product.', 'error');
        });
}
function placeOrder(productId) {
    const form = document.querySelector('.buynow-form');
    const addressId = form.querySelector('input[name="addressId"]:checked').value;
    const paymentMode = form.querySelector('input[name="paymentMode"]:checked').value;

    const contextPath = window.location.pathname.split('/')[1];
    const url = "/" + contextPath + "/placeOrder";

    const formData = new URLSearchParams();
    formData.append('productId', productId);
    formData.append('addressId', addressId);
    formData.append('paymentMode', paymentMode);
    fetch(url, {
        method: 'POST',
        credentials: 'same-origin',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
        },
        body: formData
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        // .then(response => response.json())
        .then(data => {
            if (data.success) {
                localStorage.setItem('notification', JSON.stringify({
                    success: data.success,
                    message: data.message,
                    type: data.type
                }));
                window.location.href = '/gu/orders';
            } else {
                showNotification(false, data.message, 'error');
            }
            if (data.order_count !== undefined) {
                document.querySelector('.order-count').textContent = "Orders (" + data.order_count + ")";
            }
        })
        .catch(error => {
            showNotification(false, 'Error occured while placing order.', 'error');
        });
}
function removeFromOrders(orderId) {
    const url = "/gu/removeFromOrders?orderId=" + orderId;
    fetch(url)
        .then(response => response.json())
        .then(data => {
            if (data.success) {
                localStorage.setItem('notification', JSON.stringify({
                    success: data.success,
                    message: data.message,
                    type: data.type
                }));
                window.location.reload();
                document.querySelector('.order-count').textContent = "Orders (" + data.order_count + ")";
            } else {
                showNotification(data.success, data.message, data.type);
            }
        })
        .catch(error => {
            showNotification(data.success, 'Error occured while cancelling order.', 'error');
        });
}
function buynow(productId) {
    const url = "/gu/buynow?productId=" + productId;
    fetch(url)
        .then(response => {
            const contentType = response.headers.get("content-type");
            if (contentType && contentType.includes("application/json")) {
                return response.json().then(data => {
                    showNotification(data.success, data.message, data.type);
                });
            } else {
                window.location.href = url;
            }
        })
        .catch(error => {
            console.error(error);
            showNotification(false, 'Error occurred while processing request.', 'error');
        });
}
function addAddress(event) {
    event.preventDefault(); 

    const form = document.getElementById('addAddressForm');
    const formData = new URLSearchParams(new FormData(form));

    fetch('/gu/addAddress', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: formData
    })
    .then(response => response.json())
    .then(data => {
        if (data.success) {
            fetch('/gu/sidebar')
            .then(response => response.text())
            .then(html => {
                document.querySelector('.sidebar-address').innerHTML = html;
            });
            localStorage.setItem('notification', JSON.stringify({
                success: data.success,
                message: data.message,
                type: data.type
            }));
            window.location.href = document.referrer;
        } else {
            showNotification(data.success, data.message, data.type);
        }
    })
    .catch(error => {
        console.error("Error while adding address:", error);
        showNotification(false, "Error occured while adding address.", "error");
    });
}
function deleteAddress(addressId) {
    const url = "/gu/deleteAddress?addressId=" + addressId;
    console.log(url);
    fetch(url, {
        method: "POST"
    })
        .then(response => response.json())
        .then(data => {
            if (data.success) {
                fetch('/gu/sidebar')
                    .then(response => response.text())
                    .then(html => {
                        document.querySelector('.sidebar-address').innerHTML = html;
                    });
            }
            showNotification(data.success, data.message, data.type);
        })
        .catch(error => {
            console.error(error);
            showNotification(false, 'Error occurred while deleting address.', 'error');
        })
}
document.addEventListener('DOMContentLoaded', function () {
    const notificationData = localStorage.getItem('notification');
    if (notificationData) {
        const { success, message, type } = JSON.parse(notificationData);
        showNotification(success, message, type);
        localStorage.removeItem('notification');
    }
});