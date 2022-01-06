<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="style.css">
<title>View Menu</title>
</head>
<body>
       <button><a href = "ViewSingleEmployee.jsp">View single Employee </a><br></button>
       <form action = "EmployeeServlet" method = "post">
       <input type="hidden" name="type" value="viewAll">
       <input type = "submit" value = "View All Employee"/><br />
       </form>
       <a href = "EmployeeMenu.jsp">Back </a><br>
      
</body>
</html>