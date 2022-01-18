<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.ideas2it.employeemanagement.model.EmployeeVO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Updated</title>
<link rel="stylesheet" href="style.css">
</head>
<body>
    <header class="header1">
    <div class="row">
    <div class="logo" style = "float: left;" >
    <img alt="Logo" src="logo.png">
    <h1>Employee Management System</h1>
    </div>
        <ul class="main-nav">
        <li> <a href = "EmployeeMenu.jsp">Employee</a><br></li>
        <li> <a href = "ProjectMenu.jsp">Project</a><br></li>
        </ul>
        </div>
    </header>
<div style="margin:10% 40%; display:block; font-size:30px;color:black">
    <%Boolean isUpdated = (Boolean)request.getAttribute("isUpdated");%>
        <%String message = (String)request.getAttribute("message");%>
        <%String error = (String)request.getAttribute("error");%>
    
    <%if (isUpdated == true) {%>
        <%=message %><br>
    <%} else {%>
        <%=error %>
    <%} %>
       <input type ="button" value = "Back" onclick="history.back()"/>
     </div>
</body>
</html>