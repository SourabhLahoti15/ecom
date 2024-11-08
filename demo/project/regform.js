function validateForm(){
    const name = document.getElementById("name").value;
    const address = document.getElementById("address").value;
    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;
    const college = document.getElementById("college").value;
    const subject = document.getElementById("subject").value;
    const agree = document.getElementById("agree").checked;

    const nameError = document.getElementById("name-error");
    const addressError = document.getElementById("address-error");
    const emailError = document.getElementById("email-error");
    const passwordError = document.getElementById("password-error");
    const collegeError = document.getElementById("college-error");
    const subjectError = document.getElementById("subject-error");
    const agreeError = document.getElementById("agree-error");

    nameError.textContent="";
    addressError.textContent="";
    emailError.textContent="";
    passwordError.textContent="";
    subjectError.textContent="";
    collegeError.textContent="";
    agreeError.textContent="";
    
    let isValid = true;

    if(name==="" || /\d/.test(name)){
        nameError.textContent="Enter your name properly.";
        isValid=false;
    }
    if(address===""){
        addressError.textContent="Enter your address.";
        isValid=false;
    }
    if(email==="" || !email.includes("@")){
        emailError.textContent="Enter valid email.";
        isValid=false;
    }
    if(password==="" || password.length<8){
        passwordError.textContent="Enter password with atleast 8 characters."
        isValid=false;
    }
    if(college===""){
        collegeError.textContent="Enter college name.";
        isValid=false;
    }
    if(subject===""){
        subjectError.textContent="Enter subject."
        isValid=false;
    }
    if(!agree){
        agreeError.textContent="Agree to the above information."
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