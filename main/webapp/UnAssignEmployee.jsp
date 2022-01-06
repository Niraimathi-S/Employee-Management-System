<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.Set" import="java.util.HashSet" import = "com.ideas2it.employeemanagement.model.EmployeeVO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>UnAssign Employee</title>
<link rel="stylesheet" href="style.css">
</head>
<body>
    <%Set<EmployeeVO> employees = (HashSet<EmployeeVO>)request.getAttribute("employees");
    if((!employees.isEmpty()) && (null != employees)) {%>
    Employees:
            <table border="1">
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
              <td><%=employeeVO.getEmployeeId()%>></td>
              <td><%=employeeVO.getName()%></td>
              <td><%=employeeVO.getDateOfBirth()%></td>
              <td><%=employeeVO.getEmail()%></td>
              <td><%=employeeVO.getMobileNumber()%></td>
              <td><%=employeeVO.getSalary()%></td>
              <td>
                <form action = "ProjectServlet" method = "get">
                <input type="hidden" name="type" value="unassign">
                <input type = "hidden" name = "projectId" value=<%=request.getParameter("projectId")%> pattern = "[1-9][0-9]{0,4}"/><br />
                <input type = "hidden" name = "employeeId" value="<%=employeeVO.getEmployeeId()%>" pattern = "[1-9][0-9]{0,4}"/><br />
                <input type = "submit" value = "UnAssign"/><br />
                </form>
              </td>
            </tr>
        <%} %>
        </table>
     <%} %>
</body>
</html>