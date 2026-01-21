function employeeLogin() {
      //error
      const error=document.getElementById("employee-login-error");

      //employee input
      const employeeEmail=document.getElementById("employee-login-email");
      const employeePassword=document.getElementById("employee-login-password")

      const email = employeeEmail.value.trim();
      const password = employeePassword.value.trim();
      if(email==="" || password===""){
        error.textContent = "All fields are required";
        return;
      }
}

function managerLogin() {
      //error
      const error=document.getElementById("manager-login-error");

      //employee input
      const managerEmail=document.getElementById("manager-login-email");
      const managerPassword=document.getElementById("manager-login-password")

      //invalid
      const managerInvalid=document.getElementById("manager-email-invalid")
      const passwordInvalid=document.getElementById("manager-password-invalid")

      const email = managerEmail.value.trim();
      const password = managerPassword.value.trim();
      if(email==="" || password===""){
        error.textContent = "All fields are required";
        return;
      }
}