<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.ideas2it.employeemanagement.model.EmployeeVO"
%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
    <head>
    <link rel="stylesheet" href="resources/css/style.css">
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
     <div style="display:block;margin:0 auto;">
        <form:form action = "save" method = "POST" modelAttribute="employeeVO">
         <table style="margin:0 auto;text-align:left;">
         <tr><td>Name         : </td><td><form:input required = "required"  type = "text" path="name" pattern = "([a-zA-Z]{3,}([\\s]{1}[a-zA-Z]+)*){0,100}"/></td></tr>
         <tr><td>EmailId      : </td><td><form:input required = "required"  type = "email" path = "email" pattern = "[a-zA-Z][\\w&&[^_]]{2,}([#$%&*!?\\.\\-_]{1}[\\w&&[^_]]+)*?@[a-zA-Z][a-zA-Z0-9]+ 
([\\.\\-]{1}[a-zA-Z0-9]+){0,4}.[\\w]{2,5}" /></td></tr>
        <tr><td> </td><td>
        <div style="color:red">
        <%Boolean isDublicateEmail = (Boolean)request.getAttribute("isDublicateEmail");
        if(isDublicateEmail) {%>
        <%="This Email id already exists!!" %>  
        <%}%>
        </div></td></tr>
         <tr><td>Date of Birth:</td><td> <form:input required = "required" type = "date" path = "dateOfBirth" />
         <tr><td>Mobile Number:</td><td> <form:input required = "required" type = "tel" path = "mobileNumber" pattern = "^[6-9][0-9]{9}" /> </td></tr>
        <tr><td> </td><td><div style="color:red">
        <%Boolean isDublicateMobileNumber = (Boolean)request.getAttribute("isDublicateMobileNumber");
        if(isDublicateMobileNumber) {%>
        <%="This mobile number already exists!!" %>  
        <%}%>
        </div></td></tr>
         <tr><td>Salary       :</td><td> <form:input required = "required" type = "number" path = "salary" pattern = "[0-9]+([\\.][0-9]{0,4})?" /> </td></tr>
         <tr><td>Door Number  :</td><td> <form:input required = "required" path = "addressDTO.doorNumber" pattern = "[\\w&&[^_]]+[/-]{0,1}[\\w&&[^_]]+" /></td></tr>
         <tr><td>Street       :</td><td> <form:input required = "required" path = "addressDTO.street" pattern = "([\\w&&[^_]]+([\\s\\.,-]{1}[0-9a-zA-Z]+)*){1,100}" /></td></tr> 
         <tr><td>City         :</td><td> <form:input required = "required" path = "addressDTO.city" pattern = "([\\w&&[^_]]+([\\s\\.,-]{1}[0-9a-zA-Z]+)*){1,100}" /></td></tr> 
         <tr><td>State        :</td><td> <form:input required = "required" path = "addressDTO.state" pattern = "([\\w&&[^_]]+([\\s\\.,-]{1}[0-9a-zA-Z]+)*){1,100}" /></td></tr> 
         <tr><td>Country      :</td><td> <form:input required = "required" path = "addressDTO.country" pattern = "([\\w&&[^_]]+([\\s\\.,-]{1}[0-9a-zA-Z]+)*){1,100}" /> </td></tr>
         <tr><td>Pin code     :</td><td> <form:input required = "required" path = "addressDTO.pinCode" pattern = "[1-9][0-9]{5}"/> </td></tr>
         </table>
         <input type = "submit" value = "Submit" />
         <button><a href = "EmployeeMenu.jsp">Back</a></button>
                 <div style="color:black;margin-left:43%;">
        <%EmployeeVO employeeVO = (EmployeeVO)request.getAttribute("employeeVO");
        EmployeeVO returnedEmployee = (EmployeeVO)request.getAttribute("returnedEmployee");
        if((null != employeeVO)&&(null != returnedEmployee)) {%>
        <%="Employee Created successfully. <br>Employee Id :" %>  
        <%=returnedEmployee.getEmployeeId()%>
        <%}%>
        </div>
        </form:form>

        </div>
    </body>
</html>