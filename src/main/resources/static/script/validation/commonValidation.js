// regex
const emailRegex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
const passwordRegex = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@#$!%*?&])[A-Za-z\d@#$!%*?&]{8,}$/;
const phoneNumberRegex = /^(?:\+91|0)?[6-9]\d{9}$/;

// helpers
function setErrorMessage(el, msg) {
    el.innerText = msg;
    el.style.color = "red";
}

function setSuccessMessage(el, msg) {
    el.innerText = msg;
    el.style.color = "green";
}

function validateEmail(value, el) {
    if (value === "") {
        setErrorMessage(el, "❌ Email should not be empty");
        return false;
    }
    if (!emailRegex.test(value)) {
        setErrorMessage(el, "❌ Invalid email format");
        return false;
    }
    setSuccessMessage(el, "✅ Email is okay");
    return true;
}

function validatePassword(value, el) {
    if (value === "") {
        setErrorMessage(el, "❌ Password should not be empty");
        return false;
    }
    if (!passwordRegex.test(value)) {
        setErrorMessage(el, "❌ Must contain letters, numbers & special chars");
        return false;
    }
    setSuccessMessage(el, "✅ Password is okay");
    return true;
}

function validateName(value, el) {
    if (value === "") {
        setErrorMessage(el, "❌ Name should not be empty");
        return false;
    }
    if (value.length < 3) {
        setErrorMessage(el, "❌ Minimum 3 characters");
        return false;
    }
    setSuccessMessage(el, "✅ Name is okay");
    return true;
}

function validatePhone(value, el) {
    if (value === "") {
        setErrorMessage(el, "❌ Phone should not be empty");
        return false;
    }
    if (!phoneNumberRegex.test(value)) {
        setErrorMessage(el, "❌ Invalid phone number");
        return false;
    }
    setSuccessMessage(el, "✅ Phone is okay");
    return true;
}

function validateConfirmPassword(p, cp, el) {
    if (cp === "") {
        setErrorMessage(el, "❌ Confirm password required");
        return false;
    }
    if (p !== cp) {
        setErrorMessage(el, "❌ Passwords do not match");
        return false;
    }
    setSuccessMessage(el, "✅ Matched");
    return true;
}
