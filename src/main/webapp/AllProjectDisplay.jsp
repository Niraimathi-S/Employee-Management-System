<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.ideas2it.employeemanagement.model.EmployeeVO,com.ideas2it.employeemanagement.model.ProjectDTO,java.util.List,java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="style.css">
<title>All Project Display</title>
</head>
<body class="body">
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
    <h2>Project list</h2>
<div style=" display:block; font-size:20px; align-items:left;">
    <%List<ProjectDTO> projects = (ArrayList<ProjectDTO>)request.getAttribute("projects");%>
        <%if ((null == projects) || (projects.isEmpty())) {%>
        sorry..Nothing to display!!.<br>  
        <%} else {
            for(ProjectDTO projectDTO:projects) {%>
        <table style="margin-left:40%;text-align:left;">
        <tr>
           <td>Project ID     :<%=projectDTO.getProjectId()%></td>
        </tr>
        <tr>
           <td>Project Name   :<%=projectDTO.getName()%></td>
           <td>Start Date     :<%=projectDTO.getStartDate()%></td>
        </tr>
        <tr>
           <td>Project Manager:<%=projectDTO.getManager()%></td>
           <td>Domain         :<%=projectDTO.getDomainName()%></td>
        </tr>

        <%if (!projectDTO.getEmployeesVO().isEmpty()) {%>
        <tr>
          <td> Employees:</td>
          <td>        
          <table border="1">
            <tr>
              <th>Employee Id</th>
              <th>Name</th>
              <th>DOB</th>
              <th>Email</th>
              <th>Mobile number</th>
              <th>Salary</th>
            </tr>
        <%for (EmployeeVO employeeVO:projectDTO.getEmployeesVO()){ %>
            <tr>
              <td><%=employeeVO.getEmployeeId()%></td>
              <td><%=employeeVO.getName()%></td>
              <td><%=employeeVO.getDateOfBirth()%></td>
              <td><%=employeeVO.getEmail()%></td>
              <td><%=employeeVO.getMobileNumber()%></td>
              <td><%=employeeVO.getSalary()%></td>
            </tr>
        <%} %>
        </table>
        </td>
        </tr>
        <%} %>
        </table>
        <br><br><br>
        <%} %>
        <%} %>
        <a href = "ProjectMenu.jsp">Main menu </a><br>
        </div>
</body>
</html>