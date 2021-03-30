// Used to validate email input
function ValidateEmail(inputText) {
    var mailformat = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;
    if(!(inputText.value.match(mailformat))) {
        alert("Invalid email address! Please redo your submition.");
        return false;

    } else {
        alert("Email validated, you're all set!");
        return true;
        
    }

}

// Used to validate phone number input
function ValidatePhone(inputNum) {
    var phoneformat = /^(1\s|1|)?((\(\d{3}\))|\d{3})(\-|\s)?(\d{3})(\-|\s)?(\d{4})$/;
    if(!(inputNum.value.match(phoneformat))) {
        alert("Invalid phone number! Please redo your submition");
        return false;
    } else {
        alert("Phone number validated, you're all set!");
        return true;
    }

}
