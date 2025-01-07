function validateForm(event) {
    var pincode = document.getElementById("pincode");
    var phone = document.getElementById("number");

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
        
    }
    return isValid;
}
document.getElementById('number').addEventListener('input', function() {
    this.setCustomValidity('');
});
document.getElementById('pincode').addEventListener('input', function() {
    this.setCustomValidity('');
});