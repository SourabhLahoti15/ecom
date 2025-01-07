function validateForm(event) {
    var gender = document.getElementById("gender");
    var email = document.getElementById("email");
    var password = document.getElementById("password");
    var phone = document.getElementById("number");

    email.setCustomValidity("");
    password.setCustomValidity("");
    phone.setCustomValidity("");
    gender.setCustomValidity("");

    let isValid = true;

    // Email validation
    var emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$/;
    if (!email.value.match(emailPattern)) {
        email.setCustomValidity("Please enter a valid email address.");
        isValid = false;
    }

    // Password validation
    if (password.value.length < 6) {
        password.setCustomValidity("Password must be at least 6 characters long.");
        isValid = false;
    } else if (!/[A-Z]/.test(password.value)) {
        password.setCustomValidity("Password must contain at least one uppercase letter.");
        isValid = false;
    } else if (!/[!@#$%^&*(),.?":{}|<>]/.test(password.value)) {
        password.setCustomValidity("Password must contain at least one special character.");
        isValid = false;
    }

    // Phone validation
    var phonePattern = /^[0-9]{10}$/;
    if (!phone.value.match(phonePattern)) {
        phone.setCustomValidity("Please enter a valid 10-digit phone number.");
        isValid = false;
    }

    if (!isValid) {
        event.preventDefault();
        if (!email.validity.valid) {
            email.reportValidity();
        } else if (!password.validity.valid) {
            password.reportValidity();
        } else if (!phone.validity.valid) {
            phone.reportValidity();
        }
    }

    return isValid;
}
document.getElementById('email').addEventListener('input', function() {
    this.setCustomValidity('');
});

document.getElementById('password').addEventListener('input', function() {
    this.setCustomValidity('');
});

document.getElementById('number').addEventListener('input', function() {
    this.setCustomValidity('');
});