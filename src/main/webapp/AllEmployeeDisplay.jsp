<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.ideas2it.employeemanagement.model.EmployeeVO"
    import="com.ideas2it.employeemanagement.model.AddressDTO,com.ideas2it.employeemanagement.model.ProjectDTO"
    import="java.util.List" import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>All Employee display</title>
<link rel="stylesheet" href="resources/css/style.css">

</head>
<style>
.view-single {
	border-color:azure;
	background-color:azure;
	border-style: none;
	color:blue;
	text-align:center;
}

.view-single:hover{
	text-decoration:underline;
	color:purple;
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
    <h2>Employee list</h2>
<div style=" display:block;margin: 0 auto; font-size:20px;text-align:left;">
    <%List<EmployeeVO> employees = (ArrayList<EmployeeVO>)request.getAttribute("Employees");%>
        <%if ((null == employees) || (employees.isEmpty())) {%>
        Sorry..Nothing to show!<br>  
        <%} else {%>
        <table border="1" style="margin: 0 auto;">
        <tr>
              <th>Employee Id</th>
              <th>Name</th>
              <th>DOB</th>
              <th>Email</th>
              <th>Mobile number</th>
              <th>Salary</th>
            </tr>
        <%for (EmployeeVO employeeVO:employees){ %>
       
            
          <tr>
              <td><%=employeeVO.getEmployeeId()%></td>
              <td><%=employeeVO.getName()%></td>
              <td><%=employeeVO.getDateOfBirth()%></td>
              <td><%=employeeVO.getEmail()%></td>
              <td><%=employeeVO.getMobileNumber()%></td>
              <td><%=employeeVO.getSalary()%></td>
        <td>
        <form action = "viewSingle" method = "get" class="form">
        <input type = "hidden" name = "employeeId" value="<%=employeeVO.getEmployeeId()%>" pattern = "[1-9][0-9]{0,4}"/><br>
        <input class="view-single" type = "submit" value ="view details"/><br><br>
        </form>
        </td>
        </tr>
        <%} %>   
        <%} %>
        </table>
        
     <button  class="button" style="margin:0 44%;width:200px;"><a href="EmployeeMenu.jsp">Back</a></button>
     </div>
</body>
</html>