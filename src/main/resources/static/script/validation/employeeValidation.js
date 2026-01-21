function employeeValidation(event, type, formId) {
    event.preventDefault();

    const form = document.getElementById(formId);

    const email = document.getElementById("employee-login-email");
    const password = document.getElementById("employee-login-password");

    const emailErr = document.getElementById("employee-email-invalid");
    const passErr = document.getElementById("employee-password-invalid");

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

        const nameErr = document.getElementById("employee-name-invalid");
        const phoneErr = document.getElementById("employee-phoneNumber-invalid");
        const cpassErr = document.getElementById("employee-cpassword-invalid");

        const okName = validateName(name.value, nameErr);
        okEmail = validateEmail(email.value, emailErr);
        const okPhone = validatePhone(phone.value, phoneErr);
        okPass = validatePassword(password.value, passErr);
        const okC = validateConfirmPassword(password.value, confirm.value, cpassErr);

        if (okName && okEmail && okPhone && okPass && okC) form.submit();
    }
}
