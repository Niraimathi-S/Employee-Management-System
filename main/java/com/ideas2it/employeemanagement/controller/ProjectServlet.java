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

@WebServlet(urlPatterns = { "/ProjectServlet" })
public class ProjectServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String types = request.getParameter("type");  

		switch (types) {
		case "create" :
			createProject(request,response);
			break;
		case "view" :
			viewProject(request,response);
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
	
	public void viewProject(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		String types = request.getParameter("type"); 
        ProjectService projectService = new ProjectServiceImplementation();
        try {
    	ProjectDTO projectDTO = projectService.getProjectById(Integer.parseInt(request.getParameter("projectId")));
    	request.setAttribute("returnedProjectDTO", projectDTO);
		switch (types) {
		case "update" :
			//updateProject(request,response);
			break;
		case "delete" :
			deleteProject(request,response);
			break;
		default :
			//viewSingleProject(request,response);
        	request.getRequestDispatcher("SingleProjectDisplay.jsp").forward(request, response);
			break;
		}
        } catch (EmployeeManagementException exception) {
            EmployeeManagementLogger.logger.error(exception);
        }
	}
	
	public void createProject(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        ProjectDTO returnedProjectDTO = null;
        ProjectService projectService = new ProjectServiceImplementation();
        ProjectDTO projectDTO = new ProjectDTO(request.getParameter("name"), request.getParameter("domain"), 
        		LocalDate.parse(request.getParameter("start_date")), request.getParameter("manager"));
        try {
        	returnedProjectDTO = projectService.createProject(projectDTO);
        	request.setAttribute("returnedProjectDTO", returnedProjectDTO);
        	request.getRequestDispatcher("CreateProject.jsp").forward(request, response);
        } catch (EmployeeManagementException exception) {
            EmployeeManagementLogger.logger.error(exception);
        }
	}
	
	public void deleteProject(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
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
        		isRecordDeleted = projectService.deleteOneProject(returnedProjectDTO);
        	}
        	request.setAttribute("isUpdated", isRecordDeleted);
            request.setAttribute("message", message);
            request.setAttribute("error", error);

	    	request.getRequestDispatcher("Updated.jsp").forward(request, response);
        } catch (EmployeeManagementException exception) {
            EmployeeManagementLogger.logger.error(exception);
        }
	}
	
	public void viewSingleProject(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        ProjectService projectService = new ProjectServiceImplementation();
        ProjectDTO projectDTO = null;
        int projectId = 0;
        try {
        	projectId = Integer.parseInt(request.getParameter("projectId"));
        	projectDTO = projectService.getProjectById(projectId);
        	request.setAttribute("returnedProjectDTO", projectDTO);
        	request.getRequestDispatcher("SingleProjectDisplay.jsp").forward(request, response);
        } catch (EmployeeManagementException exception) {
            EmployeeManagementLogger.logger.error(exception);
        }
	}
	
	public void viewAllProjects(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        ProjectService projectService = new ProjectServiceImplementation();
        List<ProjectDTO> projects = null;
        try {
        	projects = projectService.viewAllProject();
        	request.setAttribute("projects", projects);
        	request.getRequestDispatcher("AllProjectDisplay.jsp").forward(request, response);
        } catch (EmployeeManagementException exception) {
            EmployeeManagementLogger.logger.error(exception);
        }

	}
	
	public void updateProject(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		 ProjectService projectService = new ProjectServiceImplementation();
	     ProjectDTO projectDTO = null;
	     ProjectDTO returnedprojectDTO = null;
	     boolean isUpdated = false;
	     int projectId = 0;
	     try {
	         projectId = Integer.parseInt(request.getParameter("projectId"));
	         projectDTO = projectService.getProjectById(projectId);
	         //returnedprojectDTO = projectDTO;

	         if(null != projectDTO ) {
	             projectDTO.setProjectId(projectId);
	             projectDTO.setName(request.getParameter("name"));
	             projectDTO.setDomainName(request.getParameter("domain"));
	             projectDTO.setStartDate(LocalDate.parse(request.getParameter("start_date")));
	             projectDTO.setManager(request.getParameter("manager"));
		         returnedprojectDTO = projectDTO;
		         isUpdated = projectService.updateAllFields(projectDTO);
	         }
	         //System.out.println("after returnedprojectDTO equals"+(projectDTO==returnedprojectDTO));
	         request.setAttribute("isUpdated", isUpdated);
	         request.setAttribute("projectDTO", projectDTO);
	         request.getRequestDispatcher("UpdateProject.jsp").forward(request, response);

	     } catch (EmployeeManagementException exception) {
	             EmployeeManagementLogger.logger.error(exception);
	     }
	}
	
    public void assignAndUnassign(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
        	//System.out.println("type in assign if else "+type+"\n"+type=="assign"+type.equals("assign"));
        	//employeeVO = employeeService.getEmployeeById(employeeId);
        	if( null != projectDTO) {
        		assignedEmployees = projectDTO.getEmployeesVO();
        		allEmployees = projectService.getAllEmployeeDTOs();
                if (assignedEmployees.isEmpty() || (null == assignedEmployees)) {
                	assignedEmployees = new HashSet<EmployeeVO>();
                }
                	//allProjects.removeAll(assignedProjects);
        	}
        	//request.setAttribute("projects", allProjects);
        	if(type.equals("assign")) {
        		allEmployees.removeAll(assignedEmployees);
            	request.setAttribute("employees", allEmployees);
        	    request.getRequestDispatcher("AssignEmployee.jsp").forward(request, response);
        	} else {
            	request.setAttribute("employees", assignedEmployees);
            	request.getRequestDispatcher("UnAssignEmployee.jsp").forward(request, response);
        	}
        } catch (EmployeeManagementException exception) {
            EmployeeManagementLogger.logger.error(exception);
        }
    }
    
    public void assignEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
        		employeeIds[0] = Integer.parseInt(request.getParameter("employeeId"));
        		employeesToAssign = projectService.getEmployeeDTOs(employeeIds);
                if (assignedEmployees.isEmpty() || (null == assignedEmployees)) {
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

    	    	request.getRequestDispatcher("Updated.jsp").forward(request, response);
        	}
        } catch (EmployeeManagementException exception) {
            EmployeeManagementLogger.logger.error(exception);
        }
    }

    public void unAssignEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
        		employeeIds[0] = Integer.parseInt(request.getParameter("employeeId"));
        		employeesToUnAssign = projectService.getEmployeeDTOs(employeeIds);
                if (assignedEmployees.isEmpty() || (null == assignedEmployees)) {
                	assignedEmployees = new HashSet<EmployeeVO>();
                }
                assignedEmployees.removeAll(employeesToUnAssign);
                projectDTO.setEmployeesVO(assignedEmployees);
                isUpdated = projectService.updateAllFields(projectDTO);
                request.setAttribute("isUpdated", isUpdated);
            	request.setAttribute("error", error);

                request.setAttribute("projectDTO", projectDTO);
                request.setAttribute("message", message);

    	    	request.getRequestDispatcher("Updated.jsp").forward(request, response);
        	}
        } catch (EmployeeManagementException exception) {
            EmployeeManagementLogger.logger.error(exception);
        }
    }
	
}
