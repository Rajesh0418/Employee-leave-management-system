<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
response.setHeader("Pragma", "no-cache"); // HTTP 1.0
response.setDateHeader("Expires", 0);
%>
<c:if test="${empty sessionScope.employeeData}">
    <c:redirect url="/employee/employee-login"/>
</c:if>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Request Leave</title>
<link href="${pageContext.request.contextPath}/css/mainCSS.css" rel="stylesheet">
    <style>
        body {
            margin: 0;
            font-family: Arial, sans-serif;
            background: #f4f6f9;
        }

        .content {
            flex: 1;
            padding: 30px;
        }

        .card {
            background: white;
            padding: 25px;
            border-radius: 6px;
            width: 600px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }

        .card h2 {
            margin-bottom: 20px;
        }

        .form-group {
            margin-bottom: 15px;
        }

        .form-group label {
            display: block;
            margin-bottom: 5px;
        }

        .form-group input,
        .form-group select,
        .form-group textarea {
            width: 100%;
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        .row {
            display: flex;
            gap: 15px;
        }

        .btn {
            width: 100%;
            padding: 10px;
            background: #0d9c8a;
            color: white;
            border: none;
            border-radius: 4px;
            font-size: 16px;
            cursor: pointer;
        }

        .btn:hover {
            background: #0b8374;
        }
    </style>
</head>
<body>
<div class="sidebar">
    <h2>HYSCALER</h2><br>
    <hr></hr>
    <a class="active" href="/employee/employee-leave-request-status">Leave</a>
    <a href="/employee/employee-profile">My Profile</a>
    <a href="/employee/change-password">Change Password</a>
</div>

<div class="main">
    <div class="topbar">
        <div>Welcome, ${employeeData.name}</div>
        <a class="logout" href="/employee/logout" style="color:white;text-decoration:none;">Logout</a>
    </div>

    <div class="content">
        <div class="card">
            <h2 style="text-align:center;">Request for Leave</h2>
            <div class="details-box">
                <div class="info">
                <form action="/employee/submit-leave-request" method="post">
                    <div class="form-group">
                        <label>Leave Type</label>
                        <select name="type">
                            <option>Casual Leave</option>
                            <option>Sick Leave</option>
                            <option>Vacation Leave</option>
                        </select>
                    </div>

                    <div class="row">
                        <div class="form-group" style="flex:1;">
                            <label>From Date</label>
                            <input type="date" name="startDate">
                        </div>
                        <div class="form-group" style="flex:1;">
                            <label>To Date</label>
                            <input type="date" name="endDate">
                        </div>
                    </div>

                    <div class="form-group">
                        <label>Description</label>
                        <textarea name="reason" rows="3"></textarea>
                    </div>
                    <button class="btn" type="submit">Apply Leave</button>
                </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
