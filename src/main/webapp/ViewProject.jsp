<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="style.css">
<title>View project</title>
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
    <div style="display:block;margin:100px auto;">
         <button style="border-radius: 20px;width:400px" class="button"><a style="color:black" href = "viewSingleProject.jsp">View single Project </a></button><br>
         <form action="ProjectServlet" method="post">
         <input type="hidden" name="type" value="viewAll">
         <input style="border-radius: 20px;width:400px" class="button" type="submit" value="View all projects">
         </form><br>
         <button><a style="background-color:white;" href = "ProjectMenu.jsp">Back </a><br></button>
    </div>
</body>
</html>