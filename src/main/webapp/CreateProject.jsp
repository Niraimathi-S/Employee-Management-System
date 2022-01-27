<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.ideas2it.employeemanagement.model.ProjectDTO"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="resources/css/style.css">
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style>
.form{
	margin:0 40%;
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
    <h2>Create Project</h2>
    <div style="display:block;margin:0 auto;">
        <form:form action = "createProject" method = "post" class = "form" modelAttribute="projectDTO">
        <table>
         <tr><td>Name:</td><td> <form:input type = "text" path = "name" pattern = "([a-zA-Z]{3,}([\\s]{1}[a-zA-Z]+)*){0,100}" required = "required" /></td></tr>
         <tr><td>Domain Name:</td><td><form:input type = "text" path = "domainName" pattern = "([a-zA-Z]{3,}([\\s]{1}[a-zA-Z]+)*){0,100}" required = "required" /></td></tr>
         <tr><td>Project Start Date:</td><td><form:input type = "date" path = "startDate" required = "required" /></td></tr>
         <tr><td>Manager:</td><td><form:input type = "text" path = "manager" pattern = "([a-zA-Z]{3,}([\\s]{1}[a-zA-Z]+)*){0,100}" required = "required" /> </td></tr>
         </table><br>
         <input class="button" style="width:200px;margin-left:125px;" type = "submit" value = "Submit" />
         
        </form:form>         
        <button class="button" style="width:200px;margin-left:2%;" ><a href="ProjectMenu.jsp">Back</a></button>

        <div style="color:black;margin-left:43%;">

        <%ProjectDTO projectDTO = (ProjectDTO)request.getAttribute("returnedProject");
        if(null != projectDTO) {%>
        <%="Project Created successfully. <br>Project Id :" %>  
        <%=projectDTO.getProjectId()%>
        <%}%>
        </div>
       </div>
</body>
</html>