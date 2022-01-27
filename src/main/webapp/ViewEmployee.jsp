<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="resources/css/style.css">
<title>View Menu</title>
</head>
<body>
    <header class="header1">
    <div class="row">
    <div class="logo" style = "float: left;" >
    <img alt="Logo" src="resources/css/logo.png">
    <h1>Employee Management System</h1>
    </div>
        <ul class="main-nav">
        <li> <a href = "EmployeeMenu.jsp">Employee</a><br></li>
        <li> <a href = "ProjectMenu.jsp">Project</a><br></li>
        </ul>
        </div>
    </header>
    <div class="view" style="display:block;margin:200px auto">
       <button class="button"><a href= "ViewSingleEmployee.jsp">View single Employee </a></button>
       <form action = "viewAll" method = "post">
       <input class="button" type = "submit" value = "View All Employee"/>
       </form><br>
       <button class="button"><a href = "EmployeeMenu.jsp">Back </a></button><br>
     </div> 
</body>
</html>