<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.ideas2it.employeemanagement.model.EmployeeVO"
    import="com.ideas2it.employeemanagement.model.AddressDTO"
    import="java.util.List" import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>All Employee display</title>
<link rel="stylesheet" href="style.css">

</head>
<body><div>
    <form action = "ViewAllEmployeeServlet" method = "post">
    <%List<EmployeeVO> employees = (ArrayList<EmployeeVO>)request.getAttribute("Employees");%>
        <%if ((null == employees) || (employees.isEmpty())) {%>
        sorry..Nothing to show!<br>  
        <%} else {%>
        <%for (EmployeeVO employeeVO:employees){ %>
        Employee Details:<br>
        Employee ID   :<%=employeeVO.getEmployeeId()%><br>
        Name          :<%=employeeVO.getName()%><br>
        Date Of Birth :<%=employeeVO.getDateOfBirth()%><br>
        Email         :<%=employeeVO.getEmail()%><br>
        Mobile number :<%=employeeVO.getMobileNumber()%><br>
        Salary        :<%=employeeVO.getSalary()%><br>
        Address:
        <table style="border:10px;border-color:black">
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
        </table>
        <%} %>
        <%} %>
     </form>
     <button><a href="ViewEmployee.jsp">Back</a></button>
     </div>
</body>
</html>