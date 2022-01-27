<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.ideas2it.employeemanagement.model.EmployeeVO,java.time.LocalDate"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update all fields</title>
<link rel="stylesheet" href="resources/css/style.css">
</head>
<style>
.button{
	width:200px;
	margin:0 auto;
}

.display-single-div{
margin:0 36%;
}
</style>
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
<h2>Update Employee</h2>
<div class="display-single-div">

 <form:form action = "updateAllFields" class = "form" method = "get" modelAttribute = "employeeVO">
       <form:input type = "hidden" path = "employeeId" pattern = "[1-9][0-9]{0,4}"/> 
         <table>
         
         <tr><td>Name         :</td><td> <form:input type = "text" path="name" required = "required" pattern = "([a-zA-Z]{3,}([\\s]{1}[a-zA-Z]+)*){0,100}" /></td></tr>
         <tr><td>EmailId      :</td><td>  <form:input type = "email" path = "email" required = "required" pattern = "[a-zA-Z][\\w&&[^_]]{2,}([#$%&*!?\\.\\-_]{1}[\\w&&[^_]]+)*?@[a-zA-Z][a-zA-Z0-9]+ 
([\\.\\-]{1}[a-zA-Z0-9]+){0,4}.[\\w]{2,5}" /></td></tr>
        
        <tr><td> </td><td><div style="color:red">
        <%Boolean isDublicateEmail = (Boolean)request.getAttribute("isDublicateEmail");
        if(isDublicateEmail) {%>
        <%="This Email id already exists!!" %>  
        <%}%>
        </div></td></tr>
        <tr><td> Date of Birth:</td><td>  <form:input type = "date" path = "dateOfBirth" required = "required" /></td></tr>
         <tr><td>Mobile Number:</td><td>  <form:input type = "tel" path = "mobileNumber" required = "required" pattern = "^[6-9][0-9]{9}" /> </td></tr>
        <tr><td> </td><td><div style="color:red">
        <%Boolean isDublicateMobileNumber = (Boolean)request.getAttribute("isDublicateMobileNumber");
        if(isDublicateMobileNumber) {%>
        <%="This mobile number already exists!!" %>  
        <%}%>
        </div></td></tr>
         <tr><td>Salary       :</td><td>  <form:input type = "number" path = "salary" required = "required"  pattern = "[0-9]+([\\.][0-9]{0,4})?" /></td></tr>
         </table>
           <br><input class="button" type = "submit" value = "Submit"/>
         <input class="button" style="margin-left:70px;" type="submit" formaction="viewSingle" value="Back"><br>      
 </form:form>

        <br> <button class="button" style="width:300px;margin-left:200px:"><a href="EmployeeMenu.jsp">Employee menu</a></button>
 </div>
</body>
</html>