/*
 * Copyrights (C) Ideas2IT
 */

package com.ideas2it.employeemanagement.dao;

import java.util.List;
import java.util.ArrayList;

import com.ideas2it.employeemanagement.model.Address;
import com.ideas2it.employeemanagement.model.Employee;
import com.ideas2it.employeemanagement.exception.EmployeeManagementException;
import com.ideas2it.employeemanagement.util.DatabaseConnection;


/**
 * Interacts with database and does appropriate actions based on users wish.
 * 
 * @author Niraimathi S
 * @version 1.0
 */

public interface EmployeeDAO {
    /**
     * Insert employee details into the database
     *
     * @param employee-Employee object to insert into database.
     * @return employee-inserted object if true or else null.
     */
    public Employee createEmployee(Employee employee) 
            throws EmployeeManagementException;

    /**
     * update employee details into the database
     *
     * @param employee-Employee object to update into database.
     */
    public Employee updateEmployee(Employee employee) 
            throws EmployeeManagementException;

    /**
     * Deletes employee details from the database
     *
     * @return true-if employee deleted or else false.
     */
    public boolean deleteAllEmployee() throws EmployeeManagementException;

    /**
     * Gets single employee's details from the database using Employee Id.
     *
     * @param employeeid-Employee id.
     * @return employee-employee details of a single employee.
     */
    public Employee getEmployeeById(int employeeId) 
            throws EmployeeManagementException;

    /**
     * Gets single employee's details from the database using email.
     *
     * @param email-Employee email id.
     * @return employee-employee details of a single employee.
     */
    public Employee getEmployeeByEmail(String email)
           throws EmployeeManagementException;

    /**
     * Gets single employee's details from the database using mobile number.
     *
     * @param mobileNumber-Employee mobile number.
     * @return employee-employee details of a single employee.
     */
    public Employee getEmployeeByMobileNumber(long mobileNumber) 
            throws EmployeeManagementException;

    /**
     * Deletes all employees details from the database
     *
     * @param employee-The employee object to be deleted.
     * @return true-if employee is deleted or else false.
     */
    public boolean deleteOneEmployee(Employee employee)
            throws EmployeeManagementException;

    /**
     * Gets all employee's details from the database.
     *
     * @return employees-list contains all employee details.
     */
    public List<Employee> getAllEmployees() throws EmployeeManagementException;
}
