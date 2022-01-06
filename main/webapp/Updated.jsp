<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.ideas2it.employeemanagement.model.EmployeeVO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Updated</title>
<link rel="stylesheet" href="style.css">
</head>
<body><div>
    <%Boolean isUpdated = (Boolean)request.getAttribute("isUpdated");%>
        <%String message = (String)request.getAttribute("message");%>
        <%String error = (String)request.getAttribute("error");%>
    
    <%if (isUpdated == true) {%>
        <%=message %><br>
    <%} else {%>
        <%=error %>
    <%} %>
     <button><a href="UpdateEmployee.jsp">Update Menu</a></button>
     <button><a href="EmployeeMenu.jsp">Employee Main menu</a></button>
     </div>
</body>
</html>