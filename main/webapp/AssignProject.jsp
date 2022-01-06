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
    <%List<ProjectDTO> projects = (ArrayList<ProjectDTO>)request.getAttribute("projects");
    if((!projects.isEmpty()) && (null != projects)) {%>
    
    Projects:
        <table border="1">
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
    <%} %>
</body>
</html>