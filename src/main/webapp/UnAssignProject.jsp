<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.ideas2it.employeemanagement.model.ProjectDTO"
    import="java.util.Set" import="java.util.HashSet"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="resources/css/style.css">
<title>Assign and unassign display</title>
</head>
<style>
.button{
	width:250px;
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
    <h2>UnAssign Project</h2>
    <div style=" display:block; font-size:25px;color:black">
    <%Set<ProjectDTO> projects = (HashSet<ProjectDTO>)request.getAttribute("projects");
    if((!projects.isEmpty()) && (null != projects)) {%>
    
    Projects:
        <form action = "unAssign" method = "get">
        <table border="1" style="margin: 0px auto;background-color:white;">
            <tr>
              <th>  </th>
              <th>Project Id</th>
              <th>Project Name</th>
              <th>Manager</th>
              <th>Start Date</th>
              <th>Domain</th>
            </tr>
        <%
        for(ProjectDTO project:projects){%>
            <tr>
              <td><input type="checkbox" value="<%=project.getProjectId()%>" name="projectIds"></td>
              <td><%=project.getProjectId() %></td>
              <td><%=project.getName() %></td>
              <td><%=project.getManager() %></td>
              <td><%=project.getStartDate() %></td>
              <td><%=project.getDomainName() %></td>
              <td>
              </td>
            </tr>
        <%} %>
    </table>
    <input type = "hidden" name = "employeeId" value=<%=request.getParameter("employeeId")%> pattern = "[1-9][0-9]{0,4}"/><br />
    <input class="button" type = "submit" value = "UnAssign"/><br />
    <input class="button" type="submit" formaction="viewSingle" value="Back">
</form>
    <a href="EmployeeMenu.jsp"><button class="button">Employee menu</button></a> 

     <%} else {%>
     <%="No Projects to UnAssign!!"%>
     <form action = "viewSingle" method = "get">
    <input type = "hidden" name = "employeeId" value=<%=request.getParameter("employeeId")%> pattern = "[1-9][0-9]{0,4}"/><br />
    <input class="button" type="submit" formaction="viewSingle" value="Back">
    </form>
     <%} %>
       
     </div>
</body>
</html>