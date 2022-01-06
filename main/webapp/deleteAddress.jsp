<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.ideas2it.employeemanagement.model.EmployeeVO,com.ideas2it.employeemanagement.model.AddressDTO,java.util.Set,java.util.HashSet"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="style.css">
<title>Delete Address</title>
</head>
<body><div><h2>Delete Address</h2>
<h3>Please enter the address Id to delete</h3>
    <%EmployeeVO employeeVO = (EmployeeVO)request.getAttribute("returnedEmployee");%>
    <%Set<AddressDTO> addresses = employeeVO.getaddressesDTO();
       //EmployeeVO employeeVO = (EmployeeVO)request.getAttribute("employeeVO");
       int addressId = 1;%>
       Addresses:
       <%
       if ((!addresses.isEmpty()) && (null !=addresses)){%>
    	   <table border="1">
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
       <%addressId++;
       } %>
       </table>    
       <%}%>
       </div>
</body>
</html>