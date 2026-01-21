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
<title>Manager Details</title>
<link href="${pageContext.request.contextPath}/css/mainCSS.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/profile.css" rel="stylesheet">
</head>

<body>
<div class="sidebar">
    <h2>HYSCALER</h2><br><hr></hr>
    <a  href="/manager/dashboard">Dashboard</a>
    <a  class="active" href="/manager/manager-profile">My Profile</a>
    <a  href="/employee/employees">Employees</a>
    <a  href="/manager/employee-leave-list">Leave</a>
    <a  href="/department/departments">Department</a>
    <a  href="/manager/change-password">Change Password</a>
</div>

<div class="main">
    <div class="topbar">
        <div>Welcome, ${userdata.name}</div>
        <a href="/logout" class="logout">Logout</a>
    </div>

    <div class="content">
        <div class="card">
            <h2 style="text-align:center;">Manager Details</h2>

            <div class="details-box">
                <div class="info">
                    <p><b>Employee ID :</b> ${userdata.id}</p>
                    <p><b>Employee Name :</b> ${userdata.name}</p>
                    <p><b>E-mail id :</b> ${userdata.email}</p>
                    <p><b>Phone Number :</b> ${userdata.phoneNumber}</p>
                    <p><b>Gender :</b> ${userdata.gender}</p>
                    <p><b>Password :</b> ${userdata.password}</p>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
