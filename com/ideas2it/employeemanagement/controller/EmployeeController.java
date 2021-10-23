/*
 * EmployeeController.java
 * 
 * Copyrights (C) Ideas2IT
 */

package com.ideas2it.employeemanagement.controller;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

import com.ideas2it.employeemanagement.model.EmployeeVO;
import com.ideas2it.employeemanagement.service.EmployeeService;

/**
 * Controller class acts as intermediate between view and service classes.
 * 
 * @author Niraimathi S
 * @ version 1.0 12-11-2021
 */
public class EmployeeController {
    private EmployeeService employeeService = new EmployeeService();

    /**
     * This method calls validateInput() from EmployeeService.
     *
     * @param input- the input to be validated.
     * @param patternToValidate-the pattern used to validate th input.
     * @return validatedInput-validates input returned from EmployeeService.
     */
    public boolean validateInput(String input, String patternToValidate) {
        return (employeeService.validateInput(input, patternToValidate));
    }

    /**
     * This method calls stringToInteger() from EMployeeService.
     *
     * @param input-input to be converted to integer type.
     * @return input in integer data type.
     */
    public int stringToInteger(String input) {
        return employeeService.stringToInteger(input);
    }

    /**
     * This method calls stringToFloat() from EMployeeService.
     *
     * @param input-input to be converted to float type.
     * @return input in float data type.
     */
    public float stringToFloat(String input) {
        return employeeService.stringToFloat(input);
    }

    /**
     * This method calls stringToLong() from EMployeeService.
     *
     * @param input-input to be converted to long type.
     * @return input in long data type.
     */
    public long stringToLong(String input) {
        return employeeService.stringToLong(input);
    }

    /**
     * This method calls createEmployee() from EMployeeService.
     *
     * @param employeeid-employeeid
     * @param employeeVO-The VO object to store the created employee.
     */
    public void createEmployee(int employeeId, EmployeeVO employeeVO) {
        employeeService.createEmployee(employeeId, employeeVO);
    }

    /**
     * This method calls checkEmpty() from EmployeeService.
     *
     * @return boolean-true if Empty, else false
     */
    public boolean checkEmpty() {
        return employeeService.checkEmpty();
    }

    /**
     * This method calls checkDuplicateContactNumber() from EmployeeService.
     *
     * @param contactNumber-the number to be checked for duplicate.
     * @return boolean-true if duplicate, else false.
     */
    public boolean checkDuplicateContactNumber(long contactNumber) {
        return employeeService.checkDuplicateContactNumber(contactNumber);
    }

    /**
     * This method calls checkDuplicateEmail() from EmployeeService.
     *
     * @param email- email to be checked for duplicate.
     * @return boolean-true if duplicate, else false.
     */
    public boolean checkDuplicateEmail(String email) {
        return employeeService.checkDuplicateEmail(email);
    }

    /**
     * This method calls deleteAllEmployee() from EmployeeService which deletes 
     * all employees.
     *
     */
    public void deleteAllEmployee() {
        employeeService.deleteAllEmployee();
    }

    /**
     * This method calls checkContainskey() from EmployeeService which checks.
     *
     * @param employeeId-Employee Id to check if a record already exist.
     * @return boolean-true if given employeeId exist, else false.
     */
    public boolean checkContainskey(int employeeId) {
        return (employeeService.checkContainskey(employeeId));
    }
    
    /**
     * This method calls deleteOneEmployee() from EmployeeService which deletes 
     * one employee from the records.
     *
     * @param employeeid-The employeeId of the employee to be deleted.
     */
    public void deleteOneEmployee(int employeeId) {
        employeeService.deleteOneEmployee(employeeId);
    }

    /**
     * This method calls updateAllFields() from EmployeeService which updates 
     * all employees.
     *
     * @param employeeid-employeeid to update all fields
     * @param employeeVO-employeeVO object containing updated
     *                   values get from user
     */
    public void updateAllFields(int employeeId, EmployeeVO employeeVO) {
        employeeService.updateAllFields(employeeId, employeeVO);
    }

    /**
     * This method calls updateName() from EmployeeService which updates 
     * employee name.
     *
     * @param employeeid-employeeid to update.
     * @param name-the updated name of the employee.
     */
    public void updateName(int employeeId, String name) {
        employeeService.updateName(employeeId, name);
    }

    /**
     * This method calls updateEmail() from EmployeeService which updates 
     * employee email.
     *
     * @param employeeid-employeeid to update.
     * @param email-the updated email of the employee.
     */
    public void updateEmail(int employeeId, String email) {
        employeeService.updateEmail(employeeId, email);
    }

    /**
     * This method calls updateContactNumber() from EmployeeService which  
     * updates employee contact number.
     *
     * @param employeeid-employeeid to update.
     * @param contactNumber-the updated mobile number of the employee.
     */
    public void updateContactNumber(int employeeId, long contactNumber) {
        employeeService.updateContactNumber(employeeId, contactNumber);
    }

    /**
     * This method calls updateDateOfBirth() from EmployeeService which updates. 
     * employee date of birth.
     *
     * @param employeeid-employeeid to update.
     * @param dateOfBirth-the updated date of birth of the employee.
     */
    public void updateDateOfBirth(int employeeId, LocalDate dateOfBirth) {
        employeeService.updateDateOfBirth(employeeId, dateOfBirth);
    }

    /**
     * This method calls updateSalary() from EmployeeService which updates 
     * employees salary.
     *
     * @param employeeid-employeeid to update.
     * @param salary-the updated salary of the employee.
     */
    public void updateSalary(int employeeId, float salary) {
        employeeService.updateSalary(employeeId, salary);
    }

    /**
     * This method calls viewOneEmployee() from EmployeeService.
     *
     * @param employeeid-employeeid to view.
     * @return employeeVO-object which contains details of the single employee.
     */
    public EmployeeVO viewOneEmployee(Integer employeeId) {
        return employeeService.viewOneEmployee(employeeId);
    }

    /**
     * This method calls viewAllEmployee() from EmployeeService.
     *
     * @return List of all employees
     */
    public List<EmployeeVO> viewAllEmployee() {
        return employeeService.viewAllEmployee();
    }
}

