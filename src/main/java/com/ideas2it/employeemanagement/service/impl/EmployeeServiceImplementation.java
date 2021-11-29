/*
 * Copyrights (C) Ideas2IT
 */

package com.ideas2it.employeemanagement.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.ideas2it.employeemanagement.dao.daoimpl.EmployeeDAOImplementation;
import com.ideas2it.employeemanagement.model.Address;
import com.ideas2it.employeemanagement.model.AddressDTO;
import com.ideas2it.employeemanagement.model.Employee;
import com.ideas2it.employeemanagement.model.EmployeeVO;
import com.ideas2it.employeemanagement.model.ProjectDTO;
import com.ideas2it.employeemanagement.model.Project;
import com.ideas2it.employeemanagement.service.EmployeeService;
import com.ideas2it.employeemanagement.service.ProjectService;
import com.ideas2it.employeemanagement.service.impl.ProjectServiceImplementation;
import com.ideas2it.employeemanagement.util.Mapper;

/**
 * Performs logical operations and data manupulation.
 * Interacts with Employee POJO,Employee DAO and EmployeeVO and
 * validates user inputs.
 * 
 * @author Niraimathi S
 * @version 1.0
 */
public class EmployeeServiceImplementation implements EmployeeService{
    private EmployeeDAOImplementation dao = new EmployeeDAOImplementation();
    private ProjectService projectService = null;

    /**
     * Validates the given input from the user. 
     *
     * @param input- the input to be validated.
     * @param patternToValidate-the pattern used to validate th input.
     * @return input-validated input
     */
    public boolean validateInput(String input, String patternToValidate) { 
        return input.matches(patternToValidate);
    }

    /**
     * Creates a new employee. 
     *
     * @param employeeVO-The VO object to store the created employee.
     * @return boolean-true if employee created, else false.
     */
    public EmployeeVO createEmployee(EmployeeVO employeeVO) { 
        Employee employee = Mapper.EmployeeDTOToEmployee(employeeVO);
        employeeVO 
                = Mapper.EmployeeToEmployeeDTO(dao.createEmployee(employee));
        return employeeVO;
    }

    /**
     * Checks if a employeeId exists or not.
     *
     * @param employeeid-employeeid to check if a record already exist.
     * @return boolean-true if given employeeId exist, else false.
     */
    public boolean isEmployeeExist(int employeeId) {
        return(null != dao.getEmployeeById(employeeId));
    }

    /**
     * Checks if employees is empty.
     *
     * @return boolean-true if empty, else false.
     */
    public boolean isRecordsEmpty() {
        List<Employee> employees = dao.getAllEmployees();
        return employees.isEmpty();
    }

    /**
     * Checks if a mobileNumber already exists or not.
     *
     * @param mobileNumber-the number to be checked for duplicate.
     * @return boolean-true if given mobileNumber exist, else false.
     */
    public boolean checkDuplicateMobileNumber(long mobileNumber) {
        return (null != dao.getEmployeeByMobileNumber(mobileNumber));
    }

    /**
     * Checks if a email already exists or not.
     *
     * @param email- email to be checked for duplicate.
     * @return boolean-true if given email exist, else false.
     */
    public boolean checkDuplicateEmail(String email) {
        return (null != dao.getEmployeeByEmail(email));
    }

    /**
     * Deletes all employees. 
     *
     */
    public boolean deleteAllEmployee() {
        return dao.deleteAllEmployee();
    }

    /**
     * Deletes one employee. 
     *
     * @param employeeVO-The VO object to be deleted.
     */
    public boolean deleteOneEmployee(EmployeeVO employeeVO) {
        return dao.deleteOneEmployee(Mapper.EmployeeDTOToEmployee(employeeVO));
    }

    /**
     * Updates all fields of one employee. 
     *
     * @param employeeVO-The VO object with updates values employee.
     * @return boolean-true if employee updated, else false.
     */
    public boolean updateAllFields(EmployeeVO employeeVO) { 
        Employee employee = Mapper.EmployeeDTOToEmployee(employeeVO);
        return (null != Mapper.EmployeeToEmployeeDTO(dao.updateEmployee(employee)));
    }

    /**
     * Gets one employee by Id to the user.
     *
     * @param employeeid-employeeid to view.
     * @return employeeVO-employee get by Id.
     */
    public EmployeeVO getEmployeeById(int employeeId) {
        Employee employee = dao.getEmployeeById(employeeId);
        EmployeeVO employeeVO = null;
        if (null != employee) {
            employeeVO = Mapper.EmployeeToEmployeeDTO(employee);
        }

        return employeeVO;
    }

    /**
     * Displays all employees to the user.
     *
     * @return List<employeeVO>- view object list containing all employees.
     */
    public List<EmployeeVO> viewAllEmployee() {
        List<Employee> employees = dao.getAllEmployees();
        List<EmployeeVO> employeeDetails = new ArrayList<>();
        for (Employee employee : employees) {
            employeeDetails.add(Mapper.EmployeeToEmployeeDTO(employee));
        }
        return employeeDetails;
    }

    /**
     * Gets projects by Id to the user.
     *
     * @param projectIds[]-list of project id.
     * @return List<ProjectDTO>- view object list containing all projects.
     */
    public List<ProjectDTO> getProjectDTOs(int[] projectIds) {
        /*List<ProjectDTO> projects = new ArrayList<ProjectDTO>();
        ProjectDTO projectDTO;
        for (int i : projectIds) {
            if ( null != projectService.getProjectById(i)) {
                projectDTO = projectService.getProjectById(i);
                projects.add(projectDTO);
            }
        }
        return projects;*/
        if (null == projectService) {
            projectService = new ProjectServiceImplementation();
        }
        return projectService.getProjectDTOs(projectIds);        
    }

    public List<EmployeeVO> getEmployeeDTOs(int[] employeeIds) {
        List<EmployeeVO> employees = new ArrayList<EmployeeVO>();
        EmployeeVO employeeVO;
        for (int i : employeeIds) {
            //employeeVO = employeeService.getEmployeeById(i);
            if ( null != getEmployeeById(i)) {
                employeeVO = getEmployeeById(i);
                employees.add(employeeVO);
            }
        } 
        return employees;
    }
}
