/*
 * Copyrights (C) Ideas2IT
 */

package com.ideas2it.employeemanagement.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ideas2it.employeemanagement.exception.EmployeeManagementException;
import com.ideas2it.employeemanagement.logger.EmployeeManagementLogger;
import com.ideas2it.employeemanagement.model.EmployeeVO;
import com.ideas2it.employeemanagement.model.ProjectDTO;
import com.ideas2it.employeemanagement.service.EmployeeService;
import com.ideas2it.employeemanagement.service.ProjectService;
import com.ideas2it.employeemanagement.service.impl.EmployeeServiceImplementation;
import com.ideas2it.employeemanagement.service.impl.ProjectServiceImplementation;

/**
 * ProjectServlet performs project management system function. 
 * It gets inputs from the corresponding JSP pages through HttpServlet
 *  and performs appropriate functions.
 */
public class ProjectServlet extends HttpServlet {


	private static final long serialVersionUID = 1L;

	/**
	 * This method handles the Http GET requests. It gets the request from the 
	 * user and Forward it the appropriate methods.  
	 *  
	 * @param request- Http servlet request containing request information
	 * @param response-Http servlet response containing response information
	 */
	protected void doGet(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		String types = request.getParameter("type");  

		switch (types) {
		case "assign&unassign":
			assignAndUnassign(request, response);
			break;
		case "assign":
			assignEmployee(request, response);
			break;
		case "unassign":
			unAssignEmployee(request, response);
			break;
		}
	}
	
	/**
	 * This method handles the Http POST methods. It gets the request from the 
	 * user and Forward it the appropriate methods.
	 * 
	 * @param request- Http servlet request containing request information
	 * @param response-Http servlet response containing response information
	 */
	protected void doPost(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		String types = request.getParameter("type");  

		switch (types) {
		case "create" :
			createProject(request,response);
			break;
		case "view" :
			viewSingleProject(request,response);
			break;
		case "viewAll" :
			viewAllProjects(request,response);
			break;
		case "delete" :
			deleteProject(request,response);
			break;
		case "AddAddress" :
			//addAddress(request,response);
			break;
		case "update" :
			updateProject(request,response);
			break;
		}		
	}
	
	/**
	 * Creates a new project using the data retrieved from the user.
	 * 
	 * @param request- Http servlet request containing request information
	 * @param response-Http servlet response containing response information
	 */
	public void createProject(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
        ProjectDTO returnedProjectDTO = null;
        ProjectService projectService = new ProjectServiceImplementation();
        ProjectDTO projectDTO = new ProjectDTO(request.getParameter("name"), 
        		request.getParameter("domain"), 
        		LocalDate.parse(request.getParameter("start_date")), 
        		request.getParameter("manager"));
        try {
        	returnedProjectDTO = projectService.createProject(projectDTO);
        	request.setAttribute("returnedProjectDTO", returnedProjectDTO);
        	request.getRequestDispatcher("CreateProject.jsp")
        	    .forward(request, response);
        } catch (EmployeeManagementException exception) {
            EmployeeManagementLogger.logger.error(exception);
        }
	}
	
	/**
	 * Deletes single project using the projectId given by user.
	 * 
	 * @param request- Http servlet request containing request information
	 * @param response-Http servlet response containing response information
	 */
	public void deleteProject(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int idToDelete = 0;
        boolean isRecordDeleted = false;
        String message = "Project deleted Successfully";
        String error = "Project Delete unsuccessful!!";
        ProjectDTO returnedProjectDTO = null;
        ProjectService projectService = new ProjectServiceImplementation();
        try {
        	idToDelete = Integer.parseInt(request.getParameter("projectId"));
        	returnedProjectDTO = projectService.getProjectById(idToDelete);
        	if(null != returnedProjectDTO) {
        		isRecordDeleted = projectService
        				.deleteOneProject(returnedProjectDTO);
        	}
        	request.setAttribute("isUpdated", isRecordDeleted);
            request.setAttribute("message", message);
            request.setAttribute("error", error);

	    	request.getRequestDispatcher("Updated.jsp")
	    	    .forward(request, response);
        } catch (EmployeeManagementException exception) {
            EmployeeManagementLogger.logger.error(exception);
        }
	}
	
	/**
	 * Displays single project to the user.
	 * 
	 * @param request- Http servlet request containing request information
	 * @param response-Http servlet response containing response information
	 */
	public void viewSingleProject(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
        ProjectService projectService = new ProjectServiceImplementation();
        ProjectDTO projectDTO = null;
        int projectId = 0;
        try {
        	projectId = Integer.parseInt(request.getParameter("projectId"));
        	projectDTO = projectService.getProjectById(projectId);
        	request.setAttribute("returnedProjectDTO", projectDTO);
        	request.getRequestDispatcher("SingleProjectDisplay.jsp")
        	    .forward(request, response);
        } catch (EmployeeManagementException exception) {
            EmployeeManagementLogger.logger.error(exception);
        }
	}
	
	/**
	 * Displays all Projects.
	 * 
	 * @param request- Http servlet request containing request information
	 * @param response-Http servlet response containing response information
	 */
	public void viewAllProjects(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
        ProjectService projectService = new ProjectServiceImplementation();
        List<ProjectDTO> projects = null;
        try {
        	projects = projectService.viewAllProject();
        	request.setAttribute("projects", projects);
        	request.getRequestDispatcher("AllProjectDisplay.jsp")
        	    .forward(request, response);
        } catch (EmployeeManagementException exception) {
            EmployeeManagementLogger.logger.error(exception);
        }

	}
	
	/**
	 * Updates a single project with the data given by the user.
	 * 
	 * @param request- Http servlet request containing request information
	 * @param response-Http servlet response containing response information
	 */
	public void updateProject(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		 ProjectService projectService = new ProjectServiceImplementation();
	     ProjectDTO projectDTO = null;
	     boolean isUpdated = false;
	     String message = "Project updated Successfully";
	     String error = "project not updated";
	     int projectId = 0;
	     try {
	         projectId = Integer.parseInt(request.getParameter("projectId"));
	         projectDTO = projectService.getProjectById(projectId);

	         if(null != projectDTO ) {
	             projectDTO.setProjectId(projectId);
	             projectDTO.setName(request.getParameter("name"));
	             projectDTO.setDomainName(request.getParameter("domain"));
	             projectDTO.setStartDate(LocalDate.parse(request
	            		 .getParameter("start_date")));
	             projectDTO.setManager(request.getParameter("manager"));
		         isUpdated = projectService.updateAllFields(projectDTO);
	         }
	         request.setAttribute("isUpdated", isUpdated);
	         request.setAttribute("projectDTO", projectDTO);
         	request.setAttribute("error", error);

             request.setAttribute("projectDTO", projectDTO);
             request.setAttribute("message", message);

 	    	request.getRequestDispatcher("Updated.jsp")
 	    	    .forward(request, response);

	     } catch (EmployeeManagementException exception) {
	             EmployeeManagementLogger.logger.error(exception);
	     }
	}
	
	/**
	 * Displays the employees assigned or unassigned in a projects based 
	 * on user's choice. And forward data to appropriate JSP pages.
	 * 
	 * @param request- Http servlet request containing request information
	 * @param response-Http servlet response containing response information
	 */
    public void assignAndUnassign(HttpServletRequest request,
    		HttpServletResponse response) throws ServletException, IOException {
		ProjectDTO projectDTO = null;
		int projectId = 0;
		String type = "";
        Set<EmployeeVO> assignedEmployees = null;
        List<EmployeeVO> allEmployees = null;
		ProjectService projectService = new ProjectServiceImplementation();
        try {
        	projectId = Integer.parseInt(request.getParameter("projectId"));
        	projectDTO = projectService.getProjectById(projectId);
        	type = request.getParameter("action");
        	if( null != projectDTO) {
        		assignedEmployees = projectDTO.getEmployeesVO();
        		allEmployees = projectService.getAllEmployeeDTOs();
                if (assignedEmployees.isEmpty() 
                		|| (null == assignedEmployees)) {
                	assignedEmployees = new HashSet<EmployeeVO>();
                }
        	}
        	if(type.equals("assign")) {
        		allEmployees.removeAll(assignedEmployees);
            	request.setAttribute("employees", allEmployees);
        	    request.getRequestDispatcher("AssignEmployee.jsp")
        	        .forward(request, response);
        	} else {
            	request.setAttribute("employees", assignedEmployees);
            	request.getRequestDispatcher("UnAssignEmployee.jsp")
            	    .forward(request, response);
        	}
        } catch (EmployeeManagementException exception) {
            EmployeeManagementLogger.logger.error(exception);
        }
    }
	
	/**
	 * Assigns employees to the project.
	 * 
	 * @param request- Http servlet request containing request information
	 * @param response-Http servlet response containing response information
	 */
    public void assignEmployee(HttpServletRequest request,
    		HttpServletResponse response) throws ServletException, IOException {
		ProjectService projectService = new ProjectServiceImplementation();
		int projectId = 0;
		ProjectDTO projectDTO = null;
		Set<EmployeeVO> assignedEmployees = null;
		List<EmployeeVO> employeesToAssign;
        String message = "Employee Assigned Successfully";
        String error = "Employee Assign not successful";
        boolean isUpdated = false;
        int employeeIds[] = new int[1];
        try {
        	projectId = Integer.parseInt(request.getParameter("projectId"));
        	projectDTO = projectService.getProjectById(projectId);
        	if( null != projectDTO) {
        		assignedEmployees = projectDTO.getEmployeesVO();
        		employeeIds[0] = Integer.parseInt(request
        				.getParameter("employeeId"));
        		employeesToAssign = projectService.getEmployeeDTOs(employeeIds);
                if (assignedEmployees.isEmpty() 
                		|| (null == assignedEmployees)) {
                	assignedEmployees = new HashSet<EmployeeVO>();
                }
                employeesToAssign.removeAll(assignedEmployees);
                for (EmployeeVO employee:employeesToAssign){
                	assignedEmployees.add(employee);
                }
                projectDTO.setEmployeesVO(assignedEmployees);
                isUpdated = projectService.updateAllFields(projectDTO);
                request.setAttribute("isUpdated", isUpdated);
            	request.setAttribute("error", error);

                request.setAttribute("projectDTO", projectDTO);
                request.setAttribute("message", message);

    	    	request.getRequestDispatcher("Updated.jsp")
    	    	    .forward(request, response);
        	}
        } catch (EmployeeManagementException exception) {
            EmployeeManagementLogger.logger.error(exception);
        }
    }
	
	/**
	 * Unassigns employees present in the project.
	 * 
	 * @param request- Http servlet request containing request information
	 * @param response-Http servlet response containing response information
	 */
    public void unAssignEmployee(HttpServletRequest request,
    		HttpServletResponse response) throws ServletException, IOException {
		ProjectService projectService = new ProjectServiceImplementation();
		int projectId = 0;
		ProjectDTO projectDTO = null;
		Set<EmployeeVO> assignedEmployees = null;
		List<EmployeeVO> employeesToUnAssign;
        String message = "Employee UnAssigned Successfully";
        String error = "Employee UnAssign not successful";
        boolean isUpdated = false;
        int employeeIds[] = new int[1];
        try {
        	projectId = Integer.parseInt(request.getParameter("projectId"));
        	projectDTO = projectService.getProjectById(projectId);
        	if( null != projectDTO) {
        		assignedEmployees = projectDTO.getEmployeesVO();
        		employeeIds[0] = Integer.parseInt(request
        				.getParameter("employeeId"));
        		employeesToUnAssign 
        		    = projectService.getEmployeeDTOs(employeeIds);
                if (assignedEmployees.isEmpty() 
                		|| (null == assignedEmployees)) {
                	assignedEmployees = new HashSet<EmployeeVO>();
                }
                assignedEmployees.removeAll(employeesToUnAssign);
                projectDTO.setEmployeesVO(assignedEmployees);
                isUpdated = projectService.updateAllFields(projectDTO);
                request.setAttribute("isUpdated", isUpdated);
            	request.setAttribute("error", error);

                request.setAttribute("projectDTO", projectDTO);
                request.setAttribute("message", message);

    	    	request.getRequestDispatcher("Updated.jsp")
    	    	    .forward(request, response);
        	}
        } catch (EmployeeManagementException exception) {
            EmployeeManagementLogger.logger.error(exception);
        }
    }
	
}
