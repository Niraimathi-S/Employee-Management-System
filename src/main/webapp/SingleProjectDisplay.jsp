<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.ideas2it.employeemanagement.model.EmployeeVO,com.ideas2it.employeemanagement.model.ProjectDTO"
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Single project Display</title>
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
<div class="display-single-div">
    <%ProjectDTO projectDTO = (ProjectDTO)request.getAttribute("returnedProjectDTO");%>
        <%if (null == projectDTO) {%>
        <%="sorry..No such project ID exists!!." %><br>  
        <%} else {%>
        <table style="margin: 100px auto auto auto;text-align:left;">
        <tr>
           <td>Project Details:</td>
        </tr>
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
        </table>
       <form action = "ProjectServlet" method = "post" style="margin:auto;">
       <input type="hidden" name="type" value="update">
       <input type = "hidden" name = "projectId" value=<%=projectDTO.getProjectId() %> pattern = "[1-9][0-9]{0,4}"/>
        <input type = "hidden" name = "name" value=<%=projectDTO.getName()%> pattern = "([a-zA-Z]{3,}([\\s]{1}[a-zA-Z]+)*){0,100}" /> 
        <input type = "hidden" name = "domain" value=<%=projectDTO.getDomainName()%> pattern = "([a-zA-Z]{3,}([\\s]{1}[a-zA-Z]+)*){0,100}" /> 
        <input type = "hidden" value=<%=projectDTO.getStartDate()%> value="2000-01-01"  name = "start_date" /> 
        <input type = "hidden" name = "manager" value=<%=projectDTO.getManager()%> pattern = "([a-zA-Z]{3,}([\\s]{1}[a-zA-Z]+)*){0,100}" />  
       <input type = "submit" value = "Update"/><br/>
       </form>
       <form action = "ProjectServlet" method = "post">
       <input type="hidden" name="type" value="delete">
       <input type = "hidden" name = "projectId" value=<%=projectDTO.getProjectId() %> pattern = "[1-9][0-9]{0,4}"/> 
       <input type = "submit" value = "Delete"/><br />
       </form>
        <%if (!projectDTO.getEmployeesVO().isEmpty()) {%>
        <br>
            Employees:
            <table border="1" style="margin: 0px auto;">
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
        <%} %>
       <form action = "ProjectServlet" method = "get">
       <input type="hidden" name="type" value="assign&unassign">
       <input type = "hidden" name = "projectId" value="<%=projectDTO.getProjectId() %>" pattern = "[1-9][0-9]{0,4}"/> 
       <input type="hidden" name="action" value="assign">
       <input type = "submit" value = "Assign"/><br />
       </form>
       <form action = "ProjectServlet" method = "get">
       <input type="hidden" name="type" value="assign&unassign">
       <input type = "hidden" name = "projectId" value="<%=projectDTO.getProjectId() %>" pattern = "[1-9][0-9]{0,4}"/> 
       <input type="hidden" name="action" value="unassign">
       <input type = "submit" value = "UnAssign"/><br />
       </form>
       <%} %>
       <button><a href="viewSingleProject.jsp">Back</a></button>
        <button><a href="ProjectMenu.jsp">Project Menu</a></button>
</div>
</body>
</html>