<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
response.setHeader("Pragma", "no-cache");
response.setDateHeader("Expires", 0);
%>

<c:if test="${empty sessionScope.userdata}">
    <c:redirect url="/manager/manager-login"/>
</c:if>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Change Password</title>
<link href="${pageContext.request.contextPath}/css/mainCSS.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/changePassword.css" rel="stylesheet">
</head>

<body>
<div class="sidebar">
    <h2>HYSCALER</h2><br>
    <hr></hr>
    <a  href="/manager/dashboard">Dashboard</a>
    <a  href="/manager/manager-profile">My Profile</a>
    <a  href="/employee/employees">Employees</a>
    <a  href="/manager/employee-leave-list">Leave</a>
    <a  href="/department/departments">Department</a>
    <a  class="active" href="/manager/change-password">Change Password</a>
</div>

<div class="main">
    <div class="topbar">
        <div>Welcome, ${userdata.name}</div>
        <a href="/logout" class="logout">Logout</a>
    </div>

    <div class="content">
            <div class="card">
                <h2>Change Password</h2>
                <form id="manager-change-password" action="/manager/new-password" method="post">
                    <div class="form-group">
                        <label>New Password</label>
                        <div class="show-password">
                            <input id="password" type="password" name="password" placeholder="New Password" required>
                            <span id="toggle-show-confirm-password" onclick="togglePassword(this,'password')">🙈</span>
                        </div>
                    </div>
                    <span id="password-err"></span>

                    <div class="form-group">
                        <label>Confirm Password</label>
                        <div class="show-password">
                            <input id="confirm-password" type="password" name="confirmPassword" placeholder="Confirm Password" required>
                            <span id="toggle-show-confirm-password" onclick="togglePassword(this,'confirm-password')">🙈</span>
                        </div>
                    </div>
                    <span id="confirm-password-err"></span>
                    <button onclick="newPasswordValidation(event,'manager-change-password')" class="btn" type="submit">Change Password</button>
                </form>
            </div>
    </div>
</div>
<script src="${pageContext.request.contextPath}/script/validation/commonValidation.js"></script>
<script src="${pageContext.request.contextPath}/script/validation/newPassword.js"></script>
<script src="${pageContext.request.contextPath}/script/toggle.js"></script>
</body>
</html>
