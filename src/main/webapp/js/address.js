function validateAddressForm(event) {
    var pincode = document.getElementById("pincode");
    var phone = document.getElementById("mobile");
    var fullName = document.getElementById("fullName");
    var countryRegion = document.getElementById("countryRegion");
    var flatDetails = document.getElementById("flatDetails");
    var areaDetails = document.getElementById("areaDetails");
    var landmark = document.getElementById("landmark");
    var townCity = document.getElementById("townCity");
    var state = document.getElementById("state");

    [pincode, phone, fullName, countryRegion, flatDetails, areaDetails, landmark, townCity, state].forEach(el => {
        el.setCustomValidity("");
    });

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

    const maxLengths = {
        fullName: 150,
        countryRegion: 100,
        // pincode: 6,
        // phone: 10,
        flatDetails: 255,
        areaDetails: 255,
        landmark: 255,
        townCity: 100,
        state: 100
    };
    // full name validation
    if (fullName.value.length > maxLengths.fullName) {
        fullName.setCustomValidity(`Full Name cannot exceed ${maxLengths.fullName} characters.`);
        fullName.reportValidity();
        isValid = false;
    }
    // countryRegion validation
    if (countryRegion.value.length > maxLengths.countryRegion) {
        countryRegion.setCustomValidity(`Country/Region cannot exceed ${maxLengths.countryRegion} characters.`);
        countryRegion.reportValidity();
        isValid = false;
    }
    // flat details validation
    if (flatDetails.value.length > maxLengths.flatDetails) {
        flatDetails.setCustomValidity(`Flat details cannot exceed ${maxLengths.flatDetails} characters.`);
        flatDetails.reportValidity();
        isValid = false;
    }
    // area details validation
    if (areaDetails.value.length > maxLengths.areaDetails) {
        areaDetails.setCustomValidity(`Area details cannot exceed ${maxLengths.areaDetails} characters.`);
        areaDetails.reportValidity();
        isValid = false;
    }
    // landmark validation
    if (landmark.value.length > maxLengths.landmark) {
        landmark.setCustomValidity(`Landmark cannot exceed ${maxLengths.landmark} characters.`);
        landmark.reportValidity();
        isValid = false;
    }
    // town city validation
    if (townCity.value.length > maxLengths.townCity) {
        townCity.setCustomValidity(`Town/City cannot exceed ${maxLengths.townCity} characters.`);
        townCity.reportValidity();
        isValid = false;
    }
    // state validation
    if (state.value.length > maxLengths.state) {
        state.setCustomValidity(`State cannot exceed ${maxLengths.state} characters.`);
        state.reportValidity();
        isValid = false;
    }

    if (!isValid) {
        event.preventDefault();
    } else{
        const actionType = document.getElementById("actionType").value;
        if (actionType === 'add') {
            addAddress(event);
        } else if (actionType === 'update') {
            updateAddress(event);
        }
    }
    return isValid;
}
document.querySelectorAll("input").forEach(input => {
    input.addEventListener("input", function () {
        this.setCustomValidity("");
    });
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
            // .then(html => {
            //     document.querySelector('.sidebar-address').innerHTML = html;
            // });
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
function updateAddress(event) {
    event.preventDefault(); 

    const form = document.getElementById('updateAddressForm');
    const formData = new URLSearchParams(new FormData(form));

    fetch('/gu/updateAddress', {
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
        // .then(html => {
        //     document.querySelector('.sidebar-address').innerHTML = html;
        // });
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
        showNotification(false, "Error occured while updating address", "error");
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
                localStorage.setItem('notification', JSON.stringify({
                    success: data.success,
                    message: data.message,
                    type: data.type
                }));
                window.location.reload();
            }
            else{
                showNotification(data.success, data.message, data.type);
            }
        })
        .catch(error => {
            console.error(error);
            showNotification(false, 'Error occurred while deleting address.', 'error');
        })
}