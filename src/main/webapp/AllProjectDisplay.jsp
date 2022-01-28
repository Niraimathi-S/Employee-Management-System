<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.ideas2it.employeemanagement.model.EmployeeVO,com.ideas2it.employeemanagement.model.ProjectDTO,java.util.List,java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="resources/css/style.css">
<title>All Project Display</title>
</head>
<style>
.view-single {
	border-color:azure;
	background-color:azure;
	border-style: none;
	color:blue;
	text-align:center;
}

.view-single:hover{
	text-decoration:underline;
	color:purple;
}
.button{
width:260px;
}
.button:hover{
background-color:#4dc6f5;
}
.table-heading {
background-color:rgb(94,116,192);
}
.table-heading th{
color:white;
}
table{
background-color:white;
}
</style>
<body class="body">
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
    <h2>Project list</h2>
<div style=" display:block; font-size:20px; align-items:left;">
    <%List<ProjectDTO> projects = (ArrayList<ProjectDTO>)request.getAttribute("projects");
      int i=1;%>
        <%if ((null == projects) || (projects.isEmpty())) {%>
        <div style="color:black">sorry..Nothing to display!!.</div>  
        <%} else {%> 
        <table BORDER="1" style="margin:0 auto;text-align:center;border-spacing:10px 0;border-collapse:collapse;">
        <tr class="table-heading">
              <th>S.NO</th>
              <th>PROJECT ID</th>
              <th>PROJECT NAME</th>
              <th>MANAGER</th>
              <th>START DATE</th>
              <th>DOMAIN</th>
        </tr>
        <%for(ProjectDTO projectDTO:projects) {%>
        <tr>
              <td><%=i %></td>
              <td><%=projectDTO.getProjectId() %></td>
              <td><%=projectDTO.getName() %></td>
              <td><%=projectDTO.getManager() %></td>
              <td><%=projectDTO.getStartDate() %></td>
              <td><%=projectDTO.getDomainName() %></td>
              <td>
              <form action = "viewSingleProject" method = "get" style="text-align:center;">
              <input type = "hidden" name = "projectId" value="<%=projectDTO.getProjectId() %>" pattern = "[1-9][0-9]{0,4}" required/><br>
              <a><input class="view-single" type = "submit" value ="view details"/></a><br><br>
               </form>
             </td>
            </tr>
        <% i++;
        } %>
        </table>
        <%} %>
        <br>
        <a href="ProjectMenu.jsp"><button class="button">Project Menu</button></a>
        </div>
</body>
</html>