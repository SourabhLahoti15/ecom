<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="sidebar.jsp" %>
<%@ include file="nav.jsp" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Address</title>
    <link rel="stylesheet" href="css/address.css">
</head>

<body>
    <main>
        <form id="addAddressForm" name="addAddressForm" method="POST" onsubmit="validateAddressForm(event)">
            <h2>Add Address</h2>
            <!-- User ID -->
            <input type="hidden" name="userId" value="${user.userId}">

            <!-- Country/Region -->
            <label for="countryRegion">Country/Region:</label>
            <input type="text" id="countryRegion" name="countryRegion" required>

            <!-- Full Name -->
            <label for="fullName">Full Name:</label>
            <input type="text" id="fullName" name="fullName" required>

            <!-- Pincode -->
            <label for="pincode">Pincode:</label>
            <input type="text" id="pincode" name="pincode" required>

            <!-- Mobile -->
            <label for="mobile">Mobile:</label>
            <input type="text" id="mobile" name="mobile" required>

            <!-- Flat Details -->
            <label for="flatDetails">Flat/House No., Building:</label>
            <input type="text" id="flatDetails" name="flatDetails" required>

            <!-- Area Details -->
            <label for="areaDetails">Area/Street, Sector/Village:</label>
            <input type="text" id="areaDetails" name="areaDetails" required>

            <!-- Landmark -->
            <label for="landmark">Landmark:</label>
            <input type="text" id="landmark" name="landmark">

            <!-- Town/City -->
            <label for="townCity">Town/City:</label>
            <input type="text" id="townCity" name="townCity" required>

            <!-- State -->
            <label for="state">State:</label>
            <input type="text" id="state" name="state" required>

            <!-- Address Type -->
            <label for="addressType">Address Type:</label>
            <select id="addressType" name="addressType" required>
                <option value="Home">Home</option>
                <option value="Office">Office</option>
                <option value="Other">Other</option>
            </select>

            <!-- Additional Instructions -->
            <label for="additionalInstructions">Additional Instructions:</label>
            <textarea id="additionalInstructions" name="additionalInstructions"></textarea>

            <!-- Submit Button -->
            <button type="submit" id="submit" name="submit">Add Address</button>
        </form>
    </main>
    <script src="js/notification.js"></script>
    <script>
        function validateAddressForm(event) {
            var pincode = document.getElementById("pincode");
            var phone = document.getElementById("mobile");

            pincode.setCustomValidity("");
            phone.setCustomValidity("");

            let isValid = true;
            
            // pincode validation
            if (pincode.value.length != 6) {
                pincode.setCustomValidity("Pincode must be of 6 digits");
                pincode.reportValidity();
                isValid = false;
            }
            // Phone validation
            var phonePattern = /^[0-9]{10}$/;
            if (!phone.value.match(phonePattern)) {
                phone.setCustomValidity("Please enter a valid 10-digit phone number.");
                phone.reportValidity();
                isValid = false;
            }

            if (!isValid) {
                event.preventDefault();
            } else{
                addAddress(event);
            }
            return isValid;
        }
        document.getElementById('mobile').addEventListener('input', function() {
            this.setCustomValidity('');
        });
        document.getElementById('pincode').addEventListener('input', function() {
            this.setCustomValidity('');
        });
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
                }
                showNotification(data.success, data.message, data.type);
            })
            .catch(error => {
                console.error("Error while adding address:", error);
                showNotification(false, "Error occured while adding address.", "error");
            });
        }
    </script>
</body>

</html>
