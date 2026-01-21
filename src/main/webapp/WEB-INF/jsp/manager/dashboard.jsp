<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
response.setHeader("Pragma", "no-cache"); // HTTP 1.0
response.setDateHeader("Expires", 0); // Proxies
%>
<c:if test="${empty sessionScope.userdata}">
    <c:redirect url="/manager/manager-login"/>
</c:if>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Dashboard</title>
<link  href="${pageContext.request.contextPath}/css/mainCSS.css" rel="stylesheet">
<style>
    .cards {
        display: grid;
        grid-template-columns: repeat(auto-fit, minmax(220px, 1fr));
        gap: 20px;
        margin-top: 20px;
    }
    .card {
        background: #fff;
        padding: 20px;
        border-radius: 6px;
        display: flex;
        justify-content: space-between;
        align-items: center;
    }
    .card .value {
        font-size: 22px;
        font-weight: bold;
    }
    .green {
        border-left: 6px solid #1abc9c;
    }
    .yellow {
        border-left: 6px solid #f1c40f;
    }
    .red {
        border-left: 6px solid #e74c3c;
    }
    .section-title {
        margin-top: 40px;
        text-align: center;
    }
    .sidebar h2{
       margin-bottom:18px;
    }
</style>
</head>

<body>
<div class="sidebar">
  <h2>HYSCALER</h2><hr></hr>
  <a class="active" href="/manager/dashboard">Dashboard</a>
  <a  href="/manager/manager-profile">My Profile</a>
  <a  href="/employee/employees">Employees</a>
  <a href="/manager/employee-leave-list">Leave</a>
  <a  href="/department/departments">Department</a>
  <a  href="/manager/change-password">Change Password</a>
</div>

<div class="main">
  <div class="topbar">
    <div>Welcome, ${userdata.name}</div>
    <a href="/logout" class="logout" >Logout</a>
  </div>

  <div class="content">
    <h2>Dashboard Overview</h2>

    <div class="cards">
      <div class="card green">
        <div>Total Employees</div>
        <div class="value">${empCount}</div>
      </div>
      <div class="card yellow">
        <div>Total Departments</div>
        <div class="value">${deptCount}</div>
      </div>
    </div>
  </div>
</div>
</body>
</html>
