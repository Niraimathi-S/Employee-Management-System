<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="style.css">
<title>View Single Employee</title>
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
<div  style=" display:block; font-size:30px; margin:10% 41%;align-items: left;">
    <form action = "EmployeeServlet" method = "post" class="form">
       <input type="hidden" name="type" value="viewSingle">
       Employee Id:<input type = "text" name = "employeeId" pattern = "[1-9][0-9]{0,4}"/><br><br>
        <input class="view-button" type = "submit" value ="Submit"/><br>
    <button class="view-button"><a href="EmployeeMenu.jsp">Back</a></button>
        
    </form>
</div>
</body>
</html>