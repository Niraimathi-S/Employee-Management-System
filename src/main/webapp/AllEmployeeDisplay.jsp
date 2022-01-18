<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.ideas2it.employeemanagement.model.EmployeeVO"
    import="com.ideas2it.employeemanagement.model.AddressDTO,com.ideas2it.employeemanagement.model.ProjectDTO"
    import="java.util.List" import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>All Employee display</title>
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
    <h2>Employee list</h2>
<div style=" display:block; font-size:20px;text-align:left;">
    <%List<EmployeeVO> employees = (ArrayList<EmployeeVO>)request.getAttribute("Employees");%>
        <%if ((null == employees) || (employees.isEmpty())) {%>
        Sorry..Nothing to show!<br>  
        <%} else {%>
        <%for (EmployeeVO employeeVO:employees){ %>
       <table cellPadding="5" style="margin: 0 auto;text-align:left;">
        <tr>
          <td>Employee ID:<%=employeeVO.getEmployeeId()%></td>
          <td>Email:<%=employeeVO.getEmail()%></td>
        </tr>
        <tr>
          <td>Name:<%=employeeVO.getName()%></td>
          <td>Date Of Birth :<%=employeeVO.getDateOfBirth()%></td>
        </tr>
        <tr>
          <td>Mobile number:<%=employeeVO.getMobileNumber()%></td>
          <td>Salary:<%=employeeVO.getSalary()%></td>
        </tr>
        <tr>
        <%if ((null != employeeVO.getaddressesDTO()) && (!employeeVO.getaddressesDTO().isEmpty())) {%>
        <td>Address:</td>
        <td> <table border="1" style="margin: 0px auto;">
            <tr>
              <th>Door Number</th>
              <th>Street</th>
              <th>City</th>
              <th>State</th>
              <th>Country</th>
              <th>Pin code</th>
            </tr>
        <%for (AddressDTO address:employeeVO.getaddressesDTO()){ %>
            <tr>
              <td><%=address.getDoorNumber() %></td>
              <td><%=address.getStreet() %></td>
              <td><%=address.getCity() %></td>
              <td><%=address.getState() %></td>
              <td><%=address.getCountry() %></td>
              <td><%=address.getPinCode() %></td>
            </tr>
        <%} %>
        </table></td>
        <%} %>
        </tr>
        <%if ((null != employeeVO.getProjectsDTO()) && (!employeeVO.getProjectsDTO().isEmpty())) {%>
        <tr>
        <td>Projects:
        </td>
        <td><table border="1" style="margin: 0px auto;">
            <tr>
              <th>Project Id</th>
              <th>Project Name</th>
              <th>Manager</th>
              <th>Start Date</th>
              <th>Domain</th>
            </tr>
        <%
        for(ProjectDTO project:employeeVO.getProjectsDTO()){%>
            <tr>
              <td><%=project.getProjectId() %></td>
              <td><%=project.getName() %></td>
              <td><%=project.getManager() %></td>
              <td><%=project.getStartDate() %></td>
              <td><%=project.getDomainName() %></td>
            </tr>
        <%} %>
        </table>
        </td>
        </tr>
        <%} %>
        <tr><td></td></tr>
        <tr><td></td></tr>
        </table>
        <%} %>   
        <%} %>
     <button style="margin:auto 50%;"><a href="ViewEmployee.jsp">Back</a></button>
     </div>
</body>
</html>