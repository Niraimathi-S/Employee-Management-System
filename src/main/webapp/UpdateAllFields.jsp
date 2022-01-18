<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.ideas2it.employeemanagement.model.EmployeeVO,java.time.LocalDate"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update all fields</title>
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
<div>
 <form action = "EmployeeServlet" class = "form" method = "get">
         <input type="hidden" name="type" value="updateAllFields">
         <input type = "hidden" name = "employeeId" value=<%=request.getParameter("employeeId")%> pattern = "[1-9][0-9]{0,4}"/><br />
         Name: <input type = "text" name = "name" value=<%=request.getParameter("name")%> pattern = "([a-zA-Z]{3,}([\\s]{1}[a-zA-Z]+)*){0,100}" /><br />
         EmailId: <input type = "email" name = "email" value=<%=request.getParameter("email")%> pattern = "[a-zA-Z][\\w&&[^_]]{2,}([#$%&*!?\\.\\-_]{1}[\\w&&[^_]]+)*?@[a-zA-Z][a-zA-Z0-9]+ 
([\\.\\-]{1}[a-zA-Z0-9]+){0,4}.[\\w]{2,5}" /><br />
         Date of Birth: <input type = "date" value="2000-01-01" value=<%=request.getParameter("date_of_birth")%> name = "date_of_birth" /><br />
         Mobile Number: <input type = "tel" value=<%=request.getParameter("mobile_number")%> name = "mobile_number" pattern = "^[6-9][0-9]{9}" /> <br />
         Salary: <input type = "number" value=<%=request.getParameter("salary") %> name = "salary" pattern = "[0-9]+([\\.][0-9]{0,4})?" /> <br />
         <input type = "submit" value = "Submit" />
         <input type="reset" value="Reset">
         <input type ="button" value = "Back" onclick="history.back()"/>
         <button><a href="EmployeeMenu.jsp">Employee Main menu</a></button>       
 </form>
 </div>
</body>
</html>