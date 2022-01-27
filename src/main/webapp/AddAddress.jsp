<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.ideas2it.employeemanagement.model.EmployeeVO"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Address</title>
<link rel="stylesheet" href="resources/css/style.css">

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
    <h2>Add Address</h2>
    <div style="margin:0 auto; display:block; font-size:30px">
         <%Integer employeeId = (Integer)request.getAttribute("employeeId");%>
        <form:form action = "addAddress"  method = "get" modelAttribute="addressDTO">
         <form:hidden path = "addressId" pattern = "[1-9][0-9]{0,4}" value="<%=employeeId%>"/>
         <table style="margin:0 auto;text-align:left;">
         <tr><td>Door Number  :</td><td> <form:input path = "doorNumber" pattern = "[\\w&&[^_]]+[/-]{0,1}[\\w&&[^_]]+" /></td></tr>
         <tr><td>Street       :</td><td> <form:input path = "street" pattern = "([\\w&&[^_]]+([\\s\\.,-]{1}[0-9a-zA-Z]+)*){1,100}" /></td></tr>
         <tr><td>City         :</td><td> <form:input path = "city" pattern = "([\\w&&[^_]]+([\\s\\.,-]{1}[0-9a-zA-Z]+)*){1,100}" /></td></tr>
         <tr><td>State        :</td><td> <form:input path = "state" pattern = "([\\w&&[^_]]+([\\s\\.,-]{1}[0-9a-zA-Z]+)*){1,100}" /> </td></tr>
         <tr><td>Country      :</td><td> <form:input path = "country" pattern = "([\\w&&[^_]]+([\\s\\.,-]{1}[0-9a-zA-Z]+)*){1,100}" /> </td></tr>
         <tr><td>Pin code     :</td><td> <form:input path = "pinCode" pattern = "[1-9][0-9]{5}"/> </td></tr>
         </table><br>
         <input type = "submit" value = "Submit"/>
         <input type ="button" value = "Back" onclick="history.back()"/>
         <button><a href="EmployeeMenu.jsp">Employee Main menu</a></button>
         </form:form>
    </div>
</body>
</html>