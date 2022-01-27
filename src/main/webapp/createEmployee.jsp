<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.ideas2it.employeemanagement.model.EmployeeVO"
%>
<!DOCTYPE html>
<html>
    <head>
    <link rel="stylesheet" href="style.css">
    <meta charset="UTF-8">
    <title>Create Employee</title>
    </head>
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
    <h2>CREATE EMPLOYEE</h2>   
     <div style="display:block;margin-left:43%">
        <form action = "EmployeeServlet" class = "form" method = "post" name="form">
        <input type="hidden" name="type" value="create">
         Name         : <input type = "text" name = "name" pattern = "([a-zA-Z]{3,}([\\s]{1}[a-zA-Z]+)*){0,100}" /><br />
         EmailId      : <input type = "email" name = "email" pattern = "[a-zA-Z][\\w&&[^_]]{2,}([#$%&*!?\\.\\-_]{1}[\\w&&[^_]]+)*?@[a-zA-Z][a-zA-Z0-9]+ 
([\\.\\-]{1}[a-zA-Z0-9]+){0,4}.[\\w]{2,5}" /><br />
         Date of Birth: <input type = "date" name = "date_of_birth" /><br />
         Mobile Number: <input type = "tel" name = "mobile_number" pattern = "^[6-9][0-9]{9}" /> <br />
         Salary       : <input type = "number" name = "salary" pattern = "[0-9]+([\\.][0-9]{0,4})?" /> <br />
         Door Number  : <input type = "text" name = "door_number" pattern = "[\\w&&[^_]]+[/-]{0,1}[\\w&&[^_]]+" /> <br />
         Street       : <input type = "text" name = "street" pattern = "([\\w&&[^_]]+([\\s\\.,-]{1}[0-9a-zA-Z]+)*){1,100}" /> <br />
         City         : <input type = "text" name = "city" pattern = "([\\w&&[^_]]+([\\s\\.,-]{1}[0-9a-zA-Z]+)*){1,100}" /> <br />
         State        : <input type = "text" name = "state" pattern = "([\\w&&[^_]]+([\\s\\.,-]{1}[0-9a-zA-Z]+)*){1,100}" /> <br />
         Country      : <input type = "text" name = "country" pattern = "([\\w&&[^_]]+([\\s\\.,-]{1}[0-9a-zA-Z]+)*){1,100}" /> <br />
         Pin code     : <input type = "text" name = "pincode" pattern = "[1-9][0-9]{5}" > <br />
         <input type = "submit" value = "Submit" />
         <input type="reset" value="Reset">
         <button><a href = "EmployeeMenu.jsp">Back</a></button>
        </form>
        
        <div class="ack">
        <%EmployeeVO employeeVO = (EmployeeVO)request.getAttribute("returnedEmployee");
        if(null != employeeVO) {%>
        <%="Employee Created successfully. \nEmployee Id :"%>
        <%=employeeVO.getEmployeeId()%>
        <%}%>
        </div>
        </div>
    </body>
</html>