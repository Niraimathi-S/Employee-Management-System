/*
 * Copyrights (C) Ideas2IT
 */

package com.ideas2it.employeemanagement.controller;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

import com.ideas2it.employeemanagement.exception.EmployeeManagementException;
import com.ideas2it.employeemanagement.logger.EmployeeManagementLogger;
import com.ideas2it.employeemanagement.model.AddressDTO;
import com.ideas2it.employeemanagement.model.EmployeeVO;
import com.ideas2it.employeemanagement.model.ProjectDTO;
import com.ideas2it.employeemanagement.service.EmployeeService;
import com.ideas2it.employeemanagement.service.impl.EmployeeServiceImplementation;

/**
 * Controller class is responsible for the flow control logic and
 * acts as intermediate between view and service classes.
 * 
 * @author Niraimathi S
 * @version 1.0
 */
public class EmployeeController {
    private EmployeeService employeeService 
            = new EmployeeServiceImplementation();

    /**
     * Validates the given input from the user. 
     *
     * @param input- the input to be validated.
     * @param patternToValidate-the pattern used to validate th input.
     * @return validatedInput-validates input returned from EmployeeService.
     */
    public boolean validateInput(String input, String patternToValidate) {
        return (employeeService.validateInput(input, patternToValidate));
    }

    /**
     * Creates a new employee with the data given by the user. 
     *
     * @param employeeVO-The VO object with employee details and address
     * @param employeeVO-The VO object to store the created employee.
     */
    public EmployeeVO createEmployee(EmployeeVO employeeVO) {
        EmployeeVO returnedEmployeeVO = null;
        try {
            returnedEmployeeVO = employeeService.createEmployee(employeeVO);
        } catch (EmployeeManagementException exception) {
            System.out.print(exception.getMessage());
            EmployeeManagementLogger.logger.error(exception);
        }
        //return employeeService.createEmployee(employeeVO);
        return returnedEmployeeVO;
    }

    /**
     * Checks if employees is empty.
     *
     * @return boolean-true if Empty, else false
     */
    public boolean isRecordsEmpty() {
        boolean isEmpty = false;
        try {
            isEmpty = employeeService.isRecordsEmpty();
        } catch (EmployeeManagementException exception) {
            System.out.print(exception.getMessage());
            EmployeeManagementLogger.logger.error(exception);
        }
        return isEmpty;
    }

    /**
     * Checks if a mobileNumber already exists or not.
     *
     * @param mobileNumber-the number to be checked for duplicate.
     * @return boolean-true if duplicate, else false.
     */
    public boolean checkDuplicateMobileNumber(long mobileNumber) {
        boolean isDublicate = false;
        try {
            isDublicate 
                    = employeeService.checkDuplicateMobileNumber(mobileNumber);
        } catch (EmployeeManagementException exception) {
            System.out.print(exception.getMessage());
            EmployeeManagementLogger.logger.error(exception);
        }
        return isDublicate;
    }

    /**
     * Checks if a email already exists or not.
     *
     * @param email- email to be checked for duplicate.
     * @return boolean-true if duplicate, else false.
     */
    public boolean checkDuplicateEmail(String email) {
        boolean isDublicate = false;
        try {
            isDublicate 
                    = employeeService.checkDuplicateEmail(email);
        } catch (EmployeeManagementException exception) {
            System.out.print(exception.getMessage());
            EmployeeManagementLogger.logger.error(exception);
        }
        return isDublicate;
    }

    /**
     * Deletes all employees. 
     */
    public boolean deleteAllEmployee() {
        boolean isDeleted = false;
        try {
            isDeleted = employeeService.deleteAllEmployee();
        } catch (EmployeeManagementException exception) {
            System.out.print(exception.getMessage());
            EmployeeManagementLogger.logger.error(exception);
        }
        return isDeleted;
    }

    /**
     * Checks if a employeeId exists or not.
     *
     * @param employeeId-Employee Id to check if a record already exist.
     * @return boolean-true if given employeeId exist, else false.
     */
    public boolean isEmployeeExist(int employeeId) {
        boolean isExist = false;
        try {
            isExist = employeeService.isEmployeeExist(employeeId);
        } catch (EmployeeManagementException exception) {
            System.out.print(exception.getMessage());
            EmployeeManagementLogger.logger.error(exception);
        }
        return isExist;
    }
    
    /**
     * Deletes one employee.
     *
     * @param employeeVO-The VO object of the employee to be deleted.
     * @return boolean-true if given employee deleted, else false.
     */
    public boolean deleteOneEmployee(EmployeeVO employeeVO) {
        boolean isDeleted = false;
        try {
            isDeleted = employeeService.deleteOneEmployee(employeeVO);
        } catch (EmployeeManagementException exception) {
            System.out.print(exception.getMessage());
            EmployeeManagementLogger.logger.error(exception);
        }
        return isDeleted;
    }

    /**
     * Updates all fields of one employee. 
     *
     * @param employeeVO-employeeVO object containing updated
     *                   values get from user
     */
    public boolean updateAllFields(EmployeeVO employeeVO) {
        boolean isUpdated = false;
        try {
            isUpdated = employeeService.updateAllFields(employeeVO);
        } catch (EmployeeManagementException exception) {
            System.out.print(exception.getMessage());
            EmployeeManagementLogger.logger.error(exception);
        }
        return isUpdated;
    }

    /**
     * Gets an employee details by Employee id.
     *
     * @param employeeId-The employeeId to get employee details.
     * @return EmployeeVO-object containing details of a single employee.
     */
    public EmployeeVO getEmployeeById(int employeeId) {
        EmployeeVO returnedEmployeeVO = null;
        try {
            returnedEmployeeVO = employeeService.getEmployeeById(employeeId);
        } catch (EmployeeManagementException exception) {
            System.out.print(exception.getMessage());
            EmployeeManagementLogger.logger.error(exception);
        }
        return returnedEmployeeVO;
    }

    /**
     * Views one employee to the user.
     *
     * @param employeeid-employeeid to view.
     * @return employeeVO-object which contains details of the single employee.
     */
    public EmployeeVO viewOneEmployee(int employeeId) {
        EmployeeVO returnedEmployeeVO = null;
        try {
            returnedEmployeeVO = employeeService.getEmployeeById(employeeId);
        } catch (EmployeeManagementException exception) {
            System.out.print(exception.getMessage());
            EmployeeManagementLogger.logger.error(exception);
        }
        return returnedEmployeeVO;
    }

    /**
     * Views all employees to the user.
     *
     * @return List of all employees
     */
    public List<EmployeeVO> viewAllEmployee() {
        List<EmployeeVO> employees = new ArrayList<EmployeeVO>();
        try {
            employees = employeeService.viewAllEmployee();
        } catch (EmployeeManagementException exception) {
            System.out.print(exception.getMessage());
            EmployeeManagementLogger.logger.error(exception);
        }
        return employees;
    }

    public List<ProjectDTO> getProjectDTOs(int[] projectIds) {
        List<ProjectDTO> projects = new ArrayList<ProjectDTO>();
        try {
            projects = employeeService.getProjectDTOs(projectIds);
        } catch (EmployeeManagementException exception) {
            System.out.print(exception.getMessage());
            EmployeeManagementLogger.logger.error(exception);
        }
        return projects;
    }
}

