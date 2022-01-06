<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.ideas2it.employeemanagement.model.ProjectDTO"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="style.css">
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
        <form action = "ProjectServlet" method = "post">
        <input type="hidden" name="type" value="create">
         Name: <input type = "text" name = "name" pattern = "([a-zA-Z]{3,}([\\s]{1}[a-zA-Z]+)*){0,100}" /><br />
         Domain Name: <input type = "text" name = "domain" pattern = "([a-zA-Z]{3,}([\\s]{1}[a-zA-Z]+)*){0,100}" /><br />
         Project Start Date: <input type = "date" name = "start_date" /><br />
         Manager: <input type = "text" name = "manager" pattern = "([a-zA-Z]{3,}([\\s]{1}[a-zA-Z]+)*){0,100}" /> <br />
         <input type = "submit" value = "Submit" />
         <input type="reset" value="Reset">
         <button><a href="ProjectMenu.html">Back</a></button>
         
        </form>
        <%ProjectDTO projectDTO = (ProjectDTO)request.getAttribute("returnedProjectDTO");
        if(null != projectDTO) {%>
        Project Created successfully. <br>Employee Id :  
        <%=projectDTO.getProjectId()%>
        <%}%>
</body>
</html>