/*
 * Copyrights (C) Ideas2IT
 */

package com.ideas2it.employeemanagement.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.ideas2it.employeemanagement.model.Employee;
import com.ideas2it.employeemanagement.model.EmployeeVO;
import com.ideas2it.employeemanagement.util.Mapper;

/**
 * Performs logical operations and data manupulation.
 * Interacts with Employee POJO, and EmployeeVO and validates user inputs.
 * 
 * @author Niraimathi S
 * @version 1.0 12-11-2021
 */
public class EmployeeService {
    private static Map<Integer, Employee> Employees 
            = new HashMap<Integer, Employee>();

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
     * @param employeeid-employeeid
     * @param employeeVO-The VO object to store the created employee.
     * @return boolean-true if employee created, else false.
     */
    public boolean createEmployee(int employeeId, EmployeeVO employeeVO) { 
        return (null == Employees.put(employeeId, 
                Mapper.EmployeeDTOToEmployee(employeeVO)));
    }

    /**
     * Checks if a employeeId exists or not.
     *
     * @param employeeid-employeeid to check if a record already exist.
     * @return boolean-true if given employeeId exist, else false.
     */
    public boolean isEmployeeExist(int employeeId) {
        return Employees.containsKey(employeeId);
    }

    /**
     * Checks if employees is empty.
     *
     * @return boolean-true if empty, else false.
     */
    public boolean isRecordsEmpty() {
        return Employees.isEmpty();
    }

    /**
     * Checks if a mobileNumber already exists or not.
     *
     * @param mobileNumber-the number to be checked for duplicate.
     * @return boolean-true if given mobileNumber exist, else false.
     */
    public boolean checkDuplicateMobileNumber(long mobileNumber) {
        boolean isMobileNumberDuplicate = false;
        for (Employee value:Employees.values()) {
            if (mobileNumber == value.getMobileNumber()) {
                isMobileNumberDuplicate = true; 
            }   
        } 
        return isMobileNumberDuplicate;
    }

    /**
     * Checks if a email already exists or not.
     *
     * @param email- email to be checked for duplicate.
     * @return boolean-true if given email exist, else false.
     */
    public boolean checkDuplicateEmail(String email) {
        boolean isEmailDuplicate = false;
        for (Employee value:Employees.values()) {
            if (email.equalsIgnoreCase(value.getEmail())) {
                isEmailDuplicate = true; 
            }   
        } 
        return isEmailDuplicate;
    }

    /**
     * Deletes all employees. 
     *
     */
    public void deleteAllEmployee() {
        Employees.clear();
    }

    /**
     * Deletes one employee. 
     *
     * @param employeeid-The employeeId of the employee to be deleted.
     */
    public void deleteOneEmployee(int employeeId) {
        Employees.remove(employeeId);
    }

    /**
     * Updates all fields of one employee. 
     *
     * @param employeeid-employeeid to update all fields
     * @param employeeVO-employeeVO object containing updated
     *                   values get from user
     * @return boolean-true if employee updated, else false.
     */
    public boolean updateAllFields(int employeeId, EmployeeVO employeeVO) { 
        return (null != Employees.replace(employeeId,
                        Mapper.EmployeeDTOToEmployee(employeeVO)));
    }

    /**
     * Updates name of a single employee. 
     *
     * @param employeeid-employeeid to update.
     * @param name-the updated name of the employee.
     */
    public void updateName(int employeeId, String name) {
        Employee employeeToUpdate = Employees.get(employeeId);
        employeeToUpdate.setName(name);
    }

    /**
     * Updates email of a single employee. 
     *
     * @param employeeid-employeeid to update.
     * @param email-the updated email of the employee.
     */
    public void updateEmail(int employeeId, String email) {
        Employee employeeToUpdate = Employees.get(employeeId);
        employeeToUpdate.setEmail(email);
    }

    /**
     * Updates contact number of a single employee. 
     *
     * @param employeeid-employeeid to update.
     * @param mobileNumber-the updated mobile number of the employee.
     */
    public void updateMobileNumber(int employeeId, long mobileNumber) {
        Employee employeeToUpdate = Employees.get(employeeId);
        employeeToUpdate.setMobileNumber(mobileNumber);
    }

    /**
     * Updates date of birth of a single employee. 
     *
     * @param employeeid-employeeid to update.
     * @param dateOfBirth-the updated date of birth of the employee.
     */
    public void updateDateOfBirth(int employeeId, LocalDate dateOfBirth) {
        Employee employeeToUpdate = Employees.get(employeeId);
        employeeToUpdate.setDateOfBirth(dateOfBirth);
    }

    /**
     * Updates salary of a single employee. 
     *
     * @param employeeid-employeeid to update.
     * @param salary-the updated salary of the employee.
     */
    public void updateSalary(int employeeId, float salary) {
        Employee employeeToUpdate = Employees.get(employeeId);
        employeeToUpdate.setSalary(salary);
    }

    /**
     * Displays one employee to the user.
     *
     * @param employeeid-employeeid to view.
     * @return employeeVO- view object for one employee.
     */
    public EmployeeVO viewOneEmployee(Integer employeeId) {
        Employee employee = Employees.get(employeeId);
        return (Mapper.EmployeeToEmployeeDTO(employee));
    }

    /**
     * Displays all employees to the user.
     *
     * @return List<employeeVO>- view object list containing all employees.
     */
    public List<EmployeeVO> viewAllEmployee() {
        List<EmployeeVO> employeeDetails = new ArrayList<>();
        for (Integer id : Employees.keySet()) {
            employeeDetails.add(Mapper.EmployeeToEmployeeDTO(Employees.get(id)));
        }
        return employeeDetails;
    }
}
