<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="style.css">s
<title>Assign & unassign project</title>
</head>
<body>
<div>
    <form action = "EmployeeServlet" method = "get">
       <input type="hidden" name="type" value="assisgn&unassign">
       Employee Id:<input type = "text" name = "employeeId" pattern = "[1-9][0-9]{0,4}"/><br/>
        <input type = "submit" value ="Submit"/><br/>
    </form>
</div>
</body>
</html>