function newPasswordValidation(event, formId) {
    event.preventDefault();

    const form = document.getElementById(formId);

    const password = document.getElementById("password");
    const confirmPassword = document.getElementById("confirm-password");

    const passErr = document.getElementById("password-err");
    const cPassErr = document.getElementById("confirm-password-err");

    let okPass = false,okConfirmPass = false;

    okPass = validatePassword(password.value, passErr);
    okConfirmPass = validateConfirmPassword(password.value, confirmPassword.value, cPassErr);

    if (okPass && okConfirmPass) form.submit();
}
