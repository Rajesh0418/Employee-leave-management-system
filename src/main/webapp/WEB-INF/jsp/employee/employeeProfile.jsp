<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
response.setHeader("Pragma", "no-cache"); // HTTP 1.0
response.setDateHeader("Expires", 0); // Proxies
%>
<c:if test="${empty sessionScope.employeeData}">
    <c:redirect url="/employee/employee-login"/>
</c:if>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Employee Details</title>
<link href="${pageContext.request.contextPath}/css/mainCSS.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/profile.css" rel="stylesheet">
</head>
<body>
 <div class="sidebar">
    <h2>HYSCALER</h2><br>
    <hr></hr>
    <a href="/employee/employee-leave-request-status">Leave</a>
    <a class="active" href="/employee/employee-profile" >My Profile</a>
    <a href="/employee/change-password">Change Password</a>
 </div>

<div class="main">
    <div class="topbar">
        <div>Welcome, ${employeeData.name}</div>
        <a href="/employee/logout" class="logout">Logout</a>
    </div>

    <div class="content">
        <div class="card">
            <h2 style="text-align:center;">Employee Details</h2>

            <div class="details-box">
                <div class="info">
                    <p><b>Employee ID :</b> ${employeeData.id}</p>
                    <p><b>Employee Name :</b> ${employeeData.name}</p>
                    <p><b>E-mail id :</b> ${employeeData.email}</p>
                    <p><b>Department :</b> ${employeeData.department}</p>
                    <p><b>Phone Number :</b> ${employeeData.phoneNumber}</p>
                    <p><b>Gender :</b> ${employeeData.gender}</p>
                    <p><b>Password :</b> ${employeeData.password}</p>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
