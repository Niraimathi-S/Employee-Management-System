<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.ideas2it.employeemanagement.model.EmployeeVO,com.ideas2it.employeemanagement.model.ProjectDTO"
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Single project Display</title>
<link rel="stylesheet" href="resources/css/style.css">
</head>
<style>
.table-heading {
background-color:rgb(94,116,192);
}
.table-heading th{
color:white;
}
.button{
width:200px;}
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
    <h2>Single project view</h2>
<div class="display-single-div">
    <%ProjectDTO projectDTO = (ProjectDTO)request.getAttribute("projectDTO");%>
        <%if (null == projectDTO) {%>
        <%="sorry..No such project ID exists!!." %><br>  
        <%} else {%>
                <div style="color:black;margin-left:40%">

        <%boolean isUpdated = (Boolean)request.getAttribute("isUpdated");
        if(isUpdated) {%>
        <%="Project Updated successfully." %>  
        <%}%></div>
        <%="Project Details:" %><br>
        <table style="margin:0px auto;text-align:left;border-spacing: 10px;">

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
       <form action = "update" method = "get" style="margin:auto;">
       <input type = "hidden" name = "projectId" value=<%=projectDTO.getProjectId() %> pattern = "[1-9][0-9]{0,4}"/>
       <input class="button" type = "submit" value = "Update"/><br/>
       </form>
       <form action = "deleteProject" method = "get">
       <input type = "hidden" name = "projectId" value=<%=projectDTO.getProjectId() %> pattern = "[1-9][0-9]{0,4}"/> 
       <input class="button" type = "submit" value = "Delete"/><br />
       </form>
        <%if (!projectDTO.getEmployeesVO().isEmpty()) {%>
        <br>
            Employees:
            <table border="1" style="margin: 0px auto;">
            <tr class="table-heading">
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
        <br>
       <form action = "assign&unAssign" method = "get">
       <input type="hidden" name="type" value="assign">
       <input type = "hidden" name = "projectId" value="<%=projectDTO.getProjectId() %>" pattern = "[1-9][0-9]{0,4}"/> 
       <input class="button" type = "submit" value = "Assign"/>
       </form>
       <form action = "assign&unAssign" method = "get">
       <input type="hidden" name="type" value="unAssign">
       <input type = "hidden" name = "projectId" value="<%=projectDTO.getProjectId() %>" pattern = "[1-9][0-9]{0,4}"/> 
       <input class="button" type = "submit" value = "UnAssign"/><br />
       </form><br>
       <%} %>
       <a href="viewAllProject"><button class="button">Back</button></a>
       <a href="ProjectMenu.jsp"><button class="button">Project Menu</button></a>
</div>
</body>
</html>