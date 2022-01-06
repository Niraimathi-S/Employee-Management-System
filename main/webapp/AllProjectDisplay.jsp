<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.ideas2it.employeemanagement.model.EmployeeVO,com.ideas2it.employeemanagement.model.ProjectDTO,java.util.List,java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="style.css">
<title>All Project Display</title>
</head>
<body>
    <%List<ProjectDTO> projects = (ArrayList<ProjectDTO>)request.getAttribute("projects");%>
        <%if ((null == projects) || (projects.isEmpty())) {%>
        sorry..Nothing to display!!.<br>  
        <%} else {
            for(ProjectDTO projectDTO:projects) {%>
        
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
        <%} %>
        <%} %>
        <a href = "ProjectMenu.jsp">Main menu </a><br>
</body>
</html>