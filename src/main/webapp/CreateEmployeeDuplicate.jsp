<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.ideas2it.employeemanagement.model.AddressDTO,com.ideas2it.employeemanagement.model.EmployeeVO"
%>
<!DOCTYPE html>
<html>
    <head>
    <link rel="stylesheet" href="resources/css/style.css">
    <meta charset="UTF-8">
    <title>Create Employee</title>
    </head>
    <body>
            <%EmployeeVO employeeVO = (EmployeeVO)request.getAttribute("employeeVO");
            AddressDTO addressDTO = (AddressDTO)request.getAttribute("addressDTO");
            boolean isMobileNumberDublicate = (Boolean)request.getAttribute("isMobileNumberDublicate");
            boolean isEmailDublicate = (Boolean)request.getAttribute("isEmailDublicate");%>

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
    <div style="display:block;margin-left:43%">
        <form action = "EmployeeServlet" class = "form" method = "post" name="form">
        <input type="hidden" name="type" value="create">
         Name         : <input type = "text" name = "name" value = "<%=employeeVO.getName()%>" pattern = "([a-zA-Z]{3,}([\\s]{1}[a-zA-Z]+)*){0,100}" /><br />
         EmailId      : <input type = "email" name = "email" value = "<%=employeeVO.getEmail() %>" pattern = "[a-zA-Z][\\w&&[^_]]{2,}([#$%&*!?\\.\\-_]{1}[\\w&&[^_]]+)*?@[a-zA-Z][a-zA-Z0-9]+ 
([\\.\\-]{1}[a-zA-Z0-9]+){0,4}.[\\w]{2,5}" /><br />
         <%if (isEmailDublicate) {%>
         <%="Email Already Exist" %><br>
         <%} %>
         Date of Birth: <input type = "date" name = "date_of_birth" value = "<%=employeeVO.getDateOfBirth() %>"/><br />
         Mobile Number: <input type = "tel" name = "mobile_number" value = "<%=employeeVO.getMobileNumber() %>" pattern = "^[6-9][0-9]{9}" /> <br />
         <%if (isMobileNumberDublicate) {%>
         <%="Mobile Number Already Exist" %><br>
         <%} %>
         Salary       : <input type = "number" name = "salary" value = "<%=employeeVO.getSalary() %>" pattern = "[0-9]+([\\.][0-9]{0,4})?" /> <br />
         Door Number  : <input type = "text" name = "door_number" value = "<%=addressDTO.getDoorNumber()%>" pattern = "[\\w&&[^_]]+[/-]{0,1}[\\w&&[^_]]+" /> <br />
         Street       : <input type = "text" name = "street" value = "<%=addressDTO.getStreet()%>" pattern = "([\\w&&[^_]]+([\\s\\.,-]{1}[0-9a-zA-Z]+)*){1,100}" /> <br />
         City         : <input type = "text" name = "city" value = "<%=addressDTO.getCity()%>" pattern = "([\\w&&[^_]]+([\\s\\.,-]{1}[0-9a-zA-Z]+)*){1,100}" /> <br />
         State        : <input type = "text" name = "state" value = "<%=addressDTO.getState()%>" pattern = "([\\w&&[^_]]+([\\s\\.,-]{1}[0-9a-zA-Z]+)*){1,100}" /> <br />
         Country      : <input type = "text" name = "country" value = "<%=addressDTO.getCountry()%>" pattern = "([\\w&&[^_]]+([\\s\\.,-]{1}[0-9a-zA-Z]+)*){1,100}" /> <br />
         Pin code     : <input type = "text" name = "pincode" value = "<%=addressDTO.getPinCode()%>" pattern = "[1-9][0-9]{5}" > <br />
         <input type = "submit" value = "Submit" />
         <input type="reset" value="Reset">
         <button><a class="button" href = "EmployeeMenu.jsp">Back</a></button>
        </form>
        <div class="ack">
        <%EmployeeVO employeeVO1 = (EmployeeVO)request.getAttribute("returnedEmployee");
        if(null != employeeVO1) {%>
        <%="Employee Created successfully. \nEmployee Id :"%>
        <%=employeeVO.getEmployeeId()%>
        <%}%>
        </div>
        </div>
    </body>
</html>