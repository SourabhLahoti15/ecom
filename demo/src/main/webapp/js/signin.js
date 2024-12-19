function validateForm(){
    const name = document.getElementById("name").value;
    const gender = document.querySelector('input[name="gender"]:checked')
    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;
    const address = document.getElementById("address").value;
    const phone = document.getElementById("number").value;

    const nameError = document.getElementById("name-error");
    const genderError = document.getElementById("gender-error");
    const emailError = document.getElementById("email-error");
    const passwordError = document.getElementById("password-error");
    const addressError = document.getElementById("address-error");
    const phoneError = document.getElementById("phone-error");

    nameError.textContent="";
    genderError.textContent="";
    emailError.textContent="";
    passwordError.textContent="";
    addressError.textContent="";
    phoneError.textContent="";
    
    let isValid = true;

    if(name==="" || /\d/.test(name)){
        nameError.textContent="Enter your name properly.";
        isValid=false;
    }
    if (!gender) {
        genderError.textContent = "Please select your gender.";
        isValid = false;
    }
    if(email==="" || !email.includes("@")){
        emailError.textContent="Enter valid email.";
        isValid=false;
    }
    if(password==="" || password.length<8){
        passwordError.textContent="Enter password with atleast 8 characters."
        isValid=false;
    }
    if(address===""){
        addressError.textContent="Enter your address.";
        isValid=false;
    }
    if(phone==="" || phone.length!=10){
        phoneError.textContent="Enter 10 digit phone number.";
        isValid=false;
    }
    return isValid;
}

function loginCheck(){
    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;

    const emailError = document.getElementById("email-error");
    const passwordError = document.getElementById("password-error");

    emailError.textContent="";
    passwordError.textContent="";

    isValid=true;

    if(email==="" || !email.includes("@")){
        emailError.textContent="Enter valid email.";
        isValid=false;
    }
    if(password==="" || password.length<8){
        passwordError.textContent="Enter password with atleast 8 characters."
        isValid=false;
    }

    return isValid;
}
function reset() {
    // Clear error messages
    document.getElementById("name-error").textContent = "";
    document.getElementById("gender-error").textContent = "";
    document.getElementById("email-error").textContent = "";
    document.getElementById("password-error").textContent = "";
    document.getElementById("address-error").textContent = "";
    document.getElementById("phone-error").textContent = "";
}
