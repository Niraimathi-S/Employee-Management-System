<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.ideas2it.employeemanagement.model.ProjectDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>update project</title>
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
<div>
      <%ProjectDTO projectDTO = (ProjectDTO)request.getAttribute("projectDTO");
      boolean isUpdated = (Boolean)request.getAttribute("isUpdated");
        if((null != projectDTO)) {%>
        <form action = "ProjectServlet" method = "post" class = "form" target="_top">
        <input type="hidden" name="type" value="update">
        <input type = "hidden" name = "projectId" value=<%=projectDTO.getProjectId() %> pattern = "[1-9][0-9]{0,4}"/><br />
         Name: <input type = "text" name = "name" value=<%=projectDTO.getName()%> pattern = "([a-zA-Z]{3,}([\\s]{1}[a-zA-Z]+)*){0,100}" /><br />
         Domain Name: <input type = "text" name = "domain" value=<%=projectDTO.getDomainName()%> pattern = "([a-zA-Z]{3,}([\\s]{1}[a-zA-Z]+)*){0,100}" /><br />
         Project Start Date: <input type = "date" value=<%=projectDTO.getStartDate()%> value="2000-01-01"  name = "start_date" /><br />
         Manager: <input type = "text" name = "manager" 
        value=<%=projectDTO.getManager()%> pattern = "([a-zA-Z]{3,}([\\s]{1}[a-zA-Z]+)*){0,100}" /> <br />
         <input type = "submit" id="submitButton" value = "Submit" />
         <input type="reset" value="Reset">
         <input type ="button" value = "Back" onclick="history.back()"/>
        <button><a href="ProjectMenu.jsp">Project menu</a></button><br />
        </form>
        <%} %>

</div>   
</body>
</html>