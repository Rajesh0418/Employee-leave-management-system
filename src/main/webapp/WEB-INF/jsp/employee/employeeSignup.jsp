<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Sign Up | Leave Management System</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/signup.css">
</head>
<body>

<div class="signup-box">
    <form class="form" id="employee-signup-form"
          action="${pageContext.request.contextPath}/employee/add-employee" method="post">

        <h2>CREATE EMPLOYEE ACCOUNT</h2>

        <div>
            <label>Enter your name</label>
            <input id="name" name="name" type="text" placeholder="Enter your name here" required>
        </div>
        <span id="employee-name-invalid"></span>
        <div>
            <label>Enter your E-mail</label>
            <input id="employee-login-email" name="email" type="email" placeholder="Enter your E-mail here" required>
        </div>
        <span id="employee-email-invalid"></span>
        <div>
            <label>Select your Department</label>
            <select name="department" required>
                <option value="">-- Select your Department --</option>
                <option value="Front-end developer">Front-end developer</option>
                <option value="Back-end developer">Back-end developer</option>
                <option value="Full stack developer">Full stack developer</option>
                <option value="Cloud Engineer">Cloud Engineer</option>
                <option value="Associate Software Engineer">Associate Software Engineer</option>
                <option value="Devops Engineer">Devops Engineer</option>
            </select>
        </div>

        <!-- Manager List Dropdown -->
        <div>
            <label>Select Manager</label>
            <select name="managerId" required>
                <option value="">-- Select Manager --</option>
                <c:forEach var="m" items="${managers}">
                    <option value="${m.id}">${m.name}</option>
                </c:forEach>
            </select>
        </div>

        <div>
            <label>Enter your phone number</label>
            <input id="ph-num" name="phoneNumber" type="text" placeholder="Enter your phone number here" required>
        </div>
        <span id="employee-phoneNumber-invalid"></span>
        <div>
            <label>Select your gender</label>
            <select name="gender" required>
                <option value="">-- Select Gender --</option>
                <option value="Male">Male</option>
                <option value="Female">Female</option>
                <option value="Other">Other</option>
            </select>
        </div>

        <div>
            <label>Create a password</label>
            <div class="show-password">
                <input id="employee-login-password" name="password" type="password" placeholder="Enter your password" required>
                <span onclick="togglePassword(this,'employee-login-password')">🙈</span>
            </div>
        </div>
        <span id="employee-password-invalid"></span>
        <div>
            <label>Confirm password</label>
            <div class="show-password">
                <input id="confirm-password" type="password" placeholder="Enter your confirm password" required>
                <span onclick="togglePassword(this,'confirm-password')">🙈</span>
            </div>
        </div>
        <span id="employee-cpassword-invalid"></span>
        <button onclick="employeeValidation(event,'signup','employee-signup-form')">Submit</button>
    </form>
</div>

<script src="${pageContext.request.contextPath}/script/toggle.js"></script>
<script src="${pageContext.request.contextPath}/script/validation/commonValidation.js"></script>
<script src="${pageContext.request.contextPath}/script/validation/employeeValidation.js"></script>
</body>
</html>
