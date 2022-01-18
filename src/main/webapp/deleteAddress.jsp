<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.ideas2it.employeemanagement.model.EmployeeVO,com.ideas2it.employeemanagement.model.AddressDTO,java.util.Set,java.util.HashSet"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="style.css">
<title>Delete Address</title>
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
    <div style=" display:block; font-size:30px">
    <h2>Delete Address</h2>
    <%EmployeeVO employeeVO = (EmployeeVO)request.getAttribute("returnedEmployee");%>
    <%Set<AddressDTO> addresses = employeeVO.getaddressesDTO();%>
       <h3>Addresses:</h3>
       <%
       if ((!addresses.isEmpty()) && (null !=addresses)){%>
    	   <table border="1"  style="margin: 0px auto;">
           <tr>
             <th>Door Number</th>
             <th>Street</th>
             <th>City</th>
             <th>State</th>
             <th>Country</th>
             <th>Pin code</th>
           </tr>
       <%for (AddressDTO address:employeeVO.getaddressesDTO()) {%>
           <tr>
             <td><%=address.getDoorNumber() %></td>
             <td><%=address.getStreet() %></td>
             <td><%=address.getCity() %></td>
             <td><%=address.getState() %></td>
             <td><%=address.getCountry() %></td>
             <td><%=address.getPinCode() %></td>
             <td> <form action = "EmployeeServlet" method = "get">
       <input type="hidden" name="type" value="DeleteAddress">
       <input type = "hidden" name = "employeeId" value=<%=request.getParameter("employeeId")%> pattern = "[1-9][0-9]{0,4}"/><br />
       <input type = "hidden" name = "addressId" value = "<%=address.getAddressId()%>" pattern = "[1-9][0-9]{0,4}"/><br />
       <input type = "submit" value = "Delete"/><br />
       </form>
       </td>
           </tr>
       <%} %>
       </table>   
       <%} else {%>
       <%="No Address to Delete" %>
       <%} %>
       <input type ="button" value = "Back" onclick="history.back()"/>
       </div>
</body>
</html>