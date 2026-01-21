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
<title>Manage Employees</title>
<link  href="${pageContext.request.contextPath}/css/mainCSS.css" rel="stylesheet">
<style>
    .sidebar h2{
        margin-bottom:18px;
    }
</style>
</head>
<body>
<div class="sidebar">
    <h2>HYSCALER</h2><hr></hr>
      <a  href="/manager/dashboard">Dashboard</a>
      <a  href="/manager/manager-profile">My Profile</a>
      <a  class="active" href="/employee/employees">Employees</a>
      <a  href="/manager/employee-leave-list">Leave</a>
      <a  href="/department/departments">Department</a>
      <a  href="/manager/new-password">Change Password</a>
</div>

<div class="main">
  <div class="topbar">
    <div>Welcome, ${userdata.name}</div>
    <form action="logout" method="post">
            <a href="/logout" class="logout" >Logout</a>
    </form>
  </div>

  <div class="content">
    <div class="header-row">
      <h2>Employees List</h2>
    </div>

    <table>
      <thead>
        <tr>
          <th>S No</th>
          <th>Name</th>
          <th>Department</th>
          <th>Phone Number</th>
          <th>E-Mail</th>
          <th>Sex</th>
        </tr>
      </thead>
      <tbody>
        <c:forEach var="emp" items="${employees}" varStatus="s">
          <tr>
            <td>${s.count}</td>
            <td>${emp.name}</td>
            <td>${emp.department}</td>
            <td>${emp.phoneNumber}</td>
            <td>${emp.email}</td>
            <th>${emp.gender}</th>
          </tr>
        </c:forEach>
      </tbody>
    </table>
  </div>
</div>
</body>
</html>
