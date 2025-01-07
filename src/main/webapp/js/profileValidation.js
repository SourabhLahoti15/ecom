function validateForm(event) {
    var gender = document.querySelector('input[name="gender"]:checked');
    var email = document.getElementById("email");
    var password = document.getElementById("password");
    var phone = document.getElementById("phone");

    email.setCustomValidity("");
    password.setCustomValidity("");
    phone.setCustomValidity("");
    document.querySelectorAll('input[name="gender"]').forEach(input => input.setCustomValidity(''));

    let isValid = true;

    // Gender validation
    if (!gender) {
        document.querySelectorAll('input[name="gender"]').forEach(input => {
            input.setCustomValidity("Please select a gender.");
        });
        document.querySelector('input[name="gender"]').reportValidity();
        isValid = false;
    }

    // Email validation
    var emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$/;
    if (!email.value.match(emailPattern)) {
        email.setCustomValidity("Please enter a valid email address.");
        isValid = false;
    }

    // Password validation
    if (password.value.length > 0) {  
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
    }

    // Phone validation
    var phonePattern = /^[0-9]{10}$/;
    if (!phone.value.match(phonePattern)) {
        phone.setCustomValidity("Please enter a valid 10-digit phone number.");
        isValid = false;
    }

    if (!isValid) {
        event.preventDefault();
        if (!gender) {
            document.querySelector('input[name="gender"]').reportValidity();
        } else if (!email.validity.valid) {
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

document.getElementById('phone').addEventListener('input', function() {
    this.setCustomValidity('');
});

document.querySelectorAll('input[name="gender"]').forEach(input => {
    input.addEventListener('change', function() {
        document.querySelectorAll('input[name="gender"]').forEach(input => 
            input.setCustomValidity('')
        );
    });
});
