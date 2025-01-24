<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="sidebar.jsp" %>
<%@ include file="nav.jsp" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Update Address</title>
    <link rel="stylesheet" href="css/address.css">
    <link rel="stylesheet" href="css/nav.css">
</head>

<body>
    <main>
        <form id="updateAddressForm" method="POST" name="updateAddressForm" onsubmit="validateAddressForm(event)">
            <h2>update Address</h2>
            <input type="hidden" id="actionType" name="actionType" value="update">
            <!-- AddressId -->
            <input type="hidden" name="addressId" value="${address.addressId}">

            <!-- User ID -->
            <input type="hidden" name="userId" value="${user.userId}">

            <!-- Country/Region -->
            <label for="countryRegion">Country/Region:</label>
            <input type="text" id="countryRegion" name="countryRegion" value="${address.countryRegion}" required>

            <!-- Full Name -->
            <label for="fullName">Full Name:</label>
            <input type="text" id="fullName" name="fullName" value="${address.fullName}" required>

            <!-- Pincode -->
            <label for="pincode">Pincode:</label>
            <input type="text" id="pincode" name="pincode" value="${address.pincode}" required>

            <!-- Mobile -->
            <label for="mobile">Mobile:</label>
            <input type="text" id="mobile" name="mobile" value="${address.mobile}" required>

            <!-- Flat Details -->
            <label for="flatDetails">Flat/House No., Building:</label>
            <input type="text" id="flatDetails" name="flatDetails" value="${address.flatDetails}" required>

            <!-- Area Details -->
            <label for="areaDetails">Area/Street, Sector/Village:</label>
            <input type="text" id="areaDetails" name="areaDetails" value="${address.areaDetails}" required>

            <!-- Landmark -->
            <label for="landmark">Landmark:</label>
            <input type="text" id="landmark" name="landmark" value="${address.landmark}">

            <!-- Town/City -->
            <label for="townCity">Town/City:</label>
            <input type="text" id="townCity" name="townCity" value="${address.townCity}" required>

            <!-- State -->
            <label for="state">State:</label>
            <input type="text" id="state" name="state" value="${address.state}" required>

            <!-- Address Type -->
            <label for="addressType">Address Type:</label>
            <select id="addressType" name="addressType" required>
                <option value="Home" <c:if test="${address.addressType == 'Home'}">selected</c:if>>Home</option>
                <option value="Office" <c:if test="${address.addressType == 'Office'}">selected</c:if>>Office</option>
                <option value="Other" <c:if test="${address.addressType == 'Other'}">selected</c:if>>Other</option>
            </select>

            <!-- Additional Instructions -->
            <label for="additionalInstructions">Additional Instructions:</label>
            <textarea id="additionalInstructions" name="additionalInstructions">${address.additionalInstructions}</textarea>

            <!-- Submit Button -->
            <button type="submit" id="address-submit" name="submit">Update Address</button>
        </form>
    </main>
    <script src="js/notification.js"></script>
    <script src="js/address.js"></script>
</body>

</html>
