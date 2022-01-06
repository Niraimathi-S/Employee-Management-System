<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.ideas2it.employeemanagement.model.EmployeeVO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="style.css">
<title>Insert title here</title>
</head>
<body>
    <%EmployeeVO employeeVO = (EmployeeVO)request.getAttribute("returnedEmployee");%>
    <%Boolean idToDelete = (Boolean)request.getAttribute("isRecordDeleted");%>
        <%if ((null != employeeVO) && (idToDelete == true)) {%>
        Employee Deleted successfully!!.<br>  
        <%} else if ((null != employeeVO) &&(idToDelete != true)){%>
        Employee not deleted!
        <%} else if (null == employeeVO){%>
        No such Employee exist!!..Please give a valid employee ID.
        <%} else {%>
        Please give an Employee Id to delete
        <%} %>
        <button><a href="DeleteEmployee.jsp">Back</a></button>
        <button><a href="EmployeeMenu.jsp">Employee Main menu</a></button>
</body>
</html>