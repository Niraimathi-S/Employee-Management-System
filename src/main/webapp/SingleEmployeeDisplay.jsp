<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.ideas2it.employeemanagement.model.EmployeeVO"
    import="com.ideas2it.employeemanagement.model.AddressDTO,com.ideas2it.employeemanagement.model.ProjectDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="style.css">
<title>Single Employee Display</title>
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
       <form action = "UpdateAllFields.jsp" method = "get">
       <input type="hidden" name="type" value="updateAllFields">
       <input type = "hidden" name = "employeeId" value=<%=employeeVO.getEmployeeId() %> pattern = "[1-9][0-9]{0,4}"/>
       <input type = "hidden" name = "name" value=<%=employeeVO.getName() %> pattern = "([a-zA-Z]{3,}([\\s]{1}[a-zA-Z]+)*){0,100}" />
       <input type = "hidden" name = "email" value=<%=employeeVO.getEmail() %> pattern = "[a-zA-Z][\\w&&[^_]]{2,}([#$%&*!?\\.\\-_]{1}[\\w&&[^_]]+)*?@[a-zA-Z][a-zA-Z0-9]+ 
([\\.\\-]{1}[a-zA-Z0-9]+){0,4}.[\\w]{2,5}" />
       <input type = "hidden" name = "date_of_birth" value=<%=employeeVO.getDateOfBirth() %>/>
       <input type = "hidden" name = "mobile_number" value=<%=employeeVO.getMobileNumber() %> pattern = "^[6-9][0-9]{9}" />  
       <input type = "hidden" name = "salary" value=<%=employeeVO.getSalary() %> pattern = "[0-9]+([\\.][0-9]{0,4})?" />  
       <input type = "submit" value = "Update"/>
       </form>
       <form action = "EmployeeServlet" method = "post">
       <input type="hidden" name="type" value="delete">
       <input type = "hidden" name = "employeeId" value="<%=employeeVO.getEmployeeId() %>" pattern = "[1-9][0-9]{0,4}"/> 
       <%request.setAttribute("employeeVO",employeeVO) ;%>
       <input type = "submit" value = "Delete"/> 
       </form> 
       <br>
        <%if ((null != employeeVO.getaddressesDTO()) && (!employeeVO.getaddressesDTO().isEmpty())) {%>
       <br> <%="Address:" %>
        <table border="1" style="margin: 0px auto;">
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
        <%} %>
        </table> 
       <br><form action = "AddAddress.jsp" method = "get">
       <input type="hidden" name="type" value="AddAddress">
       <input type = "hidden" name = "employeeId" value=<%=employeeVO.getEmployeeId() %> pattern = "[1-9][0-9]{0,4}"/> 
       <input type = "submit" value = "Add Address"/> 
       </form>
       <form action = "EmployeeServlet" method = "get">
       <input type="hidden" name="type" value="update">
       <input type = "hidden" name = "employeeId" value=<%=employeeVO.getEmployeeId() %> pattern = "[1-9][0-9]{0,4}"/> 
       <input type = "submit" value = "Delete Address"/> 
       </form>

        <%if ((null != employeeVO.getProjectsDTO()) && (!employeeVO.getProjectsDTO().isEmpty())) {%>
        <br><%="Projects:" %>
        <table border="1" style="margin: 0px auto;">
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
        <%} %>
        </table><br>
       <form action = "EmployeeServlet" method = "get">
       <input type="hidden" name="type" value="assign&unassign">
       <input type = "hidden" name = "employeeId" value="<%=employeeVO.getEmployeeId() %>" pattern = "[1-9][0-9]{0,4}"/> 
       <input type="hidden" name="action" value="assign">
       <input type = "submit" value = "Assign"/> 
       </form>
       <form action = "EmployeeServlet" method = "get">
       <input type="hidden" name="type" value="assign&unassign">
       <input type = "hidden" name = "employeeId" value="<%=employeeVO.getEmployeeId() %>" pattern = "[1-9][0-9]{0,4}"/> 
       <input type="hidden" name="action" value="unassign">
       <input type = "submit" value = "UnAssign"/> 
       </form>
       <%} %>
        <button><a href="ViewSingleEmployee.jsp">Back</a></button>
        </div>
</body>
</html>