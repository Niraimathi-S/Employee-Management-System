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
         <button><a href = "viewSingleProject.jsp">View single Project </a></button><br>
         <form action="ProjectServlet" method="post">
       <input type="hidden" name="type" value="viewAll">
       <input type="submit" value="View all projects">
         </form>
         <a href = "ProjectMenu.jsp">Back </a><br>
        </ul>
</body>
</html>