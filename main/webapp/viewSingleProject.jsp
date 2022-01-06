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
<div>
    <form action = "ProjectServlet" method = "post">
       <input type="hidden" name="type" value="view">
       Project Id:<input type = "text" name = "projectId" pattern = "[1-9][0-9]{0,4}"/><br/>
        <input type = "submit" value ="Submit"/><br/>
    </form>
</div>
</body>
</html>