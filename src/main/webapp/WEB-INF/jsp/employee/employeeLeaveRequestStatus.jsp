<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

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
<title>Manage Leaves</title>
<link  href="${pageContext.request.contextPath}/css/mainCSS.css" rel="stylesheet">
<style>
    .sidebar h2{
        margin-bottom:18px;
    }
</style>
</head>
<body>
<div class="sidebar">
  <h2>HYSCALER</h2>
  <hr></hr>
    <a class="active" href="/employee/employee-leave-request-status">Leave</a>
    <a href="/employee/employee-profile">My Profile</a>
    <a href="/employee/change-password">Change Password</a>
</div>

<div class="main">
  <div class="topbar">
    <div>Welcome, ${employeeData.name}</div>
    <form action="logout" method="post">
        <a href="/employee/logout" class="logout" >Logout</a>
    </form>
  </div>

  <div class="content">
    <div class="header-row">
      <h2>Manage Leaves</h2>
      <div>
        <a href="/employee/leave-request-form" class="logout">Leave Request</a>
      </div>
    </div>

    <table>
      <thead>
        <tr>
          <th>S No</th>
          <th>Leave Type</th>
          <th>Start Date</th>
          <th>End Date</th>
          <th>Reason</th>
          <th>Status</th>
        </tr>
      </thead>
      <c:if test="${empty leaveRequests}">
          <tr>
              <td colspan="8">No leave requests found</td>
          </tr>
      </c:if>
      <c:forEach var="lv" items="${leaveRequests}" varStatus="s">
          <tr>
              <td>${s.count}</td>
              <td>${lv.type}</td>
              <td>${lv.startDate}</td>
              <td>${lv.endDate}</td>
              <td>${lv.reason}</td>
              <td>
                  <span>${lv.status}</span>
              </td>
          </tr>
      </c:forEach>
    </table>
  </div>
</div>
</body>
</html>

