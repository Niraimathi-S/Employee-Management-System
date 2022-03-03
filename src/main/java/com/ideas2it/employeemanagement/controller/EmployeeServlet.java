/*
 * Copyrights (C) Ideas2IT
 */

package com.ideas2it.employeemanagement.controller;

import java.io.IOException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ideas2it.employeemanagement.exception.EmployeeManagementException;
import com.ideas2it.employeemanagement.logger.EmployeeManagementLogger;
import com.ideas2it.employeemanagement.model.AddressDTO;
import com.ideas2it.employeemanagement.model.EmployeeVO;
import com.ideas2it.employeemanagement.model.ProjectDTO;
import com.ideas2it.employeemanagement.service.EmployeeService;

/**
 * Performs Employee management system's employee side functions. 
 * It gets inputs from the corresponding JSP pages through HttpServlet
 *  and performs appropriate functions.
 *  
 *  @author Niraimathi S
 *  @version 1.2
 */
@Controller
public class EmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	EmployeeService employeeService;
	
	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	/**
	 * Shows index page.
	 * 
	 * @return- index page
	 */
	@RequestMapping(value = "/")
	public String showIndex() {
		return "index";
	}
	
	/**
	 * Creates a new employee from the values given by the user.
	 * 
	 * @param employeeVO EmployeeVO object with values given by the user.
	 * @param model Contains the objects of the web page.
	 * @return name of the JSP page to display values.
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String createEmployee(@ModelAttribute("employeeVO")
			EmployeeVO employeeVO, Model model) 
			throws ServletException, IOException  {
        EmployeeVO returnedEmployee = null;
        AddressDTO addressDTO =  employeeVO.getAddressDTO();
        boolean isDublicateEmail =  false;
        boolean isDublicateMobileNumber = false;
    	try {
    		isDublicateMobileNumber = employeeService
    			.checkDuplicateMobileNumber(employeeVO.getMobileNumber());
    		isDublicateEmail = employeeService
    			.checkDuplicateEmail(employeeVO.getEmail());
    		if ((true != isDublicateMobileNumber) 
    				&& (true != isDublicateEmail)) {
				Set<AddressDTO> addressList = new HashSet<AddressDTO>();
				addressList.add(addressDTO);
				employeeVO.setaddressesDTO(addressList);
				returnedEmployee = employeeService
					.createEmployee(employeeVO);
    		}
        } catch (EmployeeManagementException exception) {
            EmployeeManagementLogger.logger.error(exception);
        }
		model.addAttribute("isDublicateMobileNumber", isDublicateMobileNumber);
    	model.addAttribute("isDublicateEmail", isDublicateEmail);
    	model.addAttribute("employeeVO", employeeVO);
    	model.addAttribute("returnedEmployee", returnedEmployee);
        return "CreateEmployee";
	}
	
	/**
	 * Creates a empty employeeVO object and forward it to the create
	 *  employee page which is to be used for creation of an new Employee.
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/CreateNewEmployee")
    public String CreateNewEmployee(Model model){
		model.addAttribute("employeeVO",new EmployeeVO());
		model.addAttribute("isDublicateMobileNumber", false);
		model.addAttribute("returnedEmployee", null);
		model.addAttribute("isDublicateEmail", false);
		return "CreateEmployee";
	}
    
	/**
	 * Displays a single employee to the user
	 * 
	 * @param employeeId -employee ID to view a single employee.
	 * @param model model Contains the objects of the web page
	 * @return JSP page to display the Employee.
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value = "/viewSingle",method = RequestMethod.GET)
	public String viewSingleEmployee(@RequestParam Integer employeeId, 
			Model model) throws ServletException, IOException {
		EmployeeVO employeeVO = null;
        try {
        	employeeVO = employeeService.getEmployeeById(employeeId);
        	model.addAttribute("returnedEmployee",employeeVO);
        } catch (EmployeeManagementException exception) {
            EmployeeManagementLogger.logger.error(exception);
        }
		
        return "SingleEmployeeDisplay";
	}
	
	/**
	 * Displays all employees in the Database.
	 * 
	 * @param model model Contains the objects of the web page.
	 * @return Name of the page where the employees are displayed.
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping("/viewAll")
	public String viewAllEmployees(Model model) 
			throws ServletException, IOException {
        List<EmployeeVO> employees = null;
        try {
            employees = employeeService.viewAllEmployee();
        } catch (EmployeeManagementException exception) {
            EmployeeManagementLogger.logger.error(exception);
        }
		model.addAttribute("Employees",employees);
        return "AllEmployeeDisplay";
	}
	
    /**
     * Deletes a single Employee using Employee ID.
     * 
     * @param employeeId to delete an employee.
     * @param model Contains the objects of the web page
     * @return page where the response of the method to be displayed.
     * @throws ServletException
     * @throws IOException
     */
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String deleteEmployee(@RequestParam Integer employeeId, 
			Model model) throws ServletException, IOException {
        List<EmployeeVO> employees = null;
		EmployeeVO employeeVO = null;
        try {
        	employeeVO = employeeService.getEmployeeById(employeeId);
        	
        	if (null != employeeVO) {
        		employeeService.deleteOneEmployee(employeeVO);
        		employees = employeeService.viewAllEmployee();
        	}
        } catch (EmployeeManagementException exception) {
            EmployeeManagementLogger.logger.error(exception);
        }
        model.addAttribute("Employees",employees);
		return "AllEmployeeDisplay";
	}
	
	/**
	 * Create a new AddressDTo object and forward it to the add address page 
	 * to add a new address to the employee.
	 * 
	 * @param employeeId to add a new address to the employee.
	 * @param model Contains the objects of the web page
	 * @return Name of the page which the response is being redirected.
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value = "/updateAddress", method = RequestMethod.GET)
    public String updateAddress(@RequestParam Integer employeeId, 
    		Model model) throws ServletException, IOException {
		model.addAttribute("employeeId", employeeId);
		model.addAttribute("addressDTO", new AddressDTO());
		return "AddAddress";
    }
    
	/**
	 * Check an employee ID exist in the database and forward it to updation.
	 * 
	 * @param employeeId to update an employee details.
	 * @param model Contains the objects of the web page
	 * @return Name of the redirecting page.
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value = "/updateEmployee", method = RequestMethod.GET)
    public String updateEmployee(@RequestParam Integer employeeId, 
    		Model model) throws ServletException, IOException {
		EmployeeVO employeeVO = null;
        try {
        	employeeVO = employeeService.getEmployeeById(employeeId);
        } catch (EmployeeManagementException exception) {
            EmployeeManagementLogger.logger.error(exception);
        }
        model.addAttribute("isDublicateMobileNumber", false);
		model.addAttribute("isDublicateEmail", false);
		model.addAttribute("employeeVO",employeeVO);
		return "UpdateAllFields";
    }
	
	/**
	 * Updates all the fields of an Employee.
	 * 
	 * @param employee employee object to update.
	 * @param employeeId Id of the employee which is to be updated.
	 * @param model Contains the objects of the web page
	 * @return Name of the page where the response is being forwarded.
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value = "/updateAllFields", method = RequestMethod.GET)
    public String updateAllFields(@ModelAttribute("employeeVO")
    EmployeeVO employee, @RequestParam int employeeId, Model model) 
    		throws ServletException, IOException {
        boolean isDublicateEmail =  false;
        boolean isDublicateMobileNumber = false;
        String redirectPage =  "";
        EmployeeVO employeeVO = null;
		try {
			if (null != employee) {
				employeeVO = employeeService.getEmployeeById(employeeId);
				if (null != employeeVO) {
					if (!(employeeVO.getEmail().equals(employee.getEmail()))) {
						isDublicateEmail = employeeService
								.checkDuplicateEmail(employee.getEmail());
					}
					if ((employeeVO.getMobileNumber() 
							!= employee.getMobileNumber())) {
						isDublicateMobileNumber = employeeService
								.checkDuplicateMobileNumber(employee
										.getMobileNumber());
					}
					if ((true != isDublicateMobileNumber) 
							&& (true != isDublicateEmail)) {
						employeeVO.setName(employee.getName());
						employeeVO
						    .setMobileNumber(employee.getMobileNumber());
						employeeVO.setEmail(employee.getEmail());
						employeeVO.setSalary(employee.getSalary());
						employeeVO.setDateOfBirth(employee.getDateOfBirth());
						redirectPage = "SingleEmployeeDisplay";
					} else {
						redirectPage = "UpdateAllFields";
					}
				}
			    employeeService.updateAllFields(employeeVO);
			}
        } catch (EmployeeManagementException exception) {
            EmployeeManagementLogger.logger.error(exception);
        }
        model.addAttribute("isDublicateMobileNumber", isDublicateMobileNumber);
		model.addAttribute("isDublicateEmail", isDublicateEmail);
		model.addAttribute("returnedEmployee",employeeVO);
        return redirectPage;
	}

	/**
	 * Deletes an Address from an Employee.
	 * 
	 * @param employeeId to delete address.
	 * @param addressId 
	 * @param model Contains the objects of the web page
	 * @return Name of the page which the response is being redirected.
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value = "/deleteAddress", method = RequestMethod.GET)
    public String deleteAddress(@RequestParam Integer employeeId,
    		@RequestParam Integer addressId, Model model) 
    		throws ServletException, IOException {
        EmployeeVO employeeVO = null;
        AddressDTO addressToDelete = null;
    	try {
            employeeVO = employeeService.getEmployeeById(employeeId);
        	if (null != employeeVO) {
                Set<AddressDTO> addresses = employeeVO.getaddressesDTO();
                for(AddressDTO address:addresses) {
                	if(addressId == address.getAddressId()) {
                		addressToDelete = address;
                	}
                }
                if(null != addressToDelete) {
                	addresses.remove(addressToDelete);
                }
                employeeVO.setaddressesDTO(addresses);
                employeeService.updateAllFields(employeeVO);
        	}
    	} catch (EmployeeManagementException exception) {
            EmployeeManagementLogger.logger.error(exception);
        }
    	model.addAttribute("returnedEmployee", employeeVO);
    	return "SingleEmployeeDisplay";
    }
	
	/**
	 * Adds an address to an employee.
	 * 
	 * @param addressDTO addressDTO object containing values from user.
	 * @param addressId
	 * @param model Contains the objects of the web page
	 * @return Name of the page which the response is being redirected.
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value = "/addAddress", method = RequestMethod.GET)
    public String addAddress(@ModelAttribute("addressDTO") 
    		AddressDTO addressDTO, @RequestParam Integer addressId, 
    		Model model) throws ServletException, IOException {
        EmployeeVO employeeVO = null;
    	try {
            employeeVO = employeeService.getEmployeeById(addressId);
        	if (null != employeeVO) {
                Set<AddressDTO> addresses = employeeVO.getaddressesDTO();
                addresses.add(addressDTO);
                employeeVO.setaddressesDTO(addresses);
                employeeService.updateAllFields(employeeVO);
        	}
    	} catch (EmployeeManagementException exception) {
            EmployeeManagementLogger.logger.error(exception);
        }
    	model.addAttribute("returnedEmployee", employeeVO);
    	return "SingleEmployeeDisplay";
    }
	
	/**
	 * Gets the employee to assign the projects and forward it to the 
	 * corresponding pages.
	 * 
	 * @param employeeId To get the Employee object to assign projects.
	 * @param model Contains the objects of the web page
	 * @return Name of the page which the response is being redirected.
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value = "/assignProject", method = RequestMethod.GET)
    public String assignProject(@RequestParam Integer employeeId, 
    		Model model) throws ServletException, IOException {
		EmployeeVO employeeVO = null;
        Set<ProjectDTO> assignedProjects = null;
        List<ProjectDTO> allProjects = null;
        try {
        	employeeVO = employeeService.getEmployeeById(employeeId);
        	if( null != employeeVO) {
        		assignedProjects = employeeVO.getProjectsDTO();
        		allProjects = employeeService.getAllProjectDTOs();
                if (assignedProjects.isEmpty() || (null == assignedProjects)) {
                    assignedProjects = new HashSet<ProjectDTO>();
                }
        	}
            allProjects.removeAll(assignedProjects);
        } catch (EmployeeManagementException exception) {
            EmployeeManagementLogger.logger.error(exception);
        }
        model.addAttribute("employeeId", employeeId);
        model.addAttribute("projects",allProjects);
        return "AssignProject";
    }
	
	/**
	 * Gets the employee to unassign the projects and forward it to the 
	 * corresponding pages.
	 * 
	 * @param employeeId To get the Employee object to unassign projects.
	 * @param model Contains the objects of the web page
	 * @return Name of the page which the response is being redirected.
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value = "/unassignProject", method = RequestMethod.GET)
    public String unassignProject(@RequestParam Integer employeeId, 
    		Model model) throws ServletException, IOException {
		EmployeeVO employeeVO = null;
        Set<ProjectDTO> assignedProjects = null;
        try {
        	employeeVO = employeeService.getEmployeeById(employeeId);
        	if( null != employeeVO) {
        		assignedProjects = employeeVO.getProjectsDTO();
                if (assignedProjects.isEmpty() || (null == assignedProjects)) {
                    assignedProjects = new HashSet<ProjectDTO>();
                }
        	}
        } catch (EmployeeManagementException exception) {
            EmployeeManagementLogger.logger.error(exception);
        }
        model.addAttribute("employeeId", employeeId);
        model.addAttribute("projects",assignedProjects);
        return "UnAssignProject";
    }
	
	/**
	 * Assign Projects to an employee.
	 * 
	 * @param employeeId to assign projects.
	 * @param projectIds
	 * @param model Contains the objects of the web page
	 * @return Name of the page which the response is being redirected.
	 * @throws ServletException
	 * @throws IOException
	 */
    @RequestMapping(value = "/assign", method = RequestMethod.GET)
    public String assignProject(@RequestParam Integer employeeId, 
    		@RequestParam("projectIds") int[] projectIds, Model model) 
    				throws ServletException, IOException {
    	EmployeeVO employeeVO = null;
        List<ProjectDTO> allProjects = null;
        Set<ProjectDTO> assignedProjects = null;
        List<ProjectDTO> projectsToAssign;
        try {
        	employeeVO = employeeService.getEmployeeById(employeeId);
    		allProjects = employeeService.getAllProjectDTOs();
        	if( null != employeeVO) {
        		assignedProjects = employeeVO.getProjectsDTO();
                projectsToAssign = employeeService.getProjectDTOs(projectIds);
                if (assignedProjects.isEmpty() || (null == assignedProjects)) {
                    assignedProjects = new HashSet<ProjectDTO>();
                }
                projectsToAssign.removeAll(assignedProjects);
                for (ProjectDTO project:projectsToAssign){
                    assignedProjects.add(project);
                }
                employeeVO.setProjectsDTO(assignedProjects);
                employeeService.updateAllFields(employeeVO);
                allProjects.removeAll(assignedProjects);

        	}
        } catch (EmployeeManagementException exception) {
            EmployeeManagementLogger.logger.error(exception);
        }
        model.addAttribute("employeeId", employeeId);
        model.addAttribute("projects",allProjects);
        return "AssignProject";
    }

	/**
	 * UnAssign Projects from an employee.
	 * 
	 * @param employeeId to unassign projects.
	 * @param projectIds
	 * @param model Contains the objects of the web page
	 * @return Name of the page which the response is being redirected.
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value = "/unAssign", method = RequestMethod.GET)
    public String unAssignProject(@RequestParam Integer employeeId,
    		@RequestParam("projectIds") int[] projectIds, Model model) 
    		throws ServletException, IOException {
    	EmployeeVO employeeVO = null;
        Set<ProjectDTO> assignedProjects = null;
        List<ProjectDTO> projectsToUnAssign;
        try {
        	employeeVO = employeeService.getEmployeeById(employeeId);
        	if( null != employeeVO) {
        		assignedProjects = employeeVO.getProjectsDTO();
            	projectsToUnAssign = employeeService
            			.getProjectDTOs(projectIds);
                if (assignedProjects.isEmpty() || (null == assignedProjects)) {
                    assignedProjects = new HashSet<ProjectDTO>();
                }
                assignedProjects.removeAll(projectsToUnAssign);
                employeeVO.setProjectsDTO(assignedProjects);
                employeeService.updateAllFields(employeeVO);
        	}
        } catch (EmployeeManagementException exception) {
            EmployeeManagementLogger.logger.error(exception);
        }
        model.addAttribute("employeeId", employeeId);
        model.addAttribute("projects", assignedProjects);
        return "UnAssignProject";
    }
}
