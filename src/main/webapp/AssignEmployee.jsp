<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.List" import="java.util.ArrayList,com.ideas2it.employeemanagement.model.EmployeeVO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="resources/css/style.css">
<title>Assign Employee</title>
</head>
<style>
.button{
	width:250px;
}
.table-heading {
background-color:rgb(94,116,192);
}
.table-heading th{
color:white;
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
    <h2>Assign Employee</h2>
        <div style=" display:block; font-size:20px; color:black;">
    <%List<EmployeeVO> employees = (ArrayList<EmployeeVO>)request.getAttribute("employees");
    if((!employees.isEmpty()) && (null != employees)) {%>
    Employees:
    <form action = "assignEmployee" method = "get">
        <table border="1"  style="margin: 0px auto;background-color:white;">
            <tr class="table-heading">
            <th></th>
              <th>Employee Id</th>
              <th>Name</th>
              <th>DOB</th>
              <th>Email</th>
              <th>Mobile number</th>
              <th>Salary</th>
            </tr>
        <%for (EmployeeVO employeeVO:employees){ %>
            <tr>
              <td><input type="checkbox" value="<%=employeeVO.getEmployeeId()%>" name="employeeIds"></td>
              <td><%=employeeVO.getEmployeeId()%></td>
              <td><%=employeeVO.getName()%></td>
              <td><%=employeeVO.getDateOfBirth()%></td>
              <td><%=employeeVO.getEmail()%></td>
              <td><%=employeeVO.getMobileNumber()%></td>
              <td><%=employeeVO.getSalary()%></td>
            </tr>
        <%} %>
        </table>
        <input type = "hidden" name = "projectId" value="<%=request.getParameter("projectId")%>" pattern = "[1-9][0-9]{0,4}"/><br />
        <input class="button" type = "submit" value = "Assign"/><br /><br>
        <input class="button" type="submit" formaction="viewSingleProject" value="Back">
        
        </form>
        <a href="ProjectMenu.jsp"> <button class="button">Project menu</button></a> 
     <%} else {%>
          <%="No Employees to Assign!!" %>
     <form action = "viewSingleProject" method="get">
     <input type = "hidden" name = "projectId" value=<%=request.getParameter("projectId")%> pattern = "[1-9][0-9]{0,4}"/><br />
     <input class="button" type = "submit" value = "Back"/><br />
    </form>
     <%} %>
   </div>
</body>
</html>