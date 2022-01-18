<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.ideas2it.employeemanagement.model.ProjectDTO"
    import="java.util.List" import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="style.css">
<title>Assign and unassign display</title>
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
    <div style=" display:block; font-size:25px">
    <h2>Assign Project</h2>
    <%List<ProjectDTO> projects = (ArrayList<ProjectDTO>)request.getAttribute("projects");
    if((projects.isEmpty()) || (null == projects)) {%>
    <%="No new Employees to Assign!!"%>
   <%} else {%>
    <h3>Projects:</h3>
        <table border="1"  style="margin: 0px auto;">
            <tr>
              <th>Project Id</th>
              <th>Project Name</th>
              <th>Manager</th>
              <th>Start Date</th>
              <th>Domain</th>
            </tr>
        <%
        for(ProjectDTO project:projects){%>
            <tr>
              <td><%=project.getProjectId() %></td>
              <td><%=project.getName() %></td>
              <td><%=project.getManager() %></td>
              <td><%=project.getStartDate() %></td>
              <td><%=project.getDomainName() %></td>
              <td>
                <form action = "EmployeeServlet" method = "get">
                <input type="hidden" name="type" value="assign">
                <input type = "hidden" name = "employeeId" value=<%=request.getParameter("employeeId")%> pattern = "[1-9][0-9]{0,4}"/><br />
                <input type = "hidden" name = "projectId" value="<%=project.getProjectId()%>" pattern = "[1-9][0-9]{0,4}"/><br />
                <input type = "submit" value = "Assign"/><br />
                </form>
              </td>
            </tr>
        <%} %>
        </table> 
        <%}%>
                 <input type ="button" value = "Back" onclick="history.back()"/>
         <button><a href="EmployeeMenu.jsp">Employee Main menu</a></button>  
     </div>
</body>
</html>