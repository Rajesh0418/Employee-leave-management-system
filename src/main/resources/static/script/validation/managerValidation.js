function managerValidation(event, type, formId) {
    event.preventDefault();

    const form = document.getElementById(formId);

    const email = document.getElementById("manager-login-email");
    const password = document.getElementById("manager-login-password");

    const emailErr = document.getElementById("manager-email-invalid");
    const passErr = document.getElementById("manager-password-invalid");

    let okEmail = false, okPass = false;

    if (type === "login") {
        okEmail = validateEmail(email.value, emailErr);
        okPass = validatePassword(password.value, passErr);

        if (okEmail && okPass) form.submit();
    }

    if (type === "signup") {
        const name = document.getElementById("name");
        const phone = document.getElementById("ph-num");
        const confirm = document.getElementById("confirm-password");

        const nameErr = document.getElementById("manager-name-invalid");
        const phoneErr = document.getElementById("manager-phoneNumber-invalid");
        const cpassErr = document.getElementById("manager-cpassword-invalid");

        const okName = validateName(name.value, nameErr);
        okEmail = validateEmail(email.value, emailErr);
        const okPhone = validatePhone(phone.value, phoneErr);
        okPass = validatePassword(password.value, passErr);
        const okC = validateConfirmPassword(password.value, confirm.value, cpassErr);

        if (okName && okEmail && okPhone && okPass && okC) form.submit();
    }
}
