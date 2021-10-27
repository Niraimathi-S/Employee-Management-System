/*
 * Copyrights (C) Ideas2IT
 */

package com.ideas2it.employeemanagement.controller;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

import com.ideas2it.employeemanagement.model.EmployeeVO;
import com.ideas2it.employeemanagement.service.EmployeeService;

/**
 * Controller class is responsible for the flow control logic and
 * acts as intermediate between view and service classes.
 * 
 * @author Niraimathi S
 * @version 1.0 12-11-2021
 */
public class EmployeeController {
    private EmployeeService employeeService = new EmployeeService();

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
     * @param employeeid-employeeid
     * @param employeeVO-The VO object to store the created employee.
     */
    public boolean createEmployee(int employeeId, EmployeeVO employeeVO) {
        return employeeService.createEmployee(employeeId, employeeVO);
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
    public void deleteAllEmployee() {
        employeeService.deleteAllEmployee();
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
     */
    public void deleteOneEmployee(int employeeId) {
        employeeService.deleteOneEmployee(employeeId);
    }

    /**
     * Updates all fields of one employee. 
     *
     * @param employeeid-employeeid to update all fields
     * @param employeeVO-employeeVO object containing updated
     *                   values get from user
     */
    public boolean updateAllFields(int employeeId, EmployeeVO employeeVO) {
        return employeeService.updateAllFields(employeeId, employeeVO);
    }

    /**
     * Updates name of a single employee. 
     *
     * @param employeeid-employeeid to update.
     * @param name-the updated name of the employee.
     */
    public void updateName(int employeeId, String name) {
        employeeService.updateName(employeeId, name);
    }

    /**
     * Updates email of a single employee. 
     *
     * @param employeeid-employeeid to update.
     * @param email-the updated email of the employee.
     */
    public void updateEmail(int employeeId, String email) {
        employeeService.updateEmail(employeeId, email);
    }

    /**
     * Updates mobile number of a single employee. 
     *
     * @param employeeid-employeeid to update.
     * @param mobileNumber-the updated mobile number of the employee.
     */
    public void updateMobileNumber(int employeeId, long mobileNumber) {
        employeeService.updateMobileNumber(employeeId, mobileNumber);
    }

    /**
     * Updates date of birth of a single employee. 
     *
     * @param employeeid-employeeid to update.
     * @param dateOfBirth-the updated date of birth of the employee.
     */
    public void updateDateOfBirth(int employeeId, LocalDate dateOfBirth) {
        employeeService.updateDateOfBirth(employeeId, dateOfBirth);
    }

    /**
     * Updates salary of a single employee. 
     *
     * @param employeeid-employeeid to update.
     * @param salary-the updated salary of the employee.
     */
    public void updateSalary(int employeeId, float salary) {
        employeeService.updateSalary(employeeId, salary);
    }

    /**
     * Views one employee to the user.
     *
     * @param employeeid-employeeid to view.
     * @return employeeVO-object which contains details of the single employee.
     */
    public EmployeeVO viewOneEmployee(Integer employeeId) {
        return employeeService.viewOneEmployee(employeeId);
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

