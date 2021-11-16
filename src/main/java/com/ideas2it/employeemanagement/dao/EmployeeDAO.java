/*
 * Copyrights (C) Ideas2IT
 */

package com.ideas2it.employeemanagement.dao;

import com.ideas2it.employeemanagement.model.Address;
import com.ideas2it.employeemanagement.model.Employee;
import com.ideas2it.employeemanagement.util.DatabaseConnection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.ArrayList;

/**
 * Interacts with database and does appropriate actions based on users wish.
 * 
 * @author Niraimathi S
 * @version 1.0 12-11-2021
 */

public interface EmployeeDAO {
    /**
     * Insert employee details into the database
     *
     * @param employee-Employee object to insert into database.
     * @return employee-inserted object if true or else null.
     */
    public Employee createEmployee(Address address);

    /**
     * update employee details into the database
     *
     * @param employee-Employee object to update into database.
     * @return employee-updated object if true or else null.
     */
    public Employee updateEmployee(Address address);

    /**
     * Deletes employee details from the database
     *
     * @return true-if employee deleted or else false.
     */
    public boolean deleteAllEmployee();

    /**
     * Gets single employee's details from the database using Employee Id.
     *
     * @param employee-Employee id.
     * @return employee-employee details of a single employee.
     */
    public Employee getEmployeeById(int employeeId);

    /**
     * Gets single employee's details from the database using email.
     *
     * @param employee-Employee email id.
     * @return employee-employee details of a single employee.
     */
    public Employee getEmployeeByEmail(String email);

    /**
     * Gets single employee's details from the database using mobile number.
     *
     * @param employee-Employee mobile number.
     * @return employee-employee details of a single employee.
     */
    public Employee getEmployeeByMobileNumber(long mobileNumber);

    /**
     * Deletes all employees details from the database
     *
     * @return true-if all employees are deleted or else false.
     */
    public boolean deleteOneEmployee(int employeeId);

    /**
     * Deletes one employee's address from the database
     *
     * @return true-if deleted or else false.
     */
    public boolean deleteAddress(int addressId);

    /**
     * Gets all employee's details from the database.
     *
     * @return employees-list contains all employee details.
     */
    public List<Employee> getAllEmployees();
}
