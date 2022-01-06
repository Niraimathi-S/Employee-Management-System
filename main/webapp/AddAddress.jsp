<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.ideas2it.employeemanagement.model.EmployeeVO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Address</title>
<link rel="stylesheet" href="style.css">

</head>
<body>
        <form action = "EmployeeServlet" method = "get">
        <input type="hidden" name="type" value="AddAddress">
        <%EmployeeVO employeeVO = (EmployeeVO)request.getAttribute("returnedEmployee");%>
        <%//if (null == employeeVO) {
                System.out.println("employeeVO in add address all jsp"+employeeVO);%>
        %>
        No such Employee exist!!..Please give a valid employee ID.<br>  
        <%//} else {%>
         <input type = "hidden" name = "employeeId" value=<%=request.getParameter("employeeId")%> pattern = "[1-9][0-9]{0,4}"/><br />
         Door Number: <input type = "text" name = "door_number" pattern = "[\\w&&[^_]]+[/-]{0,1}[\\w&&[^_]]+" /> <br />
         Street: <input type = "text" name = "street" pattern = "([\\w&&[^_]]+([\\s\\.,-]{1}[0-9a-zA-Z]+)*){1,100}" /> <br />
         City: <input type = "text" name = "city" pattern = "([\\w&&[^_]]+([\\s\\.,-]{1}[0-9a-zA-Z]+)*){1,100}" /> <br />
         State: <input type = "text" name = "state" pattern = "([\\w&&[^_]]+([\\s\\.,-]{1}[0-9a-zA-Z]+)*){1,100}" /> <br />
         Country: <input type = "text" name = "country" pattern = "([\\w&&[^_]]+([\\s\\.,-]{1}[0-9a-zA-Z]+)*){1,100}" /> <br />
         Pin code: <input type = "text" name = "pincode" pattern = "[1-9][0-9]{5}" > <br />
         <input type = "submit" value = "Submit"/>
         <input type="reset" value="Reset">
         </form>
         <%//} %>
         <button><a href="UpdateMenu.jsp">Back</a></button>
         <button><a href="EmployeeMenu.jsp">Employee Main menu</a></button>
</body>
</html>