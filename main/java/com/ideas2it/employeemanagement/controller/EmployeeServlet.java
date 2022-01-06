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
import com.ideas2it.employeemanagement.model.AddressDTO;
import com.ideas2it.employeemanagement.model.EmployeeVO;
import com.ideas2it.employeemanagement.model.ProjectDTO;
import com.ideas2it.employeemanagement.service.EmployeeService;
import com.ideas2it.employeemanagement.service.impl.EmployeeServiceImplementation;

@WebServlet(urlPatterns = { "/EmployeeServlet" })
public class EmployeeServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String types = request.getParameter("type");  

		switch (types) {
		case "AddAddress" :
			addAddress(request,response);
			break;
		case "DeleteAddress" :
			deleteAddress(request,response);
			break;
		case "update" :
			update(request,response);
			break;
		case "updateAllFields" :
			updateAllFields(request,response);
			break;
		case "assign&unassign":
			assignAndUnassign(request, response);
			break;
		case "assign":
			assignProject(request, response);
			break;
		case "unassign":
			unAssignProject(request, response);
			break;
		}
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String types = request.getParameter("type");  

		switch (types) {
		case "create" :
			createEmployee(request,response);
			break;
		case "viewSingle" :
			viewSingleEmployee(request,response);
			break;
		case "viewAll" :
			viewAllEmployees(request,response);
			break;
		case "delete" :
			deleteEmployee(request,response);
			break;
		case "AddAddress" :
			addAddress(request,response);
			break;
		case "update" :
			update(request,response);
			break;
		case "updateAllFields" :
			System.out.println("swithcase in update all"+types);
			updateAllFields(request,response);
			break;
		}		
	}

	public void createEmployee(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		AddressDTO addressDTO = null;
		EmployeeVO employeeVO = new EmployeeVO(request.getParameter("name"),request.getParameter("email"),Long.parseLong(request.getParameter("mobile_number")),
				LocalDate.parse(request.getParameter("date_of_birth")), Float.parseFloat(request.getParameter("salary")));
        addressDTO = new AddressDTO(request.getParameter("door_number"),request.getParameter("street"),
        		request.getParameter("city"),request.getParameter("state"),request.getParameter("country"),Integer.parseInt(request.getParameter("pincode")));  
        Set<AddressDTO> addressList = new HashSet<AddressDTO>();
        addressList.add(addressDTO);
        employeeVO.setaddressesDTO(addressList);
        EmployeeService employeeService = new EmployeeServiceImplementation();
        EmployeeVO returnedEmployee = null;
        try {
        	returnedEmployee = employeeService.createEmployee(employeeVO);
        	request.setAttribute("returnedEmployee", returnedEmployee);
        	request.getRequestDispatcher("createEmployee.jsp").forward(request, response);
        } catch (EmployeeManagementException exception) {
            EmployeeManagementLogger.logger.error(exception);
        }
	}
	
	public void viewSingleEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EmployeeVO employeeVO = null;
		int employeeId = 0;
        EmployeeService employeeService = new EmployeeServiceImplementation();
        try {
        	employeeId = Integer.parseInt(request.getParameter("employeeId"));
        	employeeVO = employeeService.getEmployeeById(employeeId);
        	request.setAttribute("returnedEmployee", employeeVO);
        	request.getRequestDispatcher("SingleEmployeeDisplay.jsp").forward(request, response);
        } catch (EmployeeManagementException exception) {
            EmployeeManagementLogger.logger.error(exception);
        }
	}
	
	public void viewAllEmployees(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<EmployeeVO> employees = null;
        EmployeeService employeeService = new EmployeeServiceImplementation();
        try {
            employees = employeeService.viewAllEmployee();
        	request.setAttribute("Employees", employees);
        	request.getRequestDispatcher("AllEmployeeDisplay.jsp").forward(request, response);
        } catch (EmployeeManagementException exception) {
            EmployeeManagementLogger.logger.error(exception);
        }
	}

	
	public void deleteEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int idToDelete = 0;
        boolean isRecordDeleted = false;
		EmployeeVO employeeVO = null;
        EmployeeService employeeService = new EmployeeServiceImplementation();
        try {
        	idToDelete = Integer.parseInt(request.getParameter("employeeId"));
        	employeeVO = employeeService.getEmployeeById(idToDelete);
        	if (null != employeeVO) {
        		isRecordDeleted = employeeService.deleteOneEmployee(employeeVO);
        	}
        	request.setAttribute("isRecordDeleted", isRecordDeleted);
        	request.setAttribute("returnedEmployee", employeeVO);
        	request.getRequestDispatcher("Deleted.jsp").forward(request, response);
        } catch (EmployeeManagementException exception) {
            EmployeeManagementLogger.logger.error(exception);
        }
	}
	
    public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String types = request.getParameter("action");  
        EmployeeService employeeService = new EmployeeServiceImplementation();
        try {
        EmployeeVO employeeVO = employeeService.getEmployeeById(Integer.parseInt(request.getParameter("employeeId")));
        request.setAttribute("returnedEmployee", employeeVO);
    	switch (types) {
		case "updateAllFields":
			//request.getRequestDispatcher("UpdateAllFields.jsp").forward(request, response);
			//updateAllFields(request,response);
			break;
		case "deleteAddress":
	    	request.getRequestDispatcher("deleteAddress.jsp").forward(request, response);
			//DeleteAddress(request,response);
			break;
		case "AddAddress":
	    	request.getRequestDispatcher("AddAddress.jsp").forward(request, response);
			//addAddress(request,response);
			break;
			
		}
        } catch (EmployeeManagementException exception) {
            EmployeeManagementLogger.logger.error(exception);
        }
    }
    
    public void updateAllFields(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean isUpdated = false;
		EmployeeVO employeeVO = null;
        int employeeId = 0;
        EmployeeService employeeService = new EmployeeServiceImplementation();
        String message =  "Updated successfully";
        String error = "Update not successfull";
        try {
        	employeeId = Integer.parseInt(request.getParameter("employeeId"));
        	employeeVO = employeeService.getEmployeeById(employeeId);
        	request.setAttribute("returnedEmployee", employeeVO);
    		System.out.println("before null check employeeVO"+employeeVO);
        	//request.getRequestDispatcher("UpdateAllFields.jsp").forward(request, response);


        	//request.getRequestDispatcher("UpdateAllFields.jsp").forward(request, response);
        	if (null != employeeVO) {
                employeeVO.setEmployeeId(employeeId);
        		employeeVO.setName(request.getParameter("name"));
        		employeeVO.setDateOfBirth(LocalDate.parse(request.getParameter("date_of_birth")));
        		employeeVO.setEmail(request.getParameter("email"));
        		employeeVO.setSalary(Float.parseFloat(request.getParameter("salary")));
        		employeeVO.setMobileNumber(Long.parseLong(request.getParameter("mobile_number")));
        		System.out.println("employeeVO"+employeeVO);
                isUpdated = employeeService.updateAllFields(employeeVO);
        		System.out.println("isUpdated"+isUpdated);

        	}
        	request.setAttribute("message", message);
        	request.setAttribute("error", error);

        	request.setAttribute("isUpdated", isUpdated);
        	request.getRequestDispatcher("Updated.jsp").forward(request, response);
        } catch (EmployeeManagementException exception) {
            EmployeeManagementLogger.logger.error(exception);
        }
	}
	
    /**
     * Add a new address to the Employee.
     *
     * @param employeeId-employeeId to add new address to the employee.
     */
    public void addAddress(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean isUpdated = false;
        EmployeeVO employeeVO = null;
        int employeeId = 0;
    	EmployeeService employeeService = new EmployeeServiceImplementation();
    	try {
        	employeeId = Integer.parseInt(request.getParameter("employeeId"));
            employeeVO = employeeService.getEmployeeById(employeeId);
        	if (null != employeeVO) {

                AddressDTO addressDTO = new AddressDTO(request.getParameter("door_number"),request.getParameter("street"),
        		    request.getParameter("city"),request.getParameter("state"),request.getParameter("country"),Integer.parseInt(request.getParameter("pincode")));
                Set<AddressDTO> addresses = employeeVO.getaddressesDTO();
                addresses.add(addressDTO);
                employeeVO.setaddressesDTO(addresses);
                isUpdated = employeeService.updateAllFields(employeeVO);
        	}
        	request.setAttribute("isUpdated", isUpdated);
	    	request.getRequestDispatcher("AddAddress.jsp").forward(request, response);
    	} catch (EmployeeManagementException exception) {
            EmployeeManagementLogger.logger.error(exception);
        }
   }
    
    public void deleteAddress(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean isUpdated = false;
        EmployeeVO employeeVO = null;
        int employeeId = 0;
        int addressId = 1;
        AddressDTO addressToDelete = null;
        String message = "Address deleted Successfully";
        String error = "Address not successful";
        
    	EmployeeService employeeService = new EmployeeServiceImplementation();
    	try {
        	employeeId = Integer.parseInt(request.getParameter("employeeId"));
            employeeVO = employeeService.getEmployeeById(employeeId);
        	if (null != employeeVO) {
        		addressId = Integer.parseInt(request.getParameter("addressId"));

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
                isUpdated = employeeService.updateAllFields(employeeVO);
        	}
            request.setAttribute("isUpdated", isUpdated);
        	request.setAttribute("error", error);
            request.setAttribute("employeeVO", employeeVO);
            request.setAttribute("message", message);
	    	request.getRequestDispatcher("Updated.jsp").forward(request, response);
    	} catch (EmployeeManagementException exception) {
            EmployeeManagementLogger.logger.error(exception);
        }
   }
    
    public void assignAndUnassign(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EmployeeVO employeeVO = null;
		int employeeId = 0;
		String type = "";
        Set<ProjectDTO> assignedProjects = null;
        List<ProjectDTO> allProjects = null;
        EmployeeService employeeService = new EmployeeServiceImplementation();
        try {
        	employeeId = Integer.parseInt(request.getParameter("employeeId"));
        	type = request.getParameter("action");
        	System.out.println("type in assign if else "+type+"\n"+type=="assign"+type.equals("assign"));
        	employeeVO = employeeService.getEmployeeById(employeeId);
        	if( null != employeeVO) {
        		assignedProjects = employeeVO.getProjectsDTO();
        		allProjects = employeeService.getAllProjectDTOs();
                if (assignedProjects.isEmpty() || (null == assignedProjects)) {
                    assignedProjects = new HashSet<ProjectDTO>();
                }
                	//allProjects.removeAll(assignedProjects);
        	}
        	//request.setAttribute("projects", allProjects);
        	if(type.equals("assign")) {
            	allProjects.removeAll(assignedProjects);
            	request.setAttribute("projects", allProjects);
        	    request.getRequestDispatcher("AssignProject.jsp").forward(request, response);
        	} else {
            	request.setAttribute("projects", assignedProjects);
            	request.getRequestDispatcher("UnAssignProject.jsp").forward(request, response);
        	}
        } catch (EmployeeManagementException exception) {
            EmployeeManagementLogger.logger.error(exception);
        }
    }
    
    public void assignProject(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	EmployeeService employeeService = new EmployeeServiceImplementation();
		int employeeId = 0;
    	EmployeeVO employeeVO = null;
        Set<ProjectDTO> assignedProjects = null;
        List<ProjectDTO> projectsToAssign;
        String message = "Project Assigned Successfully";
        String error = "Project Assign not successful";
        boolean isUpdated = false;
        int projectIds[] = new int[1];
        try {
        	employeeId = Integer.parseInt(request.getParameter("employeeId"));
        	employeeVO = employeeService.getEmployeeById(employeeId);
        	if( null != employeeVO) {
        		assignedProjects = employeeVO.getProjectsDTO();
            	projectIds[0] = Integer.parseInt(request.getParameter("projectId"));
                projectsToAssign = employeeService.getProjectDTOs(projectIds);
                if (assignedProjects.isEmpty() || (null == assignedProjects)) {
                    assignedProjects = new HashSet<ProjectDTO>();
                }
                projectsToAssign.removeAll(assignedProjects);
                for (ProjectDTO project:projectsToAssign){
                    assignedProjects.add(project);
                }
                employeeVO.setProjectsDTO(assignedProjects);
                isUpdated = employeeService.updateAllFields(employeeVO);
                request.setAttribute("isUpdated", isUpdated);
            	request.setAttribute("error", error);

                request.setAttribute("employeeVO", employeeVO);
                request.setAttribute("message", message);

    	    	request.getRequestDispatcher("Updated.jsp").forward(request, response);
        	}
        } catch (EmployeeManagementException exception) {
            EmployeeManagementLogger.logger.error(exception);
        }
    }

    public void unAssignProject(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	EmployeeService employeeService = new EmployeeServiceImplementation();
		int employeeId = 0;
    	EmployeeVO employeeVO = null;
        Set<ProjectDTO> assignedProjects = null;
        List<ProjectDTO> projectsToUnAssign;
        String message = "Project UnAssigned Successfully";
        String error = "Project UnAssign not successful";
        boolean isUpdated = false;
        int projectIds[] = new int[1];
        try {
        	employeeId = Integer.parseInt(request.getParameter("employeeId"));
        	employeeVO = employeeService.getEmployeeById(employeeId);
        	if( null != employeeVO) {
        		assignedProjects = employeeVO.getProjectsDTO();
            	projectIds[0] = Integer.parseInt(request.getParameter("projectId"));
            	projectsToUnAssign = employeeService.getProjectDTOs(projectIds);
                if (assignedProjects.isEmpty() || (null == assignedProjects)) {
                    assignedProjects = new HashSet<ProjectDTO>();
                }
                assignedProjects.removeAll(projectsToUnAssign);
                
                employeeVO.setProjectsDTO(assignedProjects);
                isUpdated = employeeService.updateAllFields(employeeVO);
                request.setAttribute("isUpdated", isUpdated);
            	request.setAttribute("error", error);

                request.setAttribute("employeeVO", employeeVO);
                request.setAttribute("message", message);

    	    	request.getRequestDispatcher("Updated.jsp").forward(request, response);
        	}
        } catch (EmployeeManagementException exception) {
            EmployeeManagementLogger.logger.error(exception);
        }	
    }
    
}
