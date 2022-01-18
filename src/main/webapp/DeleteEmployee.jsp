<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.ideas2it.employeemanagement.model.EmployeeVO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Delete single Employee</title>
<link rel="stylesheet" href="style.css">
</head>
<body>
    <form action = "DeleteEmployeeServlet" class = "form" method = "post">
       <input type="hidden" name="type" value="delete">
       Employee Id:<input type = "text" name = "employeeId" pattern = "[1-9][0-9]{0,4}"/><br />
         <input type = "submit" value = "Submit" /><br />
         <input type="reset" value="Reset"><br />
         <button><a href="EmployeeMenu.jsp">Back</a></button>
    </form>
</body>
</html>