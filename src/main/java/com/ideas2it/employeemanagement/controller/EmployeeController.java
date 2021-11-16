/*
 * Copyrights (C) Ideas2IT
 */

package com.ideas2it.employeemanagement.controller;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

import com.ideas2it.employeemanagement.model.AddressDTO;
import com.ideas2it.employeemanagement.model.EmployeeVO;
import com.ideas2it.employeemanagement.service.EmployeeService;
import com.ideas2it.employeemanagement.service.impl.EmployeeServiceImplementation;

/**
 * Controller class is responsible for the flow control logic and
 * acts as intermediate between view and service classes.
 * 
 * @author Niraimathi S
 * @version 1.0 12-11-2021
 */
public class EmployeeController {
    private EmployeeService employeeService = new EmployeeServiceImplementation();

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
     * @param AddressDTO-AddressDTO with employee details and address
     * @param employeeVO-The VO object to store the created employee.
     */
    public EmployeeVO createEmployee(AddressDTO addressDTO) {
        return employeeService.createEmployee(addressDTO);
    }

    /**
     * Checks if employees is empty.
     *
     * @return boolean-true if Empty, else false
     */
    public boolean isRecordsEmpty() {
        return employeeService.isRecordsEmpty();
    }

    /**
     * Checks if a mobileNumber already exists or not.
     *
     * @param mobileNumber-the number to be checked for duplicate.
     * @return boolean-true if duplicate, else false.
     */
    public boolean checkDuplicateMobileNumber(long mobileNumber) {
        return employeeService.checkDuplicateMobileNumber(mobileNumber);
    }

    /**
     * Checks if a email already exists or not.
     *
     * @param email- email to be checked for duplicate.
     * @return boolean-true if duplicate, else false.
     */
    public boolean checkDuplicateEmail(String email) {
        return employeeService.checkDuplicateEmail(email);
    }

    /**
     * Deletes all employees. 
     */
    public boolean deleteAllEmployee() {
        return employeeService.deleteAllEmployee();
    }

    /**
     * Checks if a employeeId exists or not.
     *
     * @param employeeId-Employee Id to check if a record already exist.
     * @return boolean-true if given employeeId exist, else false.
     */
    public boolean isEmployeeExist(int employeeId) {
        return (employeeService.isEmployeeExist(employeeId));
    }
    
    /**
     * Deletes one employee.
     *
     * @param employeeid-The employeeId of the employee to be deleted.
     * @return boolean-true if given employee deleted, else false.
     */
    public boolean deleteOneEmployee(int employeeId) {
        return employeeService.deleteOneEmployee(employeeId);
    }

    /**
     * Deletes one address of an employee.
     *
     * @param addressId-The addressId of the address to be deleted.
     * @return boolean-true if given address exist, else false.
     */
    public boolean deleteAddress(int addressId) {
        return employeeService.deleteAddress(addressId);
    }

    /**
     * Gets all addresses of an employee.
     *
     * @param employeeId-The employeeId to get all address.
     * @return List<AddressDTO>-List of all addresses of a single employee.
     */
    public List<AddressDTO> getAddressById(int employeeId) {
        return employeeService.getAddressById(employeeId);
    }

    /**
     * Updates all fields of one employee. 
     *
     * @param employeeid-employeeid to update all fields
     * @param employeeVO-employeeVO object containing updated
     *                   values get from user
     */
    public boolean updateAllFields(AddressDTO addressDTO) {
        return employeeService.updateAllFields(addressDTO);
    }

    /**
     * Gets an employee details by Employee id.
     *
     * @param employeeId-The employeeId to get employee details.
     * @return EmployeeVO-object containing details of a single employee.
     */
    public EmployeeVO getEmployeeById(int employeeId) {
        return employeeService.getEmployeeById(employeeId);
    }

    /**
     * Views one employee to the user.
     *
     * @param employeeid-employeeid to view.
     * @return employeeVO-object which contains details of the single employee.
     */
    public EmployeeVO viewOneEmployee(Integer employeeId) {
        return employeeService.getEmployeeById(employeeId);
    }

    /**
     * Views all employees to the user.
     *
     * @return List of all employees
     */
    public List<EmployeeVO> viewAllEmployee() {
        return employeeService.viewAllEmployee();
    }
}

