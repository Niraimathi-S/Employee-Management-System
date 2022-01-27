<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.ideas2it.employeemanagement.model.EmployeeVO"
    import="com.ideas2it.employeemanagement.model.AddressDTO,com.ideas2it.employeemanagement.model.ProjectDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="resources/css/style.css">
<title>Single Employee Display</title>
</head>
<style>
.table-heading {
background-color:rgb(94,116,192);
}
.table-heading th{
color:white;
}
.button{
width:200px;
}
.view-single {
	border-color:azure;
	background-color:white;
	border-style: none;
	color:red;
	text-align:center;
}

.view-single:hover{
	text-decoration:underline;
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
<h2>Single Employee View</h2><div class="display-single-div">

    <%EmployeeVO employeeVO = (EmployeeVO)request.getAttribute("returnedEmployee");%>
        <%if (null == employeeVO) {%>
        <%="sorry..No such Employee ID exists!!."%><br>  
        <%} else {%>
        <%="Employee Details:" %><br>
        <br><table cellPadding="5" style="margin: 0px auto;text-align:left;">
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
</table>
       <form action = "updateEmployee" method = "get">
       <input type = "hidden" name = "employeeId" value="<%=employeeVO.getEmployeeId() %>"/> 
       <input class="button" type = "submit" value = "Update"/>
       </form>
       <form action = "delete" method = "post">
       <input type = "hidden" name = "employeeId" value="<%=employeeVO.getEmployeeId() %>"/> 
       <input class="button" type = "submit" value = "Delete"/> 
       </form> 
       <br>
        <%if ((null != employeeVO.getaddressesDTO()) && (!employeeVO.getaddressesDTO().isEmpty())) {%>
       <br> <%="Address:" %>
        <table border="1" style="margin: 0px auto;background-color:white;">
            <tr class="table-heading">
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
              <td>       
              <form action = "deleteAddress" method = "get">
                  <input type = "hidden" name = "employeeId" value=<%=employeeVO.getEmployeeId() %> pattern = "[1-9][0-9]{0,4}"/>
                  <input type = "hidden" name = "addressId" value=<%=address.getAddressId() %> pattern = "[1-9][0-9]{0,4}"/> 
                  <input class="view-single" type = "submit" value = "Delete"/> 
              </form>
              </td>
            </tr>
        <%} %>
        <%} %>
        </table> 
       <br><form action = "updateAddress" method = "get">
       <input type="hidden" name="type" value="AddAddress">
       <input type = "hidden" name = "employeeId" value=<%=employeeVO.getEmployeeId() %> pattern = "[1-9][0-9]{0,4}"/> 
       <input class="button" type = "submit" value = "Add Address"/> 
       </form>

        <%if ((null != employeeVO.getProjectsDTO()) && (!employeeVO.getProjectsDTO().isEmpty())) {%>
        <br><%="Projects:" %>
        <table border="1" style="margin: 0px auto;background-color:white;">
            <tr class="table-heading">
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
        <%} %>
        </table><br>
       <form action = "assignProject" method = "get">
       <input type = "hidden" name = "employeeId" value="<%=employeeVO.getEmployeeId() %>" pattern = "[1-9][0-9]{0,4}"/> 
       <input class="button" type = "submit" value = "Assign"/> 
       </form>
       <form action = "unassignProject" method = "get">
       <input type = "hidden" name = "employeeId" value="<%=employeeVO.getEmployeeId() %>" pattern = "[1-9][0-9]{0,4}"/> 
       <input class="button" type = "submit" value = "UnAssign"/> 
       </form>
       <%} %>
        <br><button class="button"><a href="viewAll">Back</a></button>
        </div>
</body>
</html>