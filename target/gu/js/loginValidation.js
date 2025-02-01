function validateForm(event) {
    var email = document.getElementById("email");
    var password = document.getElementById("password");

    email.setCustomValidity("");
    password.setCustomValidity("");

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

    if (!isValid) {
        event.preventDefault();
        if (!email.validity.valid) {
            email.reportValidity();
        } else if (!password.validity.valid) {
            password.reportValidity();
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