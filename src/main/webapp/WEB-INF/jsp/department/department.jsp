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
<title>Manage Departments</title>
<link  href="${pageContext.request.contextPath}/css/mainCSS.css" rel="stylesheet">
</head>
<body>
<div class="sidebar">
  <h2>HYSCALER</h2><br><hr></hr>
    <a  href="/manager/dashboard">Dashboard</a>
    <a  href="/manager/manager-profile">My Profile</a>
    <a  href="/employee/employees">Employees</a>
    <a  href="/manager/employee-leave-list">Leave</a>
    <a  class="active" href="/department/departments">Department</a>
    <a  href="/manager/change-password">Change Password</a>
</div>

<div class="main">
  <div class="topbar">
    <div>Welcome, ${userdata.name}</div>
    <a href="/logout" class="logout">Logout</a>
  </div>

  <div class="content">
    <div class="header-row">
      <h2>Departments List</h2>
    </div>

    <table>
      <thead>
        <tr>
          <th>S No</th>
          <th>Department</th>
          <th>Number of employees in each Department</th>
        </tr>
      </thead>
      <tbody>
        <c:forEach var="d" items="${departments}" varStatus="s">
          <tr>
            <td>${s.count}</td>
            <td>${d.department_name}</td>
            <td>${d.no_of_employees}</td>
          </tr>
        </c:forEach>
      </tbody>
    </table>
  </div>
</div>
</body>
</html>
