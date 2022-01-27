<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.ideas2it.employeemanagement.model.ProjectDTO"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>update project</title>
<link rel="stylesheet" href="resources/css/style.css">
</head>
<style>
.button{
	width:200px;
}
</style>
<body>
    <header class="header1">
    <div class="row">
    <div class="logo" style = "float: left;" >
    <img alt="Logo" src="resources/css/logo.png">
    <h1>Employee Management System</h1>
    </div>
        <ul class="main-nav">
        <li> <a href = "EmployeeMenu.jsp">Employee</a><br></li>
        <li> <a href = "ProjectMenu.jsp">Project</a><br></li>
        </ul>
        </div>
    </header>
    <h2>Update Project</h2>
<div class="display-single-div">
      <%ProjectDTO projectDTO = (ProjectDTO)request.getAttribute("projectDTO");
      boolean isUpdated = (Boolean)request.getAttribute("isUpdated");
        if((null != projectDTO)) {%>
        <form:form action = "updateProject" style="margin:0 38%;" method = "post" class = "form" modelAttribute="projectDTO">
        <table>
        <form:hidden path="projectId"></form:hidden>
         <tr><td>Name:</td><td> <form:input type = "text" path = "name" pattern = "([a-zA-Z]{3,}([\\s]{1}[a-zA-Z]+)*){0,100}" /></td></tr>
         <tr><td>Domain Name:</td><td><form:input type = "text" path = "domainName" pattern = "([a-zA-Z]{3,}([\\s]{1}[a-zA-Z]+)*){0,100}"/></td></tr>
         <tr><td>Project Start Date:</td><td><form:input type = "date" path = "startDate" /></td></tr>
         <tr><td>Manager:</td><td><form:input type = "text" path = "manager" pattern = "([a-zA-Z]{3,}([\\s]{1}[a-zA-Z]+)*){0,100}" /> </td></tr>
         </table><br>
         <input  style="margin:0 31%;" class="button" type = "submit" id="submitButton" value = "Submit" />
         <input style="margin:0 31%;" class="button" type="submit" formaction="viewSingleProject" value="Back">
        </form:form><br>
        <button class="button"><a href="ProjectMenu.jsp">Project menu</a></button><br />
        <%} %>

</div>   
</body>
</html>