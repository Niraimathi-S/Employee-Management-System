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
<div>
      <%ProjectDTO projectDTO = (ProjectDTO)request.getAttribute("projectDTO");
      boolean isUpdated = (Boolean)request.getAttribute("isUpdated");
        if((null != projectDTO)) {%>
        <form action = "ProjectServlet" method = "post" name = "updateForm" target="_top">
        <input type="hidden" name="type" value="update">
        <input type = "hidden" name = "projectId" value=<%=projectDTO.getProjectId() %> pattern = "[1-9][0-9]{0,4}"/><br />
         Name: <input type = "text" name = "name" value=<%=projectDTO.getName()%> pattern = "([a-zA-Z]{3,}([\\s]{1}[a-zA-Z]+)*){0,100}" /><br />
         Domain Name: <input type = "text" name = "domain" value=<%=projectDTO.getDomainName()%> pattern = "([a-zA-Z]{3,}([\\s]{1}[a-zA-Z]+)*){0,100}" /><br />
         Project Start Date: <input type = "date" value=<%=projectDTO.getStartDate()%> value="2000-01-01"  name = "start_date" /><br />
         Manager: <input type = "text" name = "manager" 
        value=<%=projectDTO.getManager()%> pattern = "([a-zA-Z]{3,}([\\s]{1}[a-zA-Z]+)*){0,100}" /> <br />
         <input type = "submit" id="submitButton" value = "Submit" />
         <input type="reset" value="Reset">
         <button><a href="ProjectMenu.html">Back</a></button>
        </form>
       <% } else { %>
               No such project Exist!!
        <%}%>
</div>   
</body>
</html>