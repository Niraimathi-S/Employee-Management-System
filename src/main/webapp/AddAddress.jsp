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
    <div style="margin:10% 40%; display:block; font-size:30px">
        <form action = "EmployeeServlet" class = "form" method = "get">
        <input type="hidden" name="type" value="AddAddress">
        <%EmployeeVO employeeVO = (EmployeeVO)request.getAttribute("returnedEmployee");%>
         <input type = "hidden" name = "employeeId" value=<%=request.getParameter("employeeId")%> pattern = "[1-9][0-9]{0,4}"/><br />
         Door Number: <input type = "text" name = "door_number" pattern = "[\\w&&[^_]]+[/-]{0,1}[\\w&&[^_]]+" /> <br />
         Street: <input type = "text" name = "street" pattern = "([\\w&&[^_]]+([\\s\\.,-]{1}[0-9a-zA-Z]+)*){1,100}" /> <br />
         City: <input type = "text" name = "city" pattern = "([\\w&&[^_]]+([\\s\\.,-]{1}[0-9a-zA-Z]+)*){1,100}" /> <br />
         State: <input type = "text" name = "state" pattern = "([\\w&&[^_]]+([\\s\\.,-]{1}[0-9a-zA-Z]+)*){1,100}" /> <br />
         Country: <input type = "text" name = "country" pattern = "([\\w&&[^_]]+([\\s\\.,-]{1}[0-9a-zA-Z]+)*){1,100}" /> <br />
         Pin code: <input type = "text" name = "pincode" pattern = "[1-9][0-9]{5}" > <br />
         <input type = "submit" value = "Submit"/>
          <input type ="button" value = "Back" onclick="history.back()"/>
         <button><a href="EmployeeMenu.jsp">Employee Main menu</a></button>
         </form>

    </div>
</body>
</html>