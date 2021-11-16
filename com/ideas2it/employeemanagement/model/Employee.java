/* 
 * Copyrights (c) Ideas2It
 */

package com.ideas2it.employeemanagement.model;

import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

/**
 * Employee is a POJO class with getter & setter methods 
 *
 * @author 	Niraimathi S
 * @version 	1.0
 */
public class Employee {
    private int employeeId;
    private long mobileNumber;
    private float salary;
    private LocalDate dateOfBirth;
    private String name;
    private String email;

    public Employee() {
    }

    /**
     * Constructor for Employee class to initialize values
     */
    public Employee (int employeeId, String name, String email,  
                     long mobileNumber, LocalDate dateOfBirth, float salary) {
        this.employeeId = employeeId;
        this.name = name;
        this.email = email;
        this.mobileNumber = mobileNumber;
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
     * Method to get Employee mobile Number.
     *
     * @return mobileNumber	Employee Phone number
     */
    public long getMobileNumber() {
        return mobileNumber;
    }

    /**
     * Method to set Employee mobile number.
     *
     * @param mobileNumber	Employee Contact Number
     */
    public void setMobileNumber(long mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    /**
     * Method to get Employee Date of birth
     *
     * @return dateOfBirth	Employee's date of Birth
     */
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Method to set Employee date of birth.
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

    public String toString() {
        StringBuilder employeeDetails = new StringBuilder();
        employeeDetails.append("\nEmployee ID\t:").append(employeeId)
                .append("\nName\t\t:").append(name).append("\nEmail\t\t:")
                .append(email).append("\nMobile number\t:").append(mobileNumber)
                .append("\nSalary\t\t:").append(salary)
                .append("\nDate Of birth\t:")
                .append(dateOfBirth);
        return employeeDetails.toString();
    }
}
