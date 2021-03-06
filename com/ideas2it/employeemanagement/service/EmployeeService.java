/*
 * EmployeeSevice.java
 * 
 * Copyrights (C) Ideas2IT
 */
package com.ideas2it.employeemanagement.service;

import com.ideas2it.employeemanagement.model.Employee;
import com.ideas2it.employeemanagement.model.EmployeeVO;
import com.ideas2it.employeemanagement.util.Mapper;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * This class interacts with Employee POJO, and EmployeeVO.
 * 
 * @author Niraimathi S
 * @version 1.0 12-11-2021
 */
public class EmployeeService {
    private static Map<Integer, Employee> Employees 
            = new HashMap<Integer, Employee>();
    private static Scanner scanner = new Scanner(System.in);

    /**
     * This method validates the given input 
     *
     * @return input-validated input
     */
    public boolean validateInput(String input, String patternToValidate) { 
        return input.matches(patternToValidate);
    }
 
    /**
     * This method creates a new employee. 
     *
     */
    public void createEmployee(int employeeId, EmployeeVO employeeVO) { 
        Employees.put(employeeId, Mapper.EmployeeDTOToEmployee(employeeId, employeeVO));
    }

    /**
     * This method check if a employeeId exists or not.
     *
     * @return boolean-true if given employeeId exist, else false.
     */
    public boolean checkContainskey(int employeeId) {
        return Employees.containsKey(employeeId);
    }

    /**
     * This method check if the hashmap is empty.
     *
     * @return boolean-true if empty, else false.
     */
    public boolean checkEmpty() {
        return Employees.isEmpty();
    }

    /**
     * This method check if a contactnumber already exists or not 
     *
     * @return boolean-true if given contactnumber exist, else false.
     */
    public boolean checkDuplicateContactNumber(long contactNumber) {
        boolean isDuplicate = false;
        for (Employee value:Employees.values()) {
            if (contactNumber == value.getContactNumber()) {
                isDuplicate = true; 
            }   
        } 
        return isDuplicate;
    }

    /**
     * This method check if a email already exists or not 
     *
     * @return boolean-true if given email exist, else false.
     */
    public boolean checkDuplicateEmail(String email) {
        boolean isDuplicate = false;
        for (Employee value:Employees.values()) {
            if (email.equalsIgnoreCase(value.getEmail())) {
                isDuplicate = true; 
            }   
        } 
        return isDuplicate;
    }

    /**
     * This method deletes all employees. 
     *
     */
    public void deleteAllEmployee() {
        Employees.clear();
    }

    /**
     * This method deletes one employee's detail. 
     *
     */
    public void deleteOneEmployee(int employeeId) {
        Employees.remove(employeeId);
    }

    /**
     * This method updates all fields of one employee. 
     *
     */
    public void updateAllFields(int employeeId, EmployeeVO employeeVO) { 
        Employees.put(employeeId, Mapper.EmployeeDTOToEmployee(employeeId, employeeVO));
    }

    /**
     * This method updates name of the employee. 
     *
     */
    public void updateName(int employeeId, String name) {
        Employee employeeToUpdate = Employees.get(employeeId);
        employeeToUpdate.setName(name);
    }

    /**
     * This method updates email of the employee. 
     *
     */
    public void updateEmail(int employeeId, String email) {
        Employee employeeToUpdate = Employees.get(employeeId);
        employeeToUpdate.setEmail(email);
    }

    /**
     * This method updates contact number of the employee. 
     *
     */
    public void updateContactNumber(int employeeId, long contactNumber) {
        Employee employeeToUpdate = Employees.get(employeeId);
        employeeToUpdate.setContactNumber(contactNumber);
    }

    /**
     * This method updates date of birth of the employee. 
     *
     */
    public void updateDateOfBirth(int employeeId, LocalDate dateOfBirth) {
        Employee employeeToUpdate = Employees.get(employeeId);
        employeeToUpdate.setDateOfBirth(dateOfBirth);
    }

    /**
     * This method updates salary of the employee. 
     *
     */
    public void updateSalary(int employeeId, float salary) {
        Employee employeeToUpdate = Employees.get(employeeId);
        employeeToUpdate.setSalary(salary);
    }

    /**
     * This method retrives one employee from the collection.
     *
     * @return employeeVO- view object for one employee.
     */
    public EmployeeVO viewOneEmployee(Integer employeeId) {
        Employee employee = Employees.get(employeeId);

        return (Mapper.EmployeeToEmployeeDTO(employeeId, employee));
    }

    /**
     * This method retrives all employee from the collection.
     *
     * @return List<employeeVO>- view object list containing all employees.
     */
    public List<EmployeeVO> viewAllEmployee() {
        Employee employee = new Employee();
        EmployeeVO employeeVO = new EmployeeVO();
        List<EmployeeVO> employeeDetails = new ArrayList<>();

        for (Integer id : Employees.keySet()) {
            employee = Employees.get(id);
            employeeVO = Mapper.EmployeeToEmployeeDTO(id, employee);
            employeeDetails.add(employeeVO);
        }
        return employeeDetails;
    }
}
