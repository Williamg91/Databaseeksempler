const loginForm = document.getElementById("login-form");
const loginButton = document.getElementById("login-form-submit");
const loginErrorMsg = document.getElementById("login-error-msg");

var CPRFelt = document.getElementById("username-field");
var password = document.getElementById("password-field")


loginButton.addEventListener("click", (e) => {
    e.preventDefault();
    const CPR = loginForm.CPR.value;
    const Kodeord = loginForm.Kodeord.value;
    

    if (CPR === "user" && Kodeord === "1") {
        location.replace('su5.eduhost.dk/cgi-bin/CGIget?' + 'CPR=' + CPR + '&' + Kodeord+
        '=' + Kodeord
    )

        //  alert("You have successfully logged in.");


    } else {
        loginErrorMsg.style.opacity = 1;
    }
})
password.onfocus = function() {
    document.getElementById("message").style.display = "block";
}

    password.onkeyup = function() {
        // Validate lowercase letters
        var lowerCaseLetters = /[a-z]/g;
        if(password.value.match(lowerCaseLetters)) {
            letter.classList.remove("invalid");
            letter.classList.add("valid");
        } else {
            letter.classList.remove("valid");
            letter.classList.add("invalid");
        }

        // Validate capital letters
        var upperCaseLetters = /[A-Z]/g;
        if(password.value.match(upperCaseLetters)) {
            capital.classList.remove("invalid");
            capital.classList.add("valid");
        } else {
            capital.classList.remove("valid");
            capital.classList.add("invalid");
        }

        // Validate numbers
        var numbers = /[0-9]/g;
        if(password.value.match(numbers)) {
            number.classList.remove("invalid");
            number.classList.add("valid");
        } else {
            number.classList.remove("valid");
            number.classList.add("invalid");
        }

        // Validate length
        if(password.value.length >= 8) {
            length.classList.remove("invalid");
            length.classList.add("valid");
        } else {
            length.classList.remove("valid");
            length.classList.add("invalid");
        }
    }

