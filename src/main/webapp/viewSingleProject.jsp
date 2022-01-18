<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>View single project</title>
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
    <div style="display:block;margin:100px auto;">
    <form action = "ProjectServlet" method = "post">
       <input type="hidden" name="type" value="view">
       Project Id:<input type = "text" name = "projectId" pattern = "[1-9][0-9]{0,4}"/><br><br>
        <input class="view-button" type = "submit" value ="Submit"/><br/>
    </form>
   <button class="view-button"><a href="ProjectMenu.jsp">Project Menu</a></button>
</div>
</body>
</html>