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
    <h1>CREATE EMPLOYEE</h1>
        <form action = "EmployeeServlet" method = "post" name="form">
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
         <input type = "submit" value = "Submit" onclick="calculateAge()" />
         <input type="reset" value="Reset">
         <input type="button" value="Back" onclick="EmployeeMenu.jsp">
        </form>
        <%EmployeeVO employeeVO = (EmployeeVO)request.getAttribute("returnedEmployee");
        if(null != employeeVO) {%>
        Employee Created successfully. <br>Employee Id :  
        <%=employeeVO.getEmployeeId()%>
        <%}%>
        <script>
        function calculateAge() 
{
  const now = new Date();
  const date= new Date(document.getElementsByName("date_of_birth"));
  const diff = Math.abs(now - date );
  const age = Math.floor(diff / (1000 * 60 * 60 * 24 * 365)); 
  if (( 18 > age ) || ( 60 < age )) {
	  windows.alert("Invalid Age!! Age should be in between 18 and 60");
	  document.form.date_of_birth.focus();
  }
} </script>
    </body>
</html>