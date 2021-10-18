/*
 * EmployeeController.java
 * 
 * Copyrights (C) Ideas2IT
 */

package com.ideas2it.employeemanagement.controller;

import com.ideas2it.employeemanagement.model.EmployeeVO;
import com.ideas2it.employeemanagement.service.EmployeeService;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

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
     * @return validatedInput-validates input returned from EmployeeService.
     */
    public boolean validateInput(String input, String patternToValidate) {
        return (employeeService.validateInput(input, patternToValidate));
    }

    /**
     * This method calls createEmployee() from EMployeeService.
     *
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
     * @return boolean-true if duplicate, else false.
     */
    public boolean checkDuplicateContactNumber(long contactNumber) {
        return employeeService.checkDuplicateContactNumber(contactNumber);
    }

    /**
     * This method calls checkDuplicateEmail() from EmployeeService.
     *
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
     * @return boolean-true if given employeeId exist, else false.
     */
    public boolean checkContainskey(int employeeId) {
        return (employeeService.checkContainskey(employeeId));
    }
    
    /**
     * This method calls deleteAllEmployee() from EmployeeService which deletes 
     * all employees.
     *
     */
    public void deleteOneEmployee(int employeeId) {
        employeeService.deleteOneEmployee(employeeId);
    }

    /**
     * This method calls updateAllFields() from EmployeeService which updates 
     * all employees.
     *
     */
    public void updateAllFields(int employeeId, EmployeeVO employeeVO) {
        employeeService.updateAllFields(employeeId, employeeVO);
    }

    /**
     * This method calls updateName() from EmployeeService which updates 
     * employee name.
     *
     */
    public void updateName(int employeeId, String name) {
        employeeService.updateName(employeeId, name);
    }

    /**
     * This method calls updateEmail() from EmployeeService which updates 
     * employee email.
     *
     */
    public void updateEmail(int employeeId, String email) {
        employeeService.updateEmail(employeeId, email);
    }

    /**
     * This method calls updateContactNumber() from EmployeeService which  
     * updates employee contact number.
     *
     */
    public void updateContactNumber(int employeeId, long contactNumber) {
        employeeService.updateContactNumber(employeeId, contactNumber);
    }

    /**
     * This method calls updateDateOfBirth() from EmployeeService which updates 
     * employee date of birth.
     *
     */
    public void updateDateOfBirth(int employeeId, LocalDate dateOfBirth) {
        employeeService.updateDateOfBirth(employeeId, dateOfBirth);
    }

    /**
     * This method calls updateSalary() from EmployeeService which updates 
     * employees salary.
     *
     */
    public void updateSalary(int employeeId, float salary) {
        employeeService.updateSalary(employeeId, salary);
    }

    /**
     * This method calls viewOneEmployee() from EmployeeService.
     *
     * @return employeeVO object
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

