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
<div>
    <%ProjectDTO projectDTO = (ProjectDTO)request.getAttribute("returnedProjectDTO");%>
        <%if (null == projectDTO) {%>
        sorry..No such project ID exists!!.<br>  
        <%} else {%>
        Project Details:<br>
        Project ID     :<%=projectDTO.getProjectId()%><br>
        Project Name   :<%=projectDTO.getName()%><br>
        Start Date     :<%=projectDTO.getStartDate()%><br>
        Project Manager:<%=projectDTO.getManager()%><br>
        Domain         :<%=projectDTO.getDomainName()%><br>
        <%if (!projectDTO.getEmployeesVO().isEmpty()) {%>
        
        Employees in the project:
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
              <td><%=employeeVO.getEmployeeId()%>></td>
              <td><%=employeeVO.getName()%></td>
              <td><%=employeeVO.getDateOfBirth()%></td>
              <td><%=employeeVO.getEmail()%></td>
              <td><%=employeeVO.getMobileNumber()%></td>
              <td><%=employeeVO.getSalary()%></td>
            </tr>
        <%} %>
        </table>
        <%} %>
        
       <form action = "ProjectServlet" method = "post">
       <input type="hidden" name="type" value="update">
       <input type = "hidden" name = "projectId" value=<%=projectDTO.getProjectId() %> pattern = "[1-9][0-9]{0,4}"/><br />
        <input type = "hidden" name = "name" value=<%=projectDTO.getName()%> pattern = "([a-zA-Z]{3,}([\\s]{1}[a-zA-Z]+)*){0,100}" /><br />
        <input type = "hidden" name = "domain" value=<%=projectDTO.getDomainName()%> pattern = "([a-zA-Z]{3,}([\\s]{1}[a-zA-Z]+)*){0,100}" /><br />
        <input type = "hidden" value=<%=projectDTO.getStartDate()%> value="2000-01-01"  name = "start_date" /><br />
        <input type = "hidden" name = "manager" value=<%=projectDTO.getManager()%> pattern = "([a-zA-Z]{3,}([\\s]{1}[a-zA-Z]+)*){0,100}" /> <br />
       <input type = "submit" value = "Update"/><br/>
       </form>
       <form action = "ProjectServlet" method = "post">
       <input type="hidden" name="type" value="delete">
       <input type = "hidden" name = "projectId" value=<%=projectDTO.getProjectId() %> pattern = "[1-9][0-9]{0,4}"/><br />
       <input type = "submit" value = "Delete"/><br />
       </form>
       <form action = "ProjectServlet" method = "get">
       <input type="hidden" name="type" value="assign&unassign">
       <input type = "hidden" name = "projectId" value="<%=projectDTO.getProjectId() %>" pattern = "[1-9][0-9]{0,4}"/><br />
       <input type="hidden" name="action" value="assign">
       <input type = "submit" value = "Assign"/><br />
       </form>
       <form action = "ProjectServlet" method = "get">
       <input type="hidden" name="type" value="assign&unassign">
       <input type = "hidden" name = "projectId" value="<%=projectDTO.getProjectId() %>" pattern = "[1-9][0-9]{0,4}"/><br />
       <input type="hidden" name="action" value="unassign">
       <input type = "submit" value = "UnAssign"/><br />
       </form>
       <button><a href="ViewSingleEmployee.jsp">Back</a></button>
       <%} %>
</div>
</body>
</html>