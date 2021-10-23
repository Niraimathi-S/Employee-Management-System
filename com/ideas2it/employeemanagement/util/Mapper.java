/*
 * Mapper.java
 * 
 * Copyrights (C) Ideas2IT
 */

package com.ideas2it.employeemanagement.util;

import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

import com.ideas2it.employeemanagement.model.Employee;
import com.ideas2it.employeemanagement.model.EmployeeVO;

/**
 * This class contains methods to convert View object to POJO and viceversa.
 * 
 * @author Niraimathi S
 * @ version 1.0 12-11-2021
 */
public class Mapper{

    /**
     * This method converts Employee object to EmployeeVO object.
     * 
     * @param employeeid-employeeid
     * @param employee-employee object which is to be converted into DTO.
     * @return employeeVO-converted employeeVO object.
     */
    public static EmployeeVO EmployeeToEmployeeDTO(int employeeId, Employee employee) {
        EmployeeVO employeeVO = new EmployeeVO();
        employeeVO.setEmployeeId(employeeId);
        employeeVO.setName(employee.getName());
        employeeVO.setEmail(employee.getEmail());
        employeeVO.setContactNumber(employee.getContactNumber());
        employeeVO.setDateOfBirth(employee.getDateOfBirth());
        employeeVO.setSalary(employee.getSalary());
        return employeeVO;
    }

    /**
     * This method converts EmployeeVO object to Employee object.
     *
     * @param employeeid-employeeid
     * @param employeeVO-employeeVO object to convert into POJO object.
     * @return employee-converted employee object.
     */
    public static Employee EmployeeDTOToEmployee(int employeeId, 
                                         EmployeeVO employeeVO) {
        Employee employee = new Employee();
        employee.setEmployeeId(employeeId);
        employee.setName(employeeVO.getName());
        employee.setEmail(employeeVO.getEmail());
        employee.setContactNumber(employeeVO.getContactNumber());
        employee.setDateOfBirth(employeeVO.getDateOfBirth());
        employee.setSalary(employeeVO.getSalary());
        return employee;
    }
}
