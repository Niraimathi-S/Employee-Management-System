<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.ideas2it.employeemanagement.model.ProjectDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Project updated</title>
<link rel="stylesheet" href="style.css">
</head>
<body><div>
      <%ProjectDTO projectDTO = (ProjectDTO)request.getAttribute("projectDTO");
        if((null != projectDTO)) {%>
        Project details updated successfully!
        <%} else{ %>
        Project details Not updated!!
        <%} %>
        </div>
</body>
</html>