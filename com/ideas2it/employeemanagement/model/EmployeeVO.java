/* 
 * EmployeeVO.java 
 * COpyrights (c) Ideas2It
 */

package com.ideas2it.employeemanagement.model;

import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

/**
 * EmployeeVO is a Data Transfer class with getter & setter methods 
 *
 * @author 	Niraimathi S
 * @version 	1.0
 */
public class EmployeeVO {
    private int employeeId;
    private String name;
    private String email;
    private long contactNumber;
    private LocalDate dateOfBirth;
    private float salary;

    /**
     * Empty Constructor for EmployeeVO
     */
    public EmployeeVO() {
    }

    /**
     * Constructor for Employee class to initialize values
     */
    public EmployeeVO (int employeeId, String name, String email,  
            long contactNumber, LocalDate dateOfBirth, float salary) {
        this.employeeId = employeeId;
        this.name = name;
        this.email = email;
        this.contactNumber = contactNumber;
        this.dateOfBirth = dateOfBirth;
        this.salary = salary;
    }

    /**
     * Method to get Employee id
     *
     * @return employeeId-Employee id
     */
    public int getEmployeeId() {
        return employeeId;
    }

    /**
     * Method to set Employee Name
     *
     * @param name	Employee name
     */
    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    /**
     * Method to get Employee name
     *
     * @return name	Employee name
     */
    public String getName() {
        return name;
    }

    /**
     * Method to set Employee Name
     *
     * @param name	Employee name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Method to get Employee Email ID
     *
     * @return email	Employee Email ID
     */
    public String getEmail() {
        return email;
    }

    /**
     * Method to set Employee email ID
     *
     * @param email	Employee email id
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Method to get Employee Contact Number.
     *
     * @return contactNumber	Employee Phone number
     */
    public long getContactNumber() {
        return contactNumber;
    }

    /**
     * Method to set Employee contact number
     *
     * @param contactNumber	Employee Contact Number
     */
    public void setContactNumber(long contactNumber) {
        this.contactNumber = contactNumber;
    }

    /**
     * Method to get Employee Date of birth
     *
     * @return dateOfBirth	Employee's date of Birth
     */
    public LocalDate getDateOfBirth() {
        DateTimeFormatter dateFormatter 
                = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String dateAsString = dateOfBirth.format(dateFormatter);
        return (LocalDate.parse(dateAsString, dateFormatter));
    }

    /**
     * Method to set Employee contact number
     *
     * @param contactName	Employee Contact Number
     */
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * Method to get Employee salary
     *
     * @param salary	Employee salary
     */
    public float getSalary() {
        return salary;
    }

    /**
     * Method to set Employee salary
     *
     * @param salary	Employee salary
     */
    public void setSalary(float salary) {
        this.salary = salary;
    }
}
