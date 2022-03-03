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

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ideas2it.employeemanagement.exception.EmployeeManagementException;
import com.ideas2it.employeemanagement.logger.EmployeeManagementLogger;
import com.ideas2it.employeemanagement.model.EmployeeVO;
import com.ideas2it.employeemanagement.model.ProjectDTO;
import com.ideas2it.employeemanagement.service.EmployeeService;
import com.ideas2it.employeemanagement.service.ProjectService;
import com.ideas2it.employeemanagement.service.impl.EmployeeServiceImplementation;
import com.ideas2it.employeemanagement.service.impl.ProjectServiceImplementation;

/**
 * ProjectServlet performs project management system function. It gets inputs
 * from the corresponding JSP pages through HttpServlet and performs appropriate
 * functions.
 * 
 * @author Niraimathi S
 * @version 1.2
 * 
 */
@Controller
public class ProjectServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    ProjectService projectService; 
    
	public void setProjectService(ProjectService projectService) {
		this.projectService = projectService;
	}

	/**
	 * Create a new ProjectDTO object and forward it to create a new Project.
	 * 
	 * @param model
	 * @return CreateProject create page.
	 */
	@RequestMapping(value="/createNewProject", method = RequestMethod.GET)
    public String createNewProject(Model model){
		model.addAttribute("projectDTO",new ProjectDTO());
		System.out.println("createnew project");
		return "CreateProject";
	}
	
	/**
	 * Creates a new project using the data retrieved from the user.
	 * 
	 * @param projectDTO object with user entered values.
	 * @param model
	 * @return CreateProject - create project page.
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value = "/createProject", method = RequestMethod.POST) 
	public String createProject(@ModelAttribute("projectDTO") 
			ProjectDTO projectDTO, Model model) 
			throws ServletException, IOException { 
		try {
			projectDTO = projectService.createProject(projectDTO);
			model.addAttribute("projectDTO", projectDTO);
		}  catch (EmployeeManagementException exception) {
            EmployeeManagementLogger.logger.error(exception);
        }
		model.addAttribute("returnedProject", projectDTO);
		return "CreateProject";
	}

	/**
	 * Deletes a project from database.
	 * 
	 * @param projectId to delete the project.
	 * @param model
	 * @return AllProjectDisplay which displays all projects.
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping("/deleteProject") 
	public String deleteProject(@RequestParam Integer projectId, Model model) 
			throws ServletException, IOException {
		ProjectDTO returnedProjectDTO= null;
		List<ProjectDTO> projects = null;
		try { 
			returnedProjectDTO = projectService.getProjectById(projectId);
			if (null !=returnedProjectDTO) { 
				projectService.deleteOneProject(returnedProjectDTO);
	    		projects = projectService.viewAllProject();
	    		model.addAttribute("projects", projects);
			}
		} catch (EmployeeManagementException exception) {
			EmployeeManagementLogger.logger.error(exception);
		}
		return "AllProjectDisplay";
	}
  
	/**
	 * Displays a single project to the user.
	 * 
	 * @param projectId to display project.
	 * @param model
	 * @return SingleProjectDisplay - page which display the project.
	 * @throws ServletException
	 * @throws IOException
	 */
    @RequestMapping(value = "/viewSingleProject", method = RequestMethod.GET)
    public String viewSingleProject(@RequestParam Integer projectId,
    		Model model) throws ServletException, IOException { 
    	  ProjectDTO projectDTO = null; 
    	  try { 
    		  projectDTO = projectService.getProjectById(projectId);
    		  model.addAttribute("projectDTO", projectDTO);
    		  model.addAttribute("project", projectDTO);
    		  model.addAttribute("employeeList", projectDTO.getEmployeesVO());
    		  model.addAttribute("isUpdated", false);
    	  } catch (EmployeeManagementException exception) {
    		  EmployeeManagementLogger.logger.error(exception); 
    	  }
    	  return "SingleProjectDisplay";
      }
  
    /**
     * Displays all projects to the user.
     * 
     * @param model
     * @return AllProjectDisplay -the page where all projects are displayed.
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping("/viewAllProject") 
    public String viewAllProjects(Model model)
    		throws ServletException, IOException { 
    	List<ProjectDTO> projects = null;
    	try { 
    		projects = projectService.viewAllProject();
    		model.addAttribute("projects", projects);
    	} catch (EmployeeManagementException exception) {
    		EmployeeManagementLogger.logger.error(exception); 
    	}
    	return "AllProjectDisplay";
  }
  
    /**
     * Gets the object to be updated and forward it to the update page.
     * 
     * @param projectId
     * @param model
     * @return UpdateProject, the page where the object is forwarded.
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String update(@RequestParam Integer projectId, Model model) 
    		throws ServletException, IOException { 
    	ProjectDTO projectDTO =null; 
    	try { 
    		projectDTO = projectService.getProjectById(projectId);
    		model.addAttribute("projectDTO", projectDTO);
    	} catch (EmployeeManagementException exception) {
    		EmployeeManagementLogger.logger.error(exception); 
    	}
    	return "UpdateProject";
    }
  
    /**
     * Updates all the fields of a project.
     * 
     * @param project projectDTO object which is to be updated.
     * @param projectId the ID of the updated project.
     * @param model
     * @return SingleProjectDisplay - name of the page which Displays the
     * 							  updated project.
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping(value = "/updateProject", method = RequestMethod.GET)
    public String updateProject(@ModelAttribute("projectDTO") 
    		ProjectDTO project, @RequestParam Integer projectId, Model model) 
    		throws ServletException, IOException { 
    	boolean isUpdated = false; 
    	ProjectDTO projectDTO = null;
    	try { 
    		if (null != project ) {
    			projectDTO = projectService.getProjectById(projectId);
    			if (null != projectDTO ) {
    				projectDTO.setProjectId(projectId);
    				projectDTO.setName(project.getName());
    				projectDTO.setDomainName(project.getDomainName());
    				projectDTO.setStartDate(project.getStartDate());
    				projectDTO.setManager(project.getManager());
    			isUpdated = projectService.updateAllFields(projectDTO);
    			}
    		}
    		model.addAttribute("projectDTO", projectDTO);
    		model.addAttribute("isUpdated",isUpdated);
    	} catch (EmployeeManagementException exception) {
    		EmployeeManagementLogger.logger.error(exception); 
    	}
    	return "SingleProjectDisplay";
    }
    
    /**
     * Gets the project object to assign or un assign employee and forward it to
     *  the corresponding page.
     * 
     * @param projectId to assign or unassign employees.
     * @param type the type of operation to be performed.
     * @param model
     * @return forward page name.
     * @throws ServletException
     * @throws IOException
     */
	@RequestMapping(value = "/assign&unAssign", method = RequestMethod.GET)
    public String assignProject(@RequestParam Integer projectId, @RequestParam String type, Model model) throws ServletException, IOException {
		ProjectDTO projectDTO = null;
		Set<EmployeeVO> assignedEmployees = null;
        List<EmployeeVO> allEmployees = null;
        String returnPage = "";
        try {
        	projectDTO = projectService.getProjectById(projectId);
        	if (null != projectDTO) {
        		assignedEmployees = projectDTO.getEmployeesVO();
        		allEmployees = projectService.getAllEmployeeDTOs();
        		if (assignedEmployees.isEmpty() 
                		|| (null == assignedEmployees)) {
                	assignedEmployees = new HashSet<EmployeeVO>();
                }
        		if (type.equals("assign")) {
            		allEmployees.removeAll(assignedEmployees);
            		model.addAttribute("employees", allEmployees);
            		returnPage = "AssignEmployee";
            	} else {
            		model.addAttribute("employees", assignedEmployees);
            		returnPage = "UnAssignEmployee";
            	}
        		model.addAttribute("projectId", projectId);
        	}
        } catch (EmployeeManagementException exception) {
            EmployeeManagementLogger.logger.error(exception);
        }
        return returnPage;
    }

	/**
	 * Assign Employees to the project.
	 * 
	 * @param projectId the ID of the project to  which the employees to 
	 * be assigned.
	 * @param employeeIds employee IDs  toassign to the project.
	 * @param model
	 * @return AssignEmployee- the page to unassign Employees.
	 * @throws ServletException
	 * @throws IOException
	 */
    @RequestMapping(value = "/assignEmployee", method = RequestMethod.GET)
    public String assignEmployee(@RequestParam Integer projectId,
    		@RequestParam("employeeIds") int[] employeeIds, Model model) 
    		throws ServletException, IOException {
    	ProjectDTO projectDTO = null;
        List<EmployeeVO> allEmployees = null;
        Set<EmployeeVO> assignedEmployees = null;
        List<EmployeeVO> employeesToAssign; 
        try {
        	projectDTO = projectService.getProjectById(projectId);
        	allEmployees = projectService.getAllEmployeeDTOs();
        	if ( null != projectDTO) {
        		assignedEmployees = projectDTO.getEmployeesVO();
        		employeesToAssign 
        			= projectService.getEmployeeDTOs(employeeIds);
                if (assignedEmployees.isEmpty() 
                		|| (null == assignedEmployees)) {
                	assignedEmployees = new HashSet<EmployeeVO>();
                }
                employeesToAssign.removeAll(assignedEmployees);
                for (EmployeeVO employee:employeesToAssign){
                	assignedEmployees.add(employee);
                }
                projectDTO.setEmployeesVO(assignedEmployees);
                projectService.updateAllFields(projectDTO);
                allEmployees.removeAll(assignedEmployees);

        	}
        } catch (EmployeeManagementException exception) {
            EmployeeManagementLogger.logger.error(exception);
        }
        model.addAttribute("projectId", projectId);
        model.addAttribute("employees",allEmployees);
        return "AssignEmployee";
    }
  
	/**
	 * UnAssign Employees to the project.
	 * 
	 * @param projectId the ID of the project from which the employees to 
	 * be unassigned.
	 * @param employeeIds employee IDs  to unassign to the project.
	 * @param model
	 * @return AssignEmployee- the page to assign Employees.
	 * @throws ServletException
	 * @throws IOException
	 */
    @RequestMapping(value = "/UnAssignEmployee", method = RequestMethod.GET)
    public String unAssignEmployee(@RequestParam Integer projectId, 
    		@RequestParam("employeeIds") int[] employeeIds, Model model) 
    		throws ServletException, IOException {
    	ProjectDTO projectDTO = null;
        Set<EmployeeVO> assignedEmployees = null;
        List<EmployeeVO> employeesToUnAssign; 
        try {
        	projectDTO = projectService.getProjectById(projectId);
        	if ( null != projectDTO) {
        		assignedEmployees = projectDTO.getEmployeesVO();
        		employeesToUnAssign 
        		= projectService.getEmployeeDTOs(employeeIds);
                if (assignedEmployees.isEmpty() 
                		|| (null == assignedEmployees)) {
                	assignedEmployees = new HashSet<EmployeeVO>();
                }
                assignedEmployees.removeAll(employeesToUnAssign);
                projectDTO.setEmployeesVO(assignedEmployees);
                projectService.updateAllFields(projectDTO);

        	}
        } catch (EmployeeManagementException exception) {
            EmployeeManagementLogger.logger.error(exception);
        }
        model.addAttribute("projectId", projectId);
        model.addAttribute("employees",assignedEmployees);
        return "UnAssignEmployee";
    }
    
}